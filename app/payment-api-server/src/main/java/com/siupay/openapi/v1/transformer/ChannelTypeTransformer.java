package com.siupay.openapi.v1.transformer;

import com.siupay.common.lib.enums.PaymentMethod;

public class ChannelTypeTransformer {
    public static PaymentMethod toPaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethod;
    }
}
