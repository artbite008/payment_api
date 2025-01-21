package com.siupay.openapi.bo;

import com.siupay.common.api.dto.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Sucre
 * @date 2021年12月08日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PayoutTransactionQueryRequestBo extends BaseDto {

    /**
     * 法币币种
     */
    private String fiatCurrency;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 分页大小
     */
    private Integer pageSize = 10;
}
