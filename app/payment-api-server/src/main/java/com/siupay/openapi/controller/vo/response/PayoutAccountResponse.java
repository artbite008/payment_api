package com.siupay.openapi.controller.vo.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

/**
 * @author minn
 * @description
 * @date 2021/11/28
 */
@Data
@NoArgsConstructor
@ApiModel(value = "提现账户信息")
public class PayoutAccountResponse {

    @JsonProperty(value = "payout_account_id")
    @ApiModelProperty(value = "收款账户id")
    private String payoutAccountId;

    @JsonProperty(value = "channel_id")
    @ApiModelProperty(value = "渠道id")
    private String channelId;

    @ApiModelProperty(value = "法币币种")
    private String currency;

    @JsonProperty(value = "bank_name")
    @ApiModelProperty(value = "银行名称")
    private String bankName;

    @ApiModelProperty(value = "卡片别名")
    private String discription;

    @ApiModelProperty(value = "账号")
    private String iban;

    @JsonProperty(value = "iban_last4")
    @ApiModelProperty(value = "账户尾号")
    private String ibanLast4;

    @ApiModelProperty(value = "用户电子邮箱")
    @Pattern(regexp = "[a-zA-Z0-9_-]+", message = "user payee email format is incorrect.")
    @JsonProperty(value = "payee_email")
    private String payeeEmail;

    @ApiModelProperty(value = "账户状态")
    private String status;

    @ApiModelProperty(value = "渠道账户附加信息",
            notes = "账户类型(account_type): PIX, TED;" + "键类型(key_type): cpf, bankaccount;" + "个人税号(cpf);"
                    + "支行编号(branch);" + "是否为储蓄账户(is_savings): true-Savings Account, false-Checking Account;"
                    + "银行简称(short_name)")
    private Map<String, Object> extra;

    private Boolean isAllowed;
}
