package com.siupay.openapi.controller.vo.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @program: payment-api
 * @description: pay in create
 * @author: Sandy
 * @create: 2022-02-11
 **/
@ApiModel("发起钱包充值")
@Data
public class WalletCreatePayInRequest {

    // TODO

    @JsonProperty(value = "channel_id")
    @ApiModelProperty(value = "渠道id", notes = "advcash")
    @NotBlank(message = "The channel_id format is incorrect.")
    private String channelId;

    @JsonProperty(value = "payment_amount")
    @ApiModelProperty(value = "金额", notes = "advcash")
    @Max(value = 1000000000)
    private BigDecimal paymentAmount;

    @JsonProperty(value = "payment_currency")
    @ApiModelProperty(value = "法币币种", required = true)
    @Pattern(regexp = "[A-Z]{1,4}", message = "The payment_currency format is incorrect.")
    private String paymentCurrency;

    @JsonProperty(value = "payment_method")
    @ApiModelProperty(value = "支付类型", notes = "WALLET")
    @NotBlank(message = "The payment_method format is incorrect.")
    private String paymentMethod;

    @ApiModelProperty(value = "来源", notes = "web|ios|android")
//    @In(value = {"web", "ios", "android"}, message = "The source format is incorrect.")
    private String source;
}
