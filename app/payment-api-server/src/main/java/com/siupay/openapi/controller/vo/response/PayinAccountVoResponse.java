package com.siupay.openapi.controller.vo.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siupay.common.api.dto.BaseDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PayinAccountVoResponse  extends BaseDto {


    @ApiModelProperty(value = "主键")
    @JsonProperty(value = "payin_account_id")
    private String payinAccountId;

    /**
     * 系统用户id
     */
    @ApiModelProperty(value = "系统用户id", name = "user_id")
    @JsonProperty(value = "user_id")
    private String externalUserId;
    /**
     * 收款账户名称
     */
    @ApiModelProperty(value = "收款账户名称", name = "company_name")
    @JsonProperty(value = "company_name")
    private String companyName;
    /**
     * 收款账户地址
     */
    @ApiModelProperty(value = "收款账户地址", name = "company_address")
    @JsonProperty(value = "company_address")
    private String companyAddress;
    /**
     * 渠道编码
     */
    @ApiModelProperty(value = "渠道编码", name = "channel_id")
    @JsonProperty(value = "channel_id")
    private String channelId;
    /**
     * 银行名称
     */
    @ApiModelProperty(value = "银行名称", name = "bank_name")
    @JsonProperty(value = "bank_name")
    private String bankName;
    /**
     * 银行地址
     */
    @ApiModelProperty(value = "银行地址", name = "bank_address")
    @JsonProperty(value = "bank_address")
    private String bankAddress;
    /**
     * 银行账号IBAN
     */
    @ApiModelProperty(value = "银行账号IBAN", name = "iban")
    @JsonProperty(value = "iban")
    private String iban;
    /**
     * 银行BIC
     */
    @ApiModelProperty(value = "银行BIC", name = "bic")
    @JsonProperty(value = "bic")
    private String bic;
    /**
     * 用户附言
     */
    @ApiModelProperty(value = "用户附言", name = "reference")
    @JsonProperty(value = "reference")
    private String reference;
    /**
     * 创建时间 UTC时间
     */
    @ApiModelProperty(value = "", name = "", example = "100000")
    @JsonProperty(value = "created")
    private Long created;

    @ApiModelProperty(value = "账户附加信息")
    @JsonProperty(value = "extra_display")
    private Map<String, Object> extraDisplay;

    @ApiModelProperty(value = "账户验证状态")
    @JsonProperty(value = "verify_status")
    private String verifyStatus;
}
