package com.siupay.openapi.controller.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author minn
 * @description
 * @date 2021/11/30
 */
@Data
@ApiModel("提现信息")
public class PayoutRiskResponse {

    @JsonProperty(value = "payout_trxn_id")
    @ApiModelProperty(value = "提现订单id")
    private String payoutTrxnId;

    @ApiModelProperty(value = "订单状态")
    private String status;

    @JsonProperty(value = "status_message")
    @ApiModelProperty(value = "失败原因")
    private String statusMessage;


}
