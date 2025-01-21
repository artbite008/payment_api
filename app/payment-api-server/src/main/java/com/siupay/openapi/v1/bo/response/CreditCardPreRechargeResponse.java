package com.siupay.openapi.v1.bo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@ApiModel("用户充值")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardPreRechargeResponse {

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "订单明细id")
    private String orderDetailId;

    @ApiModelProperty(value = "渠道跳转信息")
    private String redirectUrl;

    @ApiModelProperty(value = "第三方订单号")
    private String channelBizId;

    @ApiModelProperty(value = "订单状态")
    private String rechargeStatus;

    @ApiModelProperty(value = "额外信息")
    private Map<String, Object> ext = new HashMap<>();


}

