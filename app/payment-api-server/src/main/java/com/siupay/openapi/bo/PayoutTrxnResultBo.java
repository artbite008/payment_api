package com.siupay.openapi.bo;

import lombok.Data;

/**
 * @author minn
 * @description
 * @date 2021/11/30
 */
@Data
public class PayoutTrxnResultBo {

    /**
     * 提现订单id
     */
    private String payoutTrxnId;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 失败原因
     */
    private String statusMessage;


}
