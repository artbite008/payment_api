package com.siupay.openapi.controller.vo.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.siupay.openapi.v1.util.FiatPrecisionConverter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author uther.chen
 * @description: 订单充值列表
 */
@ApiModel("订单充值列表")
@Data
public class OrderHistoryResponse {
    @ApiModelProperty(value = "订单ID")
    private String orderId;

    @ApiModelProperty(value = "币种", example = "CNY/USD")
    private String coin;

    @ApiModelProperty(value = "交易数量", example = "3.33")
    @JsonSerialize(using = FiatPrecisionConverter.class)
    private BigDecimal amount;

    @ApiModelProperty(value = "订单状态", example = "0/1/2 (进行中/充值成功/充值失败)")
    private Integer status;

    @ApiModelProperty(value = "申诉状态", example = "0/1/2 (未申诉/申诉中/申诉结束)")
    private Integer appealStatus;

    @ApiModelProperty(value = "渠道类型", example = "BANK TRANSFER")
    private String channelType;

    @ApiModelProperty(value = "手续费总额", example = "3.3")
    @JsonSerialize(using = FiatPrecisionConverter.class)
    private BigDecimal totalFee;

    @ApiModelProperty(value = "订单创建时间")
    private Date createTime;
}
