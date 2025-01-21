package com.siupay.openapi.controller.vo.response;

import java.util.Date;

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
@ApiModel("提现流水详情")
public class PayOutTransactionDetailsResponse {

    @ApiModelProperty("提现流水订单id")
    @JsonProperty("payout_trxn_id")
    private String payoutTrxnId;

    @ApiModelProperty("创建日期")
    private Date created;

    @ApiModelProperty("提现金额")
    @JsonProperty("withdraw_amount")
    private PaymentAmount withdrawAmount;

    @ApiModelProperty("手续费")
    @JsonProperty("fee_amount")
    private PaymentAmount feeAmount;

    @ApiModelProperty("支付方式名称")
    @JsonProperty("payment_method_name")
    private String paymentMethodName;

    @ApiModelProperty("收款账户信息")
    @JsonProperty("payout_account_info")
    private String payoutAccountInfo;
}
