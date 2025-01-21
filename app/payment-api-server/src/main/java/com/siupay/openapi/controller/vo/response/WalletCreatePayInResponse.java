package com.siupay.openapi.controller.vo.response;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: payment-api
 * @description: pay in create
 * @author: Sandy
 * @create: 2022-02-11
 **/
@ApiModel("发起钱包充值")
@Data
public class WalletCreatePayInResponse {

    @JsonProperty(value = "payment_amount")
    @ApiModelProperty(value = "金额", notes = "advcash")
    private String paymentAmount;

    @JsonProperty(value = "payment_currency")
    @ApiModelProperty(value = "币种", notes = "advcash")
    private String paymentCurrency;

    @JsonProperty(value = "payment_order_id")
    @ApiModelProperty(value = "订单id", required = true)
    private String paymentOrderId;

    @JsonProperty(value = "redirect_url")
    @ApiModelProperty(value = "渠道重定向地址", notes = "WALLET")
    private String redirectUrl;

    @JsonProperty(value = "channel_info")
    @ApiModelProperty(value = "渠道特异map")
    private Map<String, Object> channelInfo;
}
