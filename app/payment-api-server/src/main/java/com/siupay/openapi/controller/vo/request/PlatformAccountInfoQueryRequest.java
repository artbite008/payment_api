package com.siupay.openapi.controller.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@ApiModel("查询平台账户账户请求信息")
@Data
public class PlatformAccountInfoQueryRequest {

    @ApiModelProperty(value = "交易账号")
    @NotEmpty(message = "accountId is empty")
    private String accountId;

    @ApiModelProperty(value = "交易币种")
    private String currency;
}
