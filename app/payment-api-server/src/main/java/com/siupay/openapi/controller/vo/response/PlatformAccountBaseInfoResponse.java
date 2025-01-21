package com.siupay.openapi.controller.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("平台账户基本信息信息")
@Data
public class PlatformAccountBaseInfoResponse {

    @JsonProperty(value = "account_id")
    @ApiModelProperty(value = "账户编号")
    private String accountId;

    @JsonProperty(value = "account_type")
    @ApiModelProperty(value = "账户类型")
    private String accountType;

    @JsonProperty(value = "account_name")
    @ApiModelProperty(value = "账户名称")
    private String accountName;

    @JsonProperty(value = "account_tag")
    @ApiModelProperty(value = "用户账户子类型")
    private String accountTag;

}
