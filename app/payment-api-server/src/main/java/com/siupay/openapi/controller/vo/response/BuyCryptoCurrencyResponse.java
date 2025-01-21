package com.siupay.openapi.controller.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BuyCryptoCurrencyResponse {
    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "法币币种", example = "CNY")
    private String fiatCurrency;

    @ApiModelProperty(value = "数字货币种类", example = "USDT")
    private String cryptoCurrency;

    @ApiModelProperty(value = "法币交易金额", example = "100.00")
    private BigDecimal fiatAmount;

    @ApiModelProperty(value = "数字货币交易数量", example = "100.000000")
    private BigDecimal cryptoQuantity;

    @ApiModelProperty(value = "展示报价", example = "11.000000")
    private BigDecimal offerQuotation;

    @ApiModelProperty(value = "参考报价", example = "10.000000")
    private BigDecimal referenceQuotation;

    @ApiModelProperty(value = "业务来源", example = "0.余额买币1.信用卡买币")
    private Integer bizSource;

    @ApiModelProperty(value = "订单状态", example = "0.进行中1.成功2.失败 3.异常")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单异常错误码")
    private String displayErrorCode;

    @ApiModelProperty(value = "订单异常描述")
    private String displayErrorMsg;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;
}
