package com.siupay.openapi.controller.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("费用信息")
public class PayFeeResponse {

    /**
     * platform fee
     */
    @JsonProperty("platform_fee")
    @ApiModelProperty(value = "平台手续费费用", name = "platform_fee")
    private PaymentAmountResponse platformFee;
    /**
     * channel transaction fee
     */
    @ApiModelProperty(value = "渠道费用", name = "channel_fee")
    @JsonProperty("channel_fee")
    private PaymentAmountResponse channelFee;

    /**
     * channel transaction fee
     */
    @ApiModelProperty(value = "上分金额", name = "deposit_amount")
    @JsonProperty("deposit_amount")
    private PaymentAmountResponse depositAmount;
}
