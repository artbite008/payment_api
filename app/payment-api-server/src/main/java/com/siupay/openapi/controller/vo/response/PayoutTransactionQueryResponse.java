package com.siupay.openapi.controller.vo.response;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siupay.common.api.dto.PaymentAmount;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Sucre
 * @date 2021年12月08日
 */
@Data
@ApiModel("提现流水列表响应")
public class PayoutTransactionQueryResponse {

    /**
     * 提现流水订单id
     */
    @ApiModelProperty("提现流水订单id")
    @JsonProperty("payout_trxn_id")
    private String payoutTrxnId;

    /**
     * 提现方式
     */
    @ApiModelProperty("提现方式")
    @JsonProperty("withdraw_type")
    private String withdrawType;

    /**
     * 提现金额
     */
    @ApiModelProperty("提现金额")
    @JsonProperty("withdraw_amount")
    private PaymentAmount withdrawAmount;

    /**
     * 手续费
     */
    @ApiModelProperty("手续费")
    @JsonProperty("fee_amount")
    private PaymentAmount feeAmount;

    /**
     * 提现状态
     */
    @ApiModelProperty("提现状态")
    private String status;

    /**
     * iban后4位
     */
    @ApiModelProperty("iban后4位")
    @JsonProperty("iban_last4")
    private String ibanLast4;

    /**
     * 银行名称
     */
    @ApiModelProperty("银行名称")
    @JsonProperty("bank_name")
    private String bankName;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String discription;

    /**
     * 转账信息
     */
    @ApiModelProperty("转账信息")
    @JsonProperty("payout_account_info")
    private String payoutAccountInfo;

    /**
     * 状态描述
     */
    @ApiModelProperty("状态描述")
    @JsonProperty("status_message")
    private String statusMessage;

    /**
     * 创建日期
     */
    @ApiModelProperty("创建日期")
    private Date created;

    @JsonProperty(value = "channel_id")
    @ApiModelProperty(value = "渠道id")
    private String channelId;

    @ApiModelProperty("iban")
    private String iban;

    @ApiModelProperty(value = "渠道账户附加信息", notes = "账户类型(account_type): PIX, TED;" +
            "键类型(key_type): cpf, bankaccount;" +
            "个人税号(cpf);")
    @JsonProperty("account_extra")
    private Map<String, Object> accountExtra;
}
