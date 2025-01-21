package com.siupay.openapi.v1.transformer;

import com.siupay.openapi.v1.bo.response.OrderConfirmResponse;
import org.springframework.beans.BeanUtils;

public class DepositOrderConfirmResponseTransformer {
    public static OrderConfirmResponse toV1(OrderConfirmResponse depositOrder) {
        OrderConfirmResponse target = new OrderConfirmResponse();
        BeanUtils.copyProperties(depositOrder, target);
        return target;
    }
}
