package com.siupay.openapi.controller.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel("查询支持的支付方式")
public class SupportChannelRequest {

    @ApiModelProperty("法币币种")
    @NotEmpty(message = "fiatCurrency can't be null")
    private String fiatCurrency;

    @ApiModelProperty("交易类型(buy sell recharge withdraw)")
    @NotEmpty(message = "tradeType can't be null")
    private String tradeType;
}
