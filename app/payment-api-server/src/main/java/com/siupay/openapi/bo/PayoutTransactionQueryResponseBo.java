package com.siupay.openapi.bo;

import java.util.Date;
import java.util.Map;

import com.siupay.common.api.dto.PaymentAmount;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Sucre
 * @date 2021年12月08日
 */
@Data
@ApiModel("提现流水列表响应")
public class PayoutTransactionQueryResponseBo {

    /**
     * 提现流水订单id
     */
    private String payoutTrxnId;

    /**
     * 提现方式
     */
    private String withdrawType;

    /**
     * 提现金额
     */
    private PaymentAmount withdrawAmount;

    /**
     * 手续费
     */
    private PaymentAmount feeAmount;

    /**
     * 提现状态
     */
    private String status;

    /**
     * iban后4位
     */
    private String ibanLast4;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 描述
     */
    private String discription;

    /**
     * 转账信息
     */
    private String payoutAccountInfo;

    /**
     * 状态描述
     */
    private String statusMessage;

    /**
     * 创建日期
     */
    private Date created;

    private String channelId;

    private String iban;

    /**
     * 渠道账户附加信息
     */
    private Map<String, Object> accountExtra;
}
