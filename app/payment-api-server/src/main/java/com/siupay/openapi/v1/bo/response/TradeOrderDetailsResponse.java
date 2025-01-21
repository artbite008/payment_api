package com.siupay.openapi.v1.bo.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.siupay.openapi.v1.util.BigDecimalCommonConverter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel("订单详情返回")
public class TradeOrderDetailsResponse {

    @ApiModelProperty("订单id")
    private String orderId;
    @ApiModelProperty("充值订单id")
    private String rechargeOrderId;
    @ApiModelProperty("数字货币币种")
    private String cryptoCurrency;
    @ApiModelProperty("法币币种")
    private String fiatCurrency;
    @ApiModelProperty("交易类型")
    private Integer tradeType;
    @ApiModelProperty("支付方式")
    private String paymentName;
    @ApiModelProperty("绑定卡号")
    private String bindingCard;
    @ApiModelProperty("法币交易金额")
    private BigDecimal fiatAmount;
    @ApiModelProperty("数字货币交易金额")
    @JsonSerialize(using = BigDecimalCommonConverter.class)
    private BigDecimal cryptoQuantity;
    @ApiModelProperty("订单数据来源(1:余额买币, 2:信用卡买币)")
    private Integer bizSource;
    @ApiModelProperty("订单状态(0.进行中1.成功2.失败3.异常))")
    private Integer orderStatus;
    @ApiModelProperty("订单失败错误码, 错误码用于前端国际化")
    private String displayErrorCode;
    @ApiModelProperty("订单失败原因")
    private String displayErrorMsg;
    @ApiModelProperty("手续费用")
    private BigDecimal fee;
    @ApiModelProperty(
            value = "用户报价",
            example = "1.3"
    )
    private BigDecimal price;
    @ApiModelProperty("发卡行名称")
    private String issuingBank;
    @ApiModelProperty("订单创建时间")
    private Date createdAt;
}
