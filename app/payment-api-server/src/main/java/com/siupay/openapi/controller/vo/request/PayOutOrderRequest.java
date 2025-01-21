package com.siupay.openapi.controller.vo.request;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @program: payment-api
 * @description: 提现订单查询入参
 * @author: Sandy
 **/
@Data
@ApiModel("提现账户查询入参")
public class PayOutOrderRequest extends BasePageRequest {

    /**
     * 支付订单号
     */
    private String payout_trxn_id;

    /**
     * 渠道订单号
     */
    private String channel_track_id;

    /**
     * 用户userId
     */
    private String user_id;

    /**
     * 订单开始时间
     */
    private Date created_at;

    /**
     * 订单结束时间
     */
    private Date end_at;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 法币种类
     */
    private String fiat;
}
