package com.siupay.openapi.v1.bo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@ApiModel("检查法币订单状态入参")
@Data
public class OrderConfirmRequest {

    @ApiModelProperty(value = "订单id")
    @NotEmpty(message = "orderId is empty")
    private String orderId;

    @ApiModelProperty(value = "订单类型")
    @NotEmpty(message = "orderType is empty")
//    @In(value = {"WITHDRAW", "RECHARGE"}, message = "orderType is not in the given range!")
    private String orderType;

    /**
     * 用于查询cko 3ds结果
     */
    @ApiModelProperty(value = "3ds回跳结果参数")
    private String sid;
}