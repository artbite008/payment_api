package com.siupay.openapi.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author uther.chen
 * @description 用户算费响应
 */
@Data
public class CalculateFeeResponseBo {
    /**
     * 算费策略id
     */
    private String feeId;

    /**
     * 费率
     */
    private BigDecimal fixedFeeRate;

    /**
     * 固定费用
     */
    private BigDecimal fixedFee;

    /**
     * 最高费用
     */
    private BigDecimal maxFee;

    /**
     * 最低费用
     */
    private BigDecimal minFee;

    /**
     * 总费用
     */
    private BigDecimal feeAmount;

    /**
     * 费率计算msg
     */
    private String feeMsg;
}
