package com.siupay.openapi.v1.bo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("获取sessionid返回")
@Data
public class RiskSessionResponse {
    @ApiModelProperty("sessionId")
    public String sessionId;
    @ApiModelProperty("cybs请求的url")
    public String cybsUrl;
}
