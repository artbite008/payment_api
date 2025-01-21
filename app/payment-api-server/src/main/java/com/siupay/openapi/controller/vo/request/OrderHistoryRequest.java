package com.siupay.openapi.controller.vo.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author uther.chen
 * @description: 查询订单列表入参
 */
@ApiModel("订单列表入参")
@Data
public class OrderHistoryRequest extends BasePageRequest {
    @ApiModelProperty(value = "订单类型", example = "WITHDRAW/RECHARGE")
    @NotEmpty(message = "orderType is not allowed to empty!")
//    @In(value = {"BUY", "RECHARGE"})
    private String orderType;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "用户id", hidden = true)
    private String userId;

    @ApiModelProperty(value = "法币", example = "CNY/USD")
    @JsonProperty("fiat")
    @JsonAlias(value = {"fiat","fiatCurrency"})
    private String fiat;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;


    /**
     * 数字货币类型
     */
    @ApiModelProperty(value = "数字货币类型")
    private String cryptoCurrency;

    /**
     * 订单来源 1.余额买币 2.银行卡买币
     */
    @ApiModelProperty(value = "订单来源 1.余额买币 2.银行卡买币")
    private Integer bizSource;
}
