package com.siupay.openapi.util;

import static com.siupay.common.lib.enums.PaymentMethod.BANK_CARD;
import static com.siupay.common.lib.enums.PaymentMethod.WALLET;

import java.util.List;

import com.google.common.collect.Lists;
import com.siupay.enums.PayinOrderStatusEnum;

public class IntegerConvertString {

    public static String bizSourceConvert(Integer bizSource) {
        if (bizSource == null)
            return null;
        switch (bizSource) {
            case 2:
                return BANK_CARD.name();
            case 1:
                return WALLET.name();
            default:
                return null;
        }
    }

    public static List<String> payinStatusConvert(Integer status) {
        if (status == null)
            return null;
        switch (status) {
            case 1:
                return Lists.newArrayList(PayinOrderStatusEnum.SUCCEEDED.name());
            case 2:
                return Lists.newArrayList(PayinOrderStatusEnum.FAILED.name());
            default:
                return Lists.newArrayList(PayinOrderStatusEnum.PAYIN_COMPLETED.name(),
                        PayinOrderStatusEnum.PCC_ORDER_CRYPTO_FAIL.name(),
                        PayinOrderStatusEnum.PCC_ORDER_CRYPTO_PROCESSING.name());
        }
    }
}
