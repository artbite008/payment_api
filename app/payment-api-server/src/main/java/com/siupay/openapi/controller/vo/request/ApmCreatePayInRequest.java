package com.siupay.openapi.controller.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author: ywainlan
 * @date: 2022/5/1
 * @Description:
 */
@ApiModel("发起充值请求")
@Data
public class ApmCreatePayInRequest {

    @JsonProperty(value = "paymentAmount")
    @ApiModelProperty(value = "金额")
    @NotNull(
            message = "paymentAmount is null"
    )
    @Min(
            value = 0L,
            message = "paymentAmount less than 0"
    )
    @Max(
            value = 1000000000L,
            message = "paymentAmount more than 1000000000"
    )
    private BigDecimal paymentAmount;

    @JsonProperty(value = "paymentCurrency")
    @ApiModelProperty(value = "币种")
    @NotEmpty(
            message = "paymentCurrency is empty"
    )
    private String paymentCurrency;

    @JsonProperty(value = "channelId")
    @ApiModelProperty(value = "渠道ID")
    @NotEmpty(
            message = "channelId is empty"
    )
    private String channelId;

    @JsonProperty(value = "source")
    @ApiModelProperty(value = "来源", example = "ANDROID,IOS,WEB")
    @NotEmpty(
            message = "source is empty"
    )
    private String source;

    @JsonProperty(value = "merchantId")
    @ApiModelProperty(value = "商户ID")
    private String merchantId;

    @JsonProperty(value = "reference")
    @ApiModelProperty(value = "附言")
    private String reference;

    @JsonProperty(value = "extraMap")
    @ApiModelProperty(value = "拓展字段", notes = "拓展字段")
    private Map<String,Object> extraMap;
}
