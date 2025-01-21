package com.siupay.openapi.controller.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ywainlan
 * @date: 2022/5/1
 * @Description:
 */
@ApiModel("查询渠道信息响应")
@Data
public class ChannelInfoQueryResponse {

    @JsonProperty(value = "reference")
    @ApiModelProperty(value = "附言",notes = "与订单一一对应")
    private String reference;

    @JsonProperty(value = "payee")
    @ApiModelProperty(value = "收款人")
    private String payee;
}
