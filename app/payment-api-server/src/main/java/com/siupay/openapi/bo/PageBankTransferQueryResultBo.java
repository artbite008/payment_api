package com.siupay.openapi.bo;

import java.util.Date;
import java.util.Map;

import lombok.Data;

@Data
public class PageBankTransferQueryResultBo {

    /**
     * 支付通道
     */
    private String channelId;

    /**
     * payin订单id_前缀pit
     */
    private String payinTrxnId;

    /**
     * 渠道返回的单号
     */
    private String channelTrackId;

    /**
     * 上分订单号
     */
    private String depositId;

    /**
     * 支付金额
     */
    private String amount;

    /**
     * 交易货币
     */
    private String currency;

    /**
     * 方向,IN：充值，OUT：提现
     */
    private String transactionType;

    /**
     * 付款方名称
     */
    private String payerName;

    /**
     * 付款方BIC
     */
    private String payerBic;

    /**
     * 付款方账号
     */
    private String payerIban;

    /**
     * 清算网络
     */
    private String cleaningNetwork;

    /**
     * 收款方名称
     */
    private String payeeName;

    /**
     * 收款方BIC
     */
    private String payeeBic;

    /**
     * 收款方账号
     */
    private String payeeIban;

    /**
     * description
     */
    private String description;

    /**
     * 交易附言
     */
    private String reference;

    /**
     * payin状态（核销状态）
     */
    private String status;

    /**
     * 错误码（流水状态）
     */
    private String statusCode;

    /**
     * 状态描述
     */
    private String statusMessage;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新日期
     */
    private Date updated;

    /**
     * 渠道后续处理依赖的上下文信息-json
     */
    private String channelExtra;

    /**
     * 渠道后续处理依赖的上下文信息-展示字段
     */
    private Map<String, String> channelExtraDisplay;

    /**
     * 费用详情-json
     */
    private String fee;

    /**
     * 渠道手续费-展示字段
     */
    private String channelFeeDisplay;
}
