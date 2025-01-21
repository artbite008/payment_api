package com.siupay.openapi.controller.vo.response;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("平台账户信息")
@Data
public class PlatformAccountInfoResponse {

    @JsonProperty(value = "account_id")
    @ApiModelProperty(value = "账户编号")
    private String accountId;

    @JsonProperty(value = "account_type")
    @ApiModelProperty(value = "账户类型")
    private String accountType;

    @JsonProperty(value = "display_name")
    @ApiModelProperty(value = "账户名称")
    private String displayName;

    @JsonProperty(value = "account_tag")
    @ApiModelProperty(value = "用户账户子类型")
    private String accountTag;

    @ApiModelProperty(value = "币种")
    private String currency;

    @JsonProperty(value = "total_balance")
    @ApiModelProperty(value = "账户总额")
    private BigDecimal totalBalance;

    @JsonProperty(value = "available_balance")
    @ApiModelProperty(value = "账户可用余额")
    private BigDecimal availableBalance;

    @JsonProperty(value = "hold_balance")
    @ApiModelProperty(value = "账户冻结金额")
    private BigDecimal holdBalance;

    @JsonProperty(value = "updated_at")
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;


}
