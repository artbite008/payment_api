package com.siupay.openapi.controller.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel("提现交易流水详情请求")
public class PayOutTransactionDetailsRequest {

    /**
     * 订单id
     */
    @NotEmpty(message = "payOutTrxnId can't be null")
    @ApiModelProperty("订单流水id")
    @JsonProperty("payout_trxn_id")
    private String payOutTrxnId;
}
