package com.siupay.openapi.v1.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum RiskLocalBizTypeEnum {
    /**
     * 绑卡
     */
    CARD("card", null),
    /**
     * 充值
     */
    PRECHARGE("recharge", PaymentMethodEnum.BANK_CARD),
    /**
     * 买币
     */
    BYCRPPTO("bycrypto", PaymentMethodEnum.BANK_CARD);

    /**
     * code
     */
    private String code;

    /**
     * 支付方式枚举
     */
    private PaymentMethodEnum paymentMethodEnum;

    public static RiskLocalBizTypeEnum getEnumByCode(String code) {
        for (RiskLocalBizTypeEnum value : RiskLocalBizTypeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

    /**
     * 转换风控请求枚举
     * 
     * @param code
     * @return
     */
    public static PaymentMethodEnum getPayment(String code) {
        for (RiskLocalBizTypeEnum typeEnum : RiskLocalBizTypeEnum.values()) {
            if (Objects.equals(getEnumByCode(code), typeEnum)) {
                return typeEnum.getPaymentMethodEnum();
            }
        }
        return null;
    }
}
