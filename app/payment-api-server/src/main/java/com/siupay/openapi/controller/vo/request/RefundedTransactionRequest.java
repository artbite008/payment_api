package com.siupay.openapi.controller.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siupay.common.api.dto.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "线下退款")
public class RefundedTransactionRequest extends BaseDto {
    @NotNull(message = "payin_trxn_id is not allowed null!")
    @ApiModelProperty(name = "payin订单id", value = "payin_trxn_id", required = true)
    @JsonProperty(value = "payin_trxn_id")
    private String payinTrxnId;
}
