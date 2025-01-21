package com.siupay.openapi.annotation;

import com.siupay.openapi.enums.RateLimiterStrategyEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * PA-2642
 * API限流
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimiter {
    /**
     * 自定义限流表达式 支持按天，小时，分钟，秒限流  规则顺序从左到右一次是 秒，分钟，小时，天
     * 只有 strategy配置RateLimiterStrategyEnum.CUSTOM_STRATEGY 才会生效，否则会走对应的策略规则
     * 每秒允许5次 表达式： "5 0 0 0"
     * 每分钟允许5次 表达式： "0 5 0 0"
     * 每5秒允许一次 表达式： "1/5 0 0 0"
     * 组合策略
     * 每5秒允许1次，且每分钟允许10次，且每小时允许30次 表达式： "1/5 10 30 0"
     * @return
     */
    String limiterExpr() default "";

    /**
     * 策略类型，可以根据固定策略来匹配表达式或者自定义策略，匹配limiterExpr规则
     * @return
     */
    RateLimiterStrategyEnum strategy();


}
