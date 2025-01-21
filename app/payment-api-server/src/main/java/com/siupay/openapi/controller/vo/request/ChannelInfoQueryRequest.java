package com.siupay.openapi.controller.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author: ywainlan
 * @date: 2022/5/1
 * @Description:
 */
@ApiModel("查询渠道信息请求")
@Data
public class ChannelInfoQueryRequest {

    @JsonProperty(value = "channelId")
    @ApiModelProperty(value = "渠道ID")
    @NotEmpty(
            message = "channelId is empty"
    )
    private String channelId;
}
