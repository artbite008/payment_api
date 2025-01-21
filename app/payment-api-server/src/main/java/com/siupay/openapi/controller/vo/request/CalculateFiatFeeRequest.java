package com.siupay.openapi.controller.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @author Uther.chen
 * @date 2022年04月08日
 */
@Data
@ApiModel("用户算费请求")
public class CalculateFiatFeeRequest {

    @ApiModelProperty("支付方式code")
    @NotBlank(message = "支付方式code不能为空")
    private String paymentMethodCode;

    @ApiModelProperty("交易类型")
    @NotBlank(message = "交易类型不能为空")
    private String tradeType;

    @ApiModelProperty("法币币种")
    @NotBlank(message = "法币币种不能为空")
    private String fiatCurrency;

    @ApiModelProperty("卡id")
    private String cardId;

    @ApiModelProperty("法币交易金额")
    @Min(value = 0,message = "fiatAmount less than 0")
    @Max(value = 1000000000, message = "fiatAmount more than 1000000000")
    private BigDecimal fiatAmount;
}
