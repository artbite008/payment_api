package com.siupay.openapi.v1.service;

import com.siupay.common.api.dto.response.BasePaginationResponse;
import com.siupay.openapi.bo.OrderHistoryRequestBo;
import com.siupay.openapi.bo.OrderHistoryResponseBo;

public interface OrderService {
    /**
     * 获取历史订单记录
     *
     * @param orderHistoryBo
     * @return
     */
    BasePaginationResponse<OrderHistoryResponseBo> getOrderHistory(OrderHistoryRequestBo orderHistoryBo);

}
