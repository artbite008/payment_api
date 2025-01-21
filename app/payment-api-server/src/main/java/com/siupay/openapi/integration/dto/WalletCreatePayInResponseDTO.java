package com.siupay.openapi.integration.dto;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Data;

/**
 * @program: payment-api
 * @description: pay in create
 * @author: Sandy
 * @create: 2022-02-11
 **/
@Data
public class WalletCreatePayInResponseDTO {

    /**
     * 金额
     */
    private String paymentAmount;

    /**
     * 币种
     */
    private String paymentCurrency;

    /**
     * 订单id
     */
    private String payinTrxnId;

    /**
     * 渠道重定向地址
     */
    String redirectUrl;

    /**
     * 渠道特异map
     */
    private Map<String, Object> channelInfo;
}
