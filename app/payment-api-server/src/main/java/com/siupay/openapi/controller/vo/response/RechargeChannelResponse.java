package com.siupay.openapi.controller.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel("充值支付方式列表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RechargeChannelResponse {
    @ApiModelProperty(value = "渠道", example = "checkout")
    private String channelName;

    @ApiModelProperty(value = "渠道类型", example = "wallet/bank")
    private String channelType;

    @ApiModelProperty(value = "渠道图标", example = "www.xxx.com")
    private String channelIcon;

    @ApiModelProperty(value = "平台方手续费类型", example = "FLOAT/REGULAR")
    private String siupayRateType;

    @ApiModelProperty(value = "平台方手续费", example = "0.33/33")
    private String siupayFee;

    @ApiModelProperty(value = "渠道方平台方手续费类型, 可能出现某些渠道是费率或者固定数量手续费")
    private String channelRateType;

    @ApiModelProperty(value = "渠道方手续费", example = "0.33%/33")
    private String channelFee;

    @ApiModelProperty(value = "渠道状态 0/1", example = "可用/不可用")
    private Integer channelStatus;

    @ApiModelProperty(value = "渠道提示信息", example = "渠道不支持该法币")
    private String channelMessage;

    @ApiModelProperty(value = "渠道最近使用时间")
    private Date recentUsedTime;

    @ApiModelProperty(value = "渠道使用次数", example = "9")
    private Integer channelUsedNumbers;

    @ApiModelProperty(value = "渠道重定向地址", example = "www.xxx.io")
    private String channelRedirectUrl;
}

