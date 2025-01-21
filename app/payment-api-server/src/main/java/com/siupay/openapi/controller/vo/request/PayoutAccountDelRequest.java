package com.siupay.openapi.controller.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @author minn
 * @description
 * @date 2021/11/28
 */
@ApiModel("删除提现账户请求信息")
@Data
public class PayoutAccountDelRequest {
    @ApiModelProperty(value = "账号id")
    @JsonProperty(value = "payout_account_id")
    private String payoutAccountId;
    /**
     * 设备来源
     */
    @JsonProperty(value = "client_from")
    private String clientFrom = "WEB";

    @ApiModelProperty(value = "请求扩展信息")
    private Map<String, Object> extra;

}
