package com.siupay.openapi.bo;

import lombok.Data;

/**
 * @author Sucre
 * @date 2021年12月08日
 */
@Data
public class PayoutTransactionDetailsRequestBo {

    /**
     * 订单id
     */
    private String payoutTrxnId;
}
