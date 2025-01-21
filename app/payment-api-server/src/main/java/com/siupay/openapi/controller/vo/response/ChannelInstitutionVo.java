package com.siupay.openapi.controller.vo.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ywainlan
 * @date: 2022/5/1
 * @Description:
 */
@ApiModel("ChannelInstitution")
@Data
public class ChannelInstitutionVo {

    @JsonProperty(value = "institutionNumber")
    @ApiModelProperty(value = "机构编号，联合主键")
    private int institutionNumber;

    @JsonProperty(value = "institutionId")
    @ApiModelProperty(value = "机构ID")
    private String institutionId;

    @JsonProperty(value = "institutionName")
    @ApiModelProperty(value = "机构名称")
    private String institutionName;

    @JsonProperty(value = "channelBankInfo")
    @ApiModelProperty(value = "银行数据扩展数据")
    private Map<String, Object> channelBankInfo;

    @JsonProperty(value = "logo")
    @ApiModelProperty(value = "logo")
    private String logo;

}
