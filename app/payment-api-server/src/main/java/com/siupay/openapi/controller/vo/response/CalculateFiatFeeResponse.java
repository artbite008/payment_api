package com.siupay.openapi.controller.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Uther.chen
 * @date 2022年04月08日
 */
@Data
@ApiModel("用户算费响应")
public class CalculateFiatFeeResponse {
    @ApiModelProperty("算费策略id")
    private String feeId;

    @ApiModelProperty("费率")
    private BigDecimal fixedFeeRate;

    @ApiModelProperty("固定费用")
    private BigDecimal fixedFee;

    @ApiModelProperty("最高费用")
    private BigDecimal maxFee;

    @ApiModelProperty("最低费用")
    private BigDecimal minFee;

    @ApiModelProperty("总费用")
    private BigDecimal feeAmount;

    @ApiModelProperty("费率计算msg")
    private String feeMsg;
}
