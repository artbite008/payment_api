package com.siupay.openapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RateLimiterStrategyEnum {
    ORDER_RATE_LIMITER_STRATEGY,
    CUSTOM_STRATEGY;
}
