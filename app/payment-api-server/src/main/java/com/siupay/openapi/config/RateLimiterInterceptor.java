package com.siupay.openapi.config;


import com.siupay.common.api.exception.ErrorCode;
import com.siupay.common.api.exception.PaymentException;
import com.siupay.common.api.utils.UserContextUtils;
import com.siupay.openapi.annotation.RateLimiter;
import com.siupay.openapi.constant.DynamicConstants;
import com.siupay.openapi.enums.RateLimiterStrategyEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RateIntervalUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * API限流控制类
 * PA-2642
 */
@Component
@Slf4j
public class RateLimiterInterceptor extends HandlerInterceptorAdapter {

    private static final String RATE_LIMITER_API = "RATE_LIMITER_PAYMENT_API:";
    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    private DynamicConstants dynamicConstants;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            if (handler instanceof HandlerMethod) {
                HandlerMethod hm = (HandlerMethod) handler;
                RateLimiter rateLimiter = hm.getMethodAnnotation(RateLimiter.class);
                if (Objects.nonNull(rateLimiter)) {
                    RateLimiterStrategyEnum rateLimiterStrategyEnum = rateLimiter.strategy();
                    //走默认order策略
                    String limiterStrategy = null;
                    if (RateLimiterStrategyEnum.ORDER_RATE_LIMITER_STRATEGY.equals(rateLimiterStrategyEnum)) {
                        limiterStrategy = dynamicConstants.getOrderRateLimiterStrategy();
                    } else {//走自定义策略
                        limiterStrategy = rateLimiter.limiterExpr();
                    }
                    boolean canAcquire = canAcquire(limiterStrategy, hm);
                    if (!canAcquire) {
                        throw new PaymentException(ErrorCode.CALL_TOO_FREQUENCY);
                    }
                }
            }
        } catch (PaymentException e) {
            throw e;
        } catch (Exception e) {
            log.error("[RateLimiterInterceptor.preHandle] 限流检查异常,异常原因:" + e.getMessage(), e);
        }
        return true;
    }

    private boolean canAcquire(String limiterStrategy, HandlerMethod hm) {
        boolean canAcquire = true;
        String method = hm.getMethod().getDeclaringClass().getSimpleName() + ":" + hm.getMethod().getName();
        String userId = UserContextUtils.getUserId();
        String[] corns = limiterStrategy.split(" ");
        if (corns.length != 4) {
            log.error("method: {} , limiterStrategy error:{} ,must set like '0 1 1 0 0'", method, limiterStrategy);
            return true;
        } else if (StringUtils.isEmpty(userId)) {
            log.error("method: {} , limiterStrategy error: userId is empty", method);
            return true;
        } else {
            for (int i = 0; i < corns.length; i++) {
                String corn = corns[i];
                if (!StringUtils.equals("0", corn)) {
                    int rate = getRate(corn);
                    int rateInterval = getRateInterval(corn);
                    RateIntervalUnit rateIntervalUnit = RateIntervalUnit.values()[i];
                    String key = genAccessLimitKey(rateIntervalUnit, method, userId);
                    TimeUnit timeUnit = TimeUnit.values()[i + 3];
                    canAcquire = this.acquire(key, rate, rateInterval, timeUnit);
                    if (!canAcquire) {
                        return canAcquire;
                    }
                }
            }
        }
        return canAcquire;
    }


    private int getRateInterval(String corn) {
        if (corn.indexOf("/") > 0) {
            return Integer.parseInt(corn.substring(corn.indexOf("/") + 1));
        } else {
            return 1;
        }
    }

    private int getRate(String corn) {
        if (corn.indexOf("/") > 0) {
            return Integer.parseInt(corn.substring(0, corn.indexOf("/")));
        } else {
            return Integer.parseInt(corn);
        }
    }

    private String genAccessLimitKey(RateIntervalUnit rateIntervalUnit, String method, String id) {
        return RATE_LIMITER_API + method + ":" + rateIntervalUnit.name() + ":" + id;
    }


    /**
     * @param key         key
     * @param limitCount  限制数量
     * @param limitPeriod 时间周期
     * @param timeUnit    时间周期单位
     * @return boolean 是否成功
     */
    protected boolean acquire(String key, long limitCount, long limitPeriod, TimeUnit timeUnit) {
        RedisScript<String> redisLuaScript = new DefaultRedisScript<>(getScriptAsString(), String.class);
        long now = System.currentTimeMillis();
        final long ms = TimeUnit.MILLISECONDS.convert(limitPeriod, timeUnit);
        final String result = redisTemplate.execute(redisLuaScript, Collections.singletonList(key),
                String.valueOf(limitCount), String.valueOf(now), String.valueOf(ms));
        log.info("ratelimiter payment-api 限流 key: {} ,result:{} ",key,result);
        return StringUtils.isNotEmpty(result);
    }

    /**
     * lua脚本限流
     */

    public String getScriptAsString() {
        return  "if redis.call('EXISTS' ,KEYS[1]) then "+
                "redis.call('ZREMRANGEBYSCORE' ,KEYS[1] ,0 ,tonumber(ARGV[2]) - tonumber(ARGV[3])); end; \n" +
                "if (redis.call('ZCARD' , KEYS[1]) >= tonumber(ARGV[1])) then return nil; end;\n" +
                "redis.call('ZADD' ,KEYS[1] ,tonumber(ARGV[2]) ,ARGV[2]); \n" +
                "redis.call('pexpire' , KEYS[1] , tonumber(ARGV[3])); \n" +
                "return 'true';";
    }
}
