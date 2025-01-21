package com.siupay.openapi.util;

import com.siupay.common.lib.enums.PaymentMethod;
import com.siupay.common.lib.enums.TradeType;
import com.siupay.enums.PayinOrderStatusEnum;

public class StringConvertInteger {

    public static Integer payinStatusConvert(PayinOrderStatusEnum status){
        switch (status){
            case SUCCEEDED:
                return 1;
            case FAILED:
            case EXPIRED:
            case CANCELLED:
                return 2;
            default:
                return 0;
        }
    }

    public static Integer tradeTypeConvert(TradeType tradeType){
        switch (tradeType){
            case BUY:
                return 0;
            case DEPOSIT:
                return 1;
            default:
                return null;
        }
    }

    public static Integer bizSourceConvert(String paymentMethod){
        switch (PaymentMethod.valueOf(paymentMethod.toUpperCase())){
            case BANK_CARD:
                return 2;
            case WALLET:
                return 1;
            default:
                return null;
        }
    }

    public static Integer refTypeConvert(String refType){
        switch (refType.toUpperCase()){
            case "BUY":
                return 1;
            case "SELL":
                return 2;
            default:
                return null;
        }
    }
}
