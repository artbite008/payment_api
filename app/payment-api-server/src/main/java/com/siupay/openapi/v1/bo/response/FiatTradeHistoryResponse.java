package com.siupay.openapi.v1.bo.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.siupay.openapi.v1.util.BigDecimalCommonConverter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel("法币余额交易订单历史记录查询")
public class FiatTradeHistoryResponse {

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    private String orderId;

    /**
     * 数字货币币种
     */
    @ApiModelProperty(value = "数字货币币种")
    private String cryptoCurrency;

    /**
     * 法币币种
     */
    @ApiModelProperty(value = "法币币种")
    private String fiatCurrency;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private String paymentName;

    /**
     * 法币交易金额
     */
    @ApiModelProperty(value = "法币交易金额")
    private BigDecimal fiatAmount;

    /**
     * 数字货币交易金额
     */
    @ApiModelProperty(value = "数字货币交易金额")
    @JsonSerialize(using = BigDecimalCommonConverter.class)
    private BigDecimal cryptoQuantity;

    /**
     * 订单数据来源(1:余额买币, 2:信用卡买币)
     */
    @ApiModelProperty(value = "订单数据来源(1:余额买币, 2:信用卡买币)")
    private Integer bizSource;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单id")
    private Integer orderStatus;

    /**
     * 订单失败原因, 错误码用于前端国际化
     */
    @ApiModelProperty(value = "订单id,0.进行中1.成功2.失败3.异常)", example = "0")
    private String displayErrorCode;

    /**
     * 展示报价
     */
    @ApiModelProperty(value = "用户报价", example = "1.3")
    private BigDecimal price;

    /**
     * 手续费用
     */
    @ApiModelProperty(value = "手续费用")
    private BigDecimal fee = BigDecimal.ZERO;

    /**
     * 订单充值类型
     */
    @ApiModelProperty(value = "订单充值类型", example = "0:充值, 1:出售")
    private Integer refType;

    /**
     * side / buy
     */
    @ApiModelProperty(value = "side", example = "sell/buy")
    private String side;

    /**
     * 订单创建时间
     */
    @ApiModelProperty(value = "订单创建时间")
    private Date createdAt;
}
