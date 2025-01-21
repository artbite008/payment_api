package com.siupay.openapi.controller.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siupay.common.api.dto.BaseDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OptTransactionResponse  extends BaseDto {
    @JsonProperty(value = "payin_trxn_id")
    @ApiModelProperty(value = "交易流水", name = "payin_trxn_id")
    private String payinTrxnId;
    @JsonProperty(value = "status")
    @ApiModelProperty(value = "匹配的结果:成功：true，失败：false", name = "status")
    private boolean status;
}
