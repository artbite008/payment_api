package com.siupay.openapi.controller.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: payment-api
 * @description:
 * @author: Sandy
 **/
@Data
@ApiModel("提现账户查询入参")
public class PayOutAccountRequest extends BasePageRequest {
    /**
     * 用户userId
     */
    @ApiModelProperty(value = "用户userId", hidden = true)
    @JsonProperty(value = "user_id")
    private String userId;

    /**
     * 提现账户id
     */
    @ApiModelProperty(value = "提现账户id")
    @JsonProperty(value = "payment_account_id")
    private String paymentAccountId;

    /**
     * 渠道类型
     */
    @ApiModelProperty(value = "渠道类型")
    @JsonProperty(value = "channel_id")
    private String channelId;
}
