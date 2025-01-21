package com.siupay.openapi.controller.vo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("平台账户汇总信息")
@Data
public class PlatformAccountSummaryInfoResponse {

    @JsonProperty(value = "account_base_info")
    @ApiModelProperty(value = "账户基本信息")
    List<PlatformAccountBaseInfoResponse> accountBaseInfo;

    @JsonProperty(value = "support_currencies")
    @ApiModelProperty(value = "支持查询的币种")
    private List<String> supportCurrencies;

    @JsonProperty(value = "support_biz_types")
    @ApiModelProperty(value = "支持的业务类型")
    private List<String> supportBizTypes;

    /**
     * accoutType
     */
    @ApiModelProperty(name = "账务类型")
    private List<String> types;

    /**
     * tag
     */
    @ApiModelProperty(name = "账务tag")
    private List<String> tags;
}
