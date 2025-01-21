package com.siupay.openapi.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;

import lombok.Data;

/**
 * @program: payment-api
 * @description: 提现订单查询出参
 * @author: Sandy
 **/
@Data
public class PayOutOrderResponseBO {

    /**
     * Payout订单id,前缀pot_
     */
    @ExcelProperty(value = "网关订单号")
    private String payoutTrxnId;

    /**
     * 渠道返回的单号
     */
    @ExcelProperty(value = "渠道订单号")
    private String channelTrackId;

    /**
     * 外部用户ID(ucenter_id)
     */
    @ExcelProperty(value = "用户id")
    private String externalUserId;

    /**
     * 业务类型
     */
    @ExcelProperty(value = "业务类型")
    private String payType = "WITHDRAWAL";

    /**
     * 提现账户id
     */
    @ExcelProperty(value = "提现账户id")
    private String payoutAccountId;

    /**
     * 货币种类
     */
    @ExcelProperty(value = "提现法币")
    private String withdrawCurrency;

    /**
     * 原始提现金额
     */
    @ExcelProperty(value = "提现金额")
    private BigDecimal originalAmount;

    /**
     * 提现金额
     */
    @ExcelIgnore
    private BigDecimal withdrawAmount;

    /**
     * 渠道手续费
     */
    @ExcelProperty(value = "提现平台手续费")
    private BigDecimal platformFee;

    /**
     * 提现方式
     */
    @ExcelProperty(value = "提现方法")
    private String payoutMethod;

    /**
     * 提现通道
     */
    @ExcelProperty(value = "支付渠道")
    private String channelId;

    /**
     * 渠道手续费
     */
    @ExcelProperty(value = "渠道手续费")
    private BigDecimal channelFee;

    /**
     * payout状态
     */
    @ExcelProperty(value = "状态")
    private String status;

    /**
     * 错误码
     */
    @ExcelProperty(value = "错误码及原因")
    private String statusCode;

    /**
     * 创建时间
     */
    @ExcelProperty("订单创建时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private Date created;

    /**
     * 更新日期
     */
    @ExcelProperty("订单最后修改时间")
    @DateTimeFormat(value = "yyyy-MM-dd HH:mm:ss")
    private Date updated;

    /**
     * 费用详情
     */
    @ExcelIgnore
    private String fee;

    /**
     * 商户用户id
     */
    @ExcelIgnore
    private String paymentUserId;

    /**
     * 上游请求单号
     */
    @ExcelIgnore
    private String requestId;

    /**
     * 上游类型
     */
    @ExcelIgnore
    private String requestSystem;

    /**
     * 货币种类
     */
    @ExcelIgnore
    private String originalCurrency;

    /**
     * 状态描述
     */
    @ExcelIgnore
    private String statusMessage;

    /**
     * 请求来源web/ios/andriod或外部系统
     */
    @ExcelIgnore
    private String source;

    /**
     * 查询次次数
     */
    @ExcelIgnore
    private Integer queryCount;

    /**
     * 渠道后续处理依赖的上下文信息
     */
    @ExcelIgnore
    private String channelExtra;

    /**
     * 平台处理上下文信息
     */
    @ExcelIgnore
    private String platformExtra;
}
