package com.siupay.openapi.controller.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author: ywainlan
 * @date: 2022/5/1
 * @Description:
 */
@ApiModel("发起充值响应")
@Data
public class ApmCreatePayInResponse {

    @ApiModelProperty(value = "金额")
    private BigDecimal paymentAmount;

    @ApiModelProperty(value = "币种")
    private String paymentCurrency;

    @ApiModelProperty(value = "订单id")
    private String paymentOrderId;

    @ApiModelProperty(value = "订单状态")
    private String status;

    @ApiModelProperty(value = "渠道ID")
    private String channelId;

    @ApiModelProperty(value = "渠道支付工具信息")
    private Map<String, Object> apmInfo;

    @ApiModelProperty(value = "渠道支付完成后的跳转路径")
    private String redirectUrl;
}
