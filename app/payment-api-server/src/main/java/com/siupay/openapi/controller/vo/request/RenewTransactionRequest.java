package com.siupay.openapi.controller.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siupay.common.api.dto.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "更新附言")
public class RenewTransactionRequest extends BaseDto {
    @JsonProperty(value = "payin_trxn_id")
    @ApiModelProperty(name = "payin订单id", value = "payin_trxn_id", required = true)
    @NotNull(message = "payin_trxn_id is not allowed null!")
    private String payinTrxnId;

    @ApiModelProperty(name = "附言", value = "reference", required = true)
    @JsonProperty(value = "reference")
    @NotNull(message = "reference is not allowed null!")
    private String reference;
}
