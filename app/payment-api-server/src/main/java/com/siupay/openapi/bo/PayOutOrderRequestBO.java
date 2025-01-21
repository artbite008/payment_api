package com.siupay.openapi.bo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: payment-api
 * @description: 提现订单查询入参
 * @author: Sandy
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayOutOrderRequestBO extends BasePageBo {

    /**
     * 支付订单号
     */
    private String payOutTrxnId;

    /**
     * 渠道订单号
     */
    private String channelTrackId;

    /**
     * 用户userId
     */
    private String userId;

    /**
     * 订单开始时间
     */
    private Date createdAt;

    /**
     * 订单结束时间
     */
    private Date endAt;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 法币种类
     */
    private String fiat;
}
