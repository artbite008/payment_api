package com.siupay.openapi.v1.bo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel("查询订单状态")
public class QueryPayinOrderResponse {

	@ApiModelProperty(value = "订单明细id")
	private String id;

	@ApiModelProperty(value = "主订单id")
	private String orderId;

	@ApiModelProperty(value = "第三方渠道订单id")
	private String channelBizId;

	@ApiModelProperty(value = "法币币种", example = "CNY")
	private String fiatCurrency;

	@ApiModelProperty(value = "交易金额", example = "100.00")
	//@JsonSerialize(using = FiatPrecisionConverter.class)
	private BigDecimal rechargeAmount;

	@ApiModelProperty(value = "扣除手续费后实际交易金额", example = "90.00")
	//@JsonSerialize(using = FiatPrecisionConverter.class)
	private BigDecimal accountRechargebackAmount;

	@ApiModelProperty(value = "订单状态")
	private String status;

	@ApiModelProperty(value = "渠道", example = "checkout")
	private String channelName;

	@ApiModelProperty(value = "渠道类型", example = "wallet/bank")
	private String channelType;

	@ApiModelProperty(value = "手续费总额手续费", example = "10.00")
	//@JsonSerialize(using = FiatPrecisionConverter.class)
	private BigDecimal totalFeeAmount;

	@ApiModelProperty(value = "渠道手续费率")
	//@JsonSerialize(using = FiatPrecisionConverter.class)
	private BigDecimal channelFee;

	@ApiModelProperty(value = "渠道手续费")
	//@JsonSerialize(using = FiatPrecisionConverter.class)
	private BigDecimal channelFeeAmount;

	@ApiModelProperty(value = "平台手续费率")
	//@JsonSerialize(using = FiatPrecisionConverter.class)
	private BigDecimal platformFee;

	@ApiModelProperty(value = "平台手续费")
	//@JsonSerialize(using = FiatPrecisionConverter.class)
	private BigDecimal platformFeeAmount;

	@ApiModelProperty(value = "订单创建时间")
	private Date createdAt;
}
