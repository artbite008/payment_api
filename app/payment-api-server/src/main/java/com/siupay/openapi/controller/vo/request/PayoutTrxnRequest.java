package com.siupay.openapi.controller.vo.request;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author minn
 * @description
 * @date 2021/11/30
 */
@Data
@ApiModel("发起提现请求信息")
public class PayoutTrxnRequest {

    @JsonProperty(value = "channel_id")
    @ApiModelProperty(value = "渠道id", notes = "sepa_transactive, capitual")
    @NotBlank(message = "The channel_id format is incorrect.")
    private String channelId;

    @JsonProperty(value = "payment_name")
    @ApiModelProperty(value = "支付方式名称")
    @Size(min = 1, max = 36, message = "The payment_name format is incorrect.")
    private String paymentName;

    @ApiModelProperty(value = "法币币种")
    @Pattern(regexp = "[A-Z]{1,4}", message = "The currency format is incorrect.")
    private String currency;

    @ApiModelProperty(value = "提现金额", example = "1")
    @NotNull
    private BigDecimal amount;

    @JsonProperty(value = "payout_account_id")
    @ApiModelProperty(value = "收款账户id")
    @Size(min = 1, max = 36, message = "The payout_account_id format is incorrect.")
    private String payoutAccountId;

    @ApiModelProperty(value = "来源", notes = "web|ios|android")
//    @In(value = {"web", "ios", "android"}, message = "The source format is incorrect.")
    private String source;

    @ApiModelProperty("设备指纹")
    @JsonProperty(value = "finger_id")
    private String fingerId;

    @ApiModelProperty("扩展参数")
    private Map<String, Object> extra;

}
