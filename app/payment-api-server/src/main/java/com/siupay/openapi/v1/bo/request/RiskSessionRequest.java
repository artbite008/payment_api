package com.siupay.openapi.v1.bo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("获取sessionid请求")
public class RiskSessionRequest {
    @ApiModelProperty("业务类型,1:recharge充值 \n 2:card,绑定卡 \n 3:byCrypto, 4: pci 绑卡")
    @NotBlank(message = "bizType can't be null")
    String bizType;
}
