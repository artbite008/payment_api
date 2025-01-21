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
@ApiModel("机构查询请求")
@Data
public class GetChannelBankRequest {

    @JsonProperty(value = "channelId")
    @ApiModelProperty(value = "渠道ID")
    @NotEmpty(
            message = "channelId is empty"
    )
    private String channelId;

    @JsonProperty(value = "bank")
    @ApiModelProperty(value = "银行", notes = "银行名称，提供模糊匹配，传值代表模糊查询，支持模糊分页")
    private String bank;

    @JsonProperty(value = "count")
    @ApiModelProperty(value = "数量",notes = "用于分页查询，默认全量")
    private int count;

    @JsonProperty(value = "offset")
    @ApiModelProperty(value = "偏移量", notes = "偏移量，用于分页查询")
    private int offset;
}
