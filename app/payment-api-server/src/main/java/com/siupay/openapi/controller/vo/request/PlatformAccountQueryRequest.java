package com.siupay.openapi.controller.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("查询平台账户账户请求信息")
@Data
public class PlatformAccountQueryRequest {


    @JsonProperty(value = "account_type")
    @ApiModelProperty(value = "账户类型")
//    @In(value = {"THIRDPAY", "capitual"}, message = "The account_type format is incorrect.")
    private String accountType;

    @JsonProperty(value = "account_id")
    @ApiModelProperty(value = "交易账号")
    private String accountId;

    @ApiModelProperty(value = "交易币种")
    private String currency;
}
