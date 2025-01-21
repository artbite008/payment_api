package com.siupay.openapi.v1.bo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@ApiModel("用户充值法币入参")
@Data
public class BasePrRechargeRequest {
    /**
     * 渠道名称 （当前默认默认渠道为checkout）
     */
    @ApiModelProperty(value = "渠道名称", example = "checkout")
    private String channelName = "checkout";
    /**
     * 渠道类型
     */
    @ApiModelProperty(value = "渠道类型", example = "credit_card")
    @NotEmpty(message = "channelType is empty")
    private String channelType;
    /**
     * 金额
     */
    @ApiModelProperty(value = "金额", example = "100.00")
    @NotNull(message = "rechargeAmount is null")
    @Min(value = 0, message = "recharge amount less than 0")
    @Max(value = 1000000000, message = "rechargeAmount more than 1000000000")
    private BigDecimal rechargeAmount;
    /**
     * 币种
     */
    @ApiModelProperty(value = "币种", example = "CNY/USD")
    @NotEmpty(message = "fiatCurrency is empty")
    private String fiatCurrency;

    @ApiModelProperty(value = "来源设备", example = "ANDROID,IOS,WEB,M_SITE")
    private String clientFrom = "WEB";

    /**
     * 扩展属性
     */
    @ApiModelProperty(value = "扩展属性", example = "CHECKOUT-CARD-TOKEN")
    private Map<String, Object> ext = new HashMap<>();

}
