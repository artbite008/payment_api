package com.siupay.openapi.config;


import com.siupay.common.api.enums.PaymentSystem;
import com.siupay.common.api.exception.ErrorCode;
import com.siupay.common.api.exception.PaymentException;
import com.siupay.common.api.utils.UserContextUtils;
import com.siupay.openapi.annotation.AccessLimit;
import com.siupay.openapi.constant.DynamicConstants;
import com.siupay.openapi.enums.LimitLockKeyEnum;
import com.siupay.openapi.enums.LimitTypeEnum;
import com.siupay.openapi.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.IntegerCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class AccessLimitInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedissonClient client;

    @Autowired
    private DynamicConstants dynamicConstants;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            if (handler instanceof HandlerMethod) {
                HandlerMethod hm = (HandlerMethod) handler;
                AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
                if (null == accessLimit) {
                    return true;
                }
                String keySuffix = accessLimit.lockKey().getValue();
                LimitTypeEnum limitTypeEnum = accessLimit.limitType();
                String key = limitTypeEnum.getKeyPre().concat(keySuffix);
                switch (limitTypeEnum) {
                    case USER:
                        String reqMethodName = hm.getMethod().getName();
                        Map<String,String[]> paramMap = request.getParameterMap();
                        userAccessLimit(paramMap, reqMethodName, key);
                        break;
                    case ADMIN:
                        //admin接口，5秒一次调用
                        boolean adminLimit = accessLimitCheck(key, dynamicConstants.getAccessLimitAdminSeconds(), dynamicConstants.getAccessLimitAdminMaxCount());
                        if (!adminLimit) {
                            throw new PaymentException(ErrorCode.CALL_TOO_FREQUENCY);
                        }
                        break;
                }
            }
        } catch (PaymentException e) {
            throw e;
        } catch (Exception e) {
            log.error("[AccessLimitInterceptor.preHandle] 限流检查异常,异常原因:"+e.getMessage(),e);
        }
        return true;
    }

    public void userAccessLimit(Map<String,String[]> paramMap, String reqMethodName, String key) {
        String userId = UserContextUtils.getUserId();
        //获取controller中方法
        //获取接口中source来源参数
        String[] sources = paramMap.get("source");
        Map<String,Map<String,Integer>> urlLimitMap = dynamicConstants.getUrlLimitMap();
        //用户登陆
        if(!ObjectUtils.isEmpty(userId)){
            String source = sources[0];
            Integer accessLimitMaxCount = dynamicConstants.getAccessLimitUserMaxCount();
            String sourceType = StringUtils.isEmpty(source)? Constant.URL_LIMIT_GLOBAL:source.toUpperCase();
            if(!ObjectUtils.isEmpty(urlLimitMap)){
                Map<String,Integer> urlMap = urlLimitMap.get(reqMethodName);
                if(!ObjectUtils.isEmpty(urlMap)){
                    Integer limit = urlMap.get(sourceType);
                    if(!ObjectUtils.isEmpty(limit)){
                        accessLimitMaxCount = limit;
                    }
                }
            }
            key = key.concat(userId);
            boolean userLimit = accessLimitCheck(key, dynamicConstants.getAccessLimitUserSeconds(), accessLimitMaxCount);
            if (!userLimit) {
                throw new PaymentException(ErrorCode.CALL_TOO_FREQUENCY);
            }

        }else {//用户未登陆
            int globalLimit = dynamicConstants.getAccessLimitGlobalUserMaxCount();
            if(!ObjectUtils.isEmpty(urlLimitMap)){
                Map<String,Integer> urlMap = urlLimitMap.get(reqMethodName);
                if(!ObjectUtils.isEmpty(urlMap)){
                    Integer limit = urlMap.get(Constant.URL_LIMIT_GLOBAL);
                    if(!ObjectUtils.isEmpty(limit)){
                        globalLimit = limit;
                    }
                }
            }
            //全局锁: 接口，全局限流
            boolean urlLimitGlobal = accessLimitCheck(LimitLockKeyEnum.USER.getValue().concat(reqMethodName),
                    dynamicConstants.getAccessLimitGlobalUserSeconds(), globalLimit);
            if (!urlLimitGlobal) {
                throw new PaymentException(ErrorCode.CALL_TOO_FREQUENCY);
            }
        }
    }

    private boolean accessLimitCheck(String key, int seconds, int maxCount) {
        int accessCount = 0;
        try {
            RMapCache<String, Integer> mapCache = client.getMapCache(PaymentSystem.PAYMENT_API.getDesc(), IntegerCodec.INSTANCE);
            mapCache.putIfAbsent(key, 0, seconds, TimeUnit.SECONDS);
            accessCount = mapCache.addAndGet(key, 1);
        } catch (Exception e) {
            log.error("[AccessLimitInterceptor.accessLimitCheck] 限流检查异常");
        }
        return accessCount <= maxCount;
    }
}
