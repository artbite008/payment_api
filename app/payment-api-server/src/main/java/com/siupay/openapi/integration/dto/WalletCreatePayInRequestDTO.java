package com.siupay.openapi.integration.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @program: payment-api
 * @description: pay in create
 * @author: Sandy
 * @create: 2022-02-11
 **/
@Data
public class WalletCreatePayInRequestDTO {

    /**
     * 渠道id
     */
    private String channelId;

    /**
     * 金额
     */
    private BigDecimal paymentAmount;

    /**
     * 法币币种
     */
    private String paymentCurrency;

    /**
     * 支付方式
     */
    private String paymentMethod;

    /**
     * 来源
     */
    private String source;

    /**
     * request id
     */
    private String requestId;

    /**
     * request system
     */
    private String requestSystem;
}
