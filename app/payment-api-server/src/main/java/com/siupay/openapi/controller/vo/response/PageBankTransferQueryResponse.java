package com.siupay.openapi.controller.vo.response;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siupay.common.api.dto.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "交易流水信息")
public class PageBankTransferQueryResponse extends BaseDto {

    @JsonProperty(value = "channel_id")
    @ApiModelProperty(value = "渠道id", name = "channel_id", example = "sepa")
    private String channelId;

    @JsonProperty(value = "payin_trxn_id")
    @ApiModelProperty(value = "支付订单号", name = "payin_trxn_id")
    private String payinTrxnId;

    @JsonProperty(value = "channel_track_id")
    @ApiModelProperty(value = "渠道订单号", name = "channel_track_id")
    private String channelTrackId;

    @JsonProperty(value = "deposit_id")
    @ApiModelProperty(value = "上分订单号", name = "deposit_id")
    private String depositId;

    @JsonProperty(value = "amount")
    @ApiModelProperty(value = "金额", name = "amount")
    private String amount;

    @JsonProperty(value = "currency")
    @ApiModelProperty(value = "币种", name = "currency")
    private String currency;

    @JsonProperty(value = "transaction_type")
    @ApiModelProperty(value = "方向,IN：充值，OUT：提现 ", name = "transaction_type")
    private String transactionType;

    @JsonProperty(value = "reference")
    @ApiModelProperty(value = "交易附言", name = "reference")
    private String reference;

    @JsonProperty(value = "status")
    @ApiModelProperty(value = "流水状态", name = "status")
    private String status;

    @JsonProperty(value = "status_code")
    @ApiModelProperty(value = "错误码", name = "status_code")
    private String statusCode;

    @JsonProperty(value = "status_message")
    @ApiModelProperty(value = "异常原因", name = "status_message")
    private String statusMessage;

    @JsonProperty(value = "created")
    @ApiModelProperty(value = "创建时间", name = "created", example = "100000")
    private Date created;

    @JsonProperty(value = "updated")
    @ApiModelProperty(value = "最后更新时间", name = "updated", example = "10000")
    private Date updated;

    @JsonProperty(value = "payer_name")
    @ApiModelProperty(value = "付款方名称")
    private String payerName;

    @JsonProperty(value = "payer_bic")
    @ApiModelProperty(value = "付款方BIC")
    private String payerBic;

    @JsonProperty(value = "payer_iban")
    @ApiModelProperty(value = "付款方账号")
    private String payerIban;

    @JsonProperty(value = "cleaning_network")
    @ApiModelProperty(value = "清算网络")
    private String cleaningNetwork;

    @JsonProperty(value = "payee_name")
    @ApiModelProperty(value = "收款方名称")
    private String payeeName;

    @JsonProperty(value = "payee_bic")
    @ApiModelProperty(value = "收款方BIC")
    private String payeeBic;

    @JsonProperty(value = "payee_iban")
    @ApiModelProperty(value = "收款方账号")
    private String payeeIban;

    @JsonProperty(value = "description")
    @ApiModelProperty(value = "description")
    private String description;

    @JsonProperty(value = "channel_extra")
    @ApiModelProperty(value = "渠道附加信息")
    private Map<String, String> channelExtraDisplay;

    @JsonProperty(value = "channel_fee")
    @ApiModelProperty(value = "渠道手续费")
    private String channelFeeDisplay;
}
