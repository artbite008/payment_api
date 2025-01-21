package com.siupay.openapi.bo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CalculateFeeRequestBo {

    /**
     * 渠道code
     */
    private String channelId;

    /**
     * 支付方式code
     */
    @Deprecated
    private String paymentMethodCode;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 法币币种
     */
    private String fiatCurrency;

    /**
     * 卡id
     */
    private String cardId;

    /**
     * 法币交易金额
     */
    private BigDecimal fiatAmount;
}
