package com.siupay.openapi.bo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author uther.chen
 * @description: 查询订单列表入参
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderHistoryRequestBo extends BasePageBo {
    /**
     * value = "订单类型",
     * example = "WITHDRAW/RECHARGE"
     */
    private String orderType;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * value = "法币",
     * example = "CNY/USD"
     */
    private String fiat;

    private String paymentMethod;

    private List<String> orderStatus;

    private String cryptoCurrency;

}
