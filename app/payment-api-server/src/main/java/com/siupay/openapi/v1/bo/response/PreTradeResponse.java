package com.siupay.openapi.v1.bo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@ApiModel("用户信用卡买币")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreTradeResponse {

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "订单明细id")
    private String orderDetailId;

    @ApiModelProperty(value = "交易跳转信息")
    private String redirectUrl;

    @ApiModelProperty(value = "订单状态")
    private String tradeStatus;

    @ApiModelProperty(value = "额外信息")
    private Map<String, Object> ext = new HashMap<>();


}

