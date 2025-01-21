package com.siupay.openapi.bo;

import java.util.List;

import lombok.Data;

@Data
public class PageBankTransferQueryParamBo {

    /**
     * 渠道订单号
     */
    private String channelTrackId;
    /**
     * 支付订单号
     */
    private String payinTrxnId;

    /**
     * 上分订单号
     */
    private String depositId;

    /**
     * 渠道编码
     */
    private String channelId;

    /**
     * 交易附言
     */
    private String reference;

    /**
     * 描述，可能的交易附言
     */
    private String description;

    /**
     * 收(付)款放账号
     */
    private String iban;

    /**
     * 创建时间
     */
    private Long created;

    /**
     * 核销状态 
     * <p>
     * pending → 未核销
     * <p>
     * processing →已核销，未上分
     * <p>
     * Succeeded → 已核销，已上分
     * <p>
     * Validation_Failed → 核销异常
     * <p>
     * Manual_Refunded → 人工退款
     * <p>
     * Expired → 订单过期
     */
    private List<String> status;

    /**
     * 创建时间
     */
    private Long startCreated;
    private Long endCreated;
    private int pageNum;
    private int pageSize;
}
