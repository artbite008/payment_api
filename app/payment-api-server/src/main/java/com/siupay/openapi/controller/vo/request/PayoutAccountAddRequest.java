package com.siupay.openapi.controller.vo.request;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author minn
 * @description
 * @date 2021/11/28
 */
@ApiModel("新增提现账户请求信息")
@Data
public class PayoutAccountAddRequest {

    @ApiModelProperty(value = "渠道id", notes = "sepa_transactive,capitual", required = true)
    @NotBlank(message = "The channel_id format is incorrect.")
    @JsonProperty(value = "channel_id")
    private String channelId;

    @ApiModelProperty(value = "法币币种", required = true)
    @Pattern(regexp = "[A-Z]{1,4}", message = "The currency format is incorrect.")
    private String currency;

    @ApiModelProperty(value = "账号名称")
    @Size(max = 128, message = "The account_name format is incorrect.")
    @JsonProperty(value = "account_name")
    private String accountName;

    @ApiModelProperty(value = "账户编号", notes = "iban|Account|PIX Key", required = true)
    private String iban;

    @ApiModelProperty(value = "银行名称/银行简称")
    @Size(max = 128, message = "The bank_name format is incorrect.")
    @JsonProperty(value = "bank_name")
    private String bankName;

    @ApiModelProperty(value = "银行路由编号")
    @Pattern(regexp = "[a-zA-Z0-9]{8,11}",
            message = "BIC should be made up of 8 to 11 characters, without spaces and special characters.")
    private String bic;

    @ApiModelProperty(value = "卡片别名")
    @Size(max = 20, message = "The discription format is incorrect.")
    private String discription;

    @ApiModelProperty(value = "用户电子邮箱")
    @JsonProperty(value = "payee_email")
    private String payeeEmail;

    @ApiModelProperty(value = "渠道账户附加信息", notes = "账户类型(account_type): PIX, TED;" + "键类型(key_type): cpf, bankaccount;"
            + "个人税号(cpf);" + "支行编号(branch);" + "是否为储蓄账户(is_savings): true-Savings Account, false-Checking Account;")
    private Map<String, Object> extra;

    @ApiModelProperty("订单来源（iOS WEB Android）")
    private String clientFrom = "WEB";

    @ApiModelProperty("设备指纹")
    @JsonProperty(value = "finger_id")
    private String fingerId;

}
