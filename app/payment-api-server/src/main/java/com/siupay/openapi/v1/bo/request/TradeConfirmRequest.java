package com.siupay.openapi.v1.bo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@ApiModel("信用卡买币交易订单入参")
@Data
public class TradeConfirmRequest {

    @ApiModelProperty(value = "订单id")
    @NotEmpty(message = "orderId is empty")
    private String orderId;

    /**
     * 用于查询cko 3ds结果
     */
    @ApiModelProperty(value = "3ds回跳结果参数")
    private String sid;
}
