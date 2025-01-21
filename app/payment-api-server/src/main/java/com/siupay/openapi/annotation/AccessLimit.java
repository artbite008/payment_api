package com.siupay.openapi.annotation;

import com.siupay.openapi.enums.LimitLockKeyEnum;
import com.siupay.openapi.enums.LimitTypeEnum;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
    LimitLockKeyEnum lockKey();

    LimitTypeEnum limitType();
}
