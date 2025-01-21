package com.siupay.openapi.controller.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: ywainlan
 * @date: 2022/5/1
 * @Description:
 */
@ApiModel("机构查询响应")
@Data
public class GetChannelBankResponse {

    @ApiModelProperty(value = "是否有更多数据")
    private Boolean hasNext;

    @ApiModelProperty(value = "总数据量")
    private Integer totalNum;

    @ApiModelProperty(value = "拓展内容")
    private ContextVo context;

    @ApiModelProperty(value = "数据列表")
    private List<ChannelInstitutionVo> items;

}
