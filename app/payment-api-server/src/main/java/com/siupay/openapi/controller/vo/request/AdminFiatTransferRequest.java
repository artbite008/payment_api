package com.siupay.openapi.controller.vo.request;

import com.siupay.common.api.dto.PaymentAmount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Ken.Liu
 * @description
 * @date 2022/5/17
 */
@Data
@ApiModel("法币资金划转")
public class AdminFiatTransferRequest {
    @ApiModelProperty("转账金额")
    @NotNull(message = "transferAmount不能为空")
    private PaymentAmount transferAmount;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("收款方用户id")
    @NotBlank(message = "recOwnerId不能为空")
    private String recOwnerId;

    @ApiModelProperty("收款账户类型")
    private String recAccountType;

    @ApiModelProperty("收款子账户类型")
    private String recTag;

    @ApiModelProperty("付款用户id")
    @NotBlank(message = "payOwnerId不能为空")
    private String payOwnerId;

    @ApiModelProperty("付款账户类型")
    private String payAccountType;

    @ApiModelProperty("付款子账户类型")
    private String payTag;
}

