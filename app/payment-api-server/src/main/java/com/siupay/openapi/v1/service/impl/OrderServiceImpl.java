package com.siupay.openapi.v1.service.impl;

import com.github.pagehelper.Page;
import com.siupay.common.api.dto.response.BasePaginationResponse;
import com.siupay.openapi.bo.OrderHistoryRequestBo;
import com.siupay.openapi.bo.OrderHistoryResponseBo;
import com.siupay.openapi.v1.constants.Constants;
import com.siupay.openapi.v1.persistence.entity.RechargeOrderDetailsDO;
import com.siupay.openapi.v1.persistence.entity.RechargeOrderDetailsDOExample;
import com.siupay.openapi.v1.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.pagehelper.page.PageMethod.startPage;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {


    @Override
    public BasePaginationResponse<OrderHistoryResponseBo> getOrderHistory(OrderHistoryRequestBo orderHistoryBo) {
        if (orderHistoryBo.getOrderType().equals(Constants.WITHDRAW)) {
            return null;
        } else {
            RechargeOrderDetailsDOExample example = new RechargeOrderDetailsDOExample();
            RechargeOrderDetailsDOExample.Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(orderHistoryBo.getUserId());
            criteria.andOrderSourceNotEqualTo("FAIT_TRADE");
            if (StringUtils.isNotEmpty(orderHistoryBo.getOrderId())) {
                criteria.andOrderIdEqualTo(orderHistoryBo.getOrderId());
            }
            if (StringUtils.isNotEmpty(orderHistoryBo.getFiat())) {
                criteria.andFiatCurrencyEqualTo(orderHistoryBo.getFiat());
            }
            example.setOrderByClause("created_at DESC");
            startPage(orderHistoryBo.getPageNum(), orderHistoryBo.getPageSize());
            Page<RechargeOrderDetailsDO> rechargeOrderDetailsDos =
                    new Page<>() ;
            List<OrderHistoryResponseBo> response = buildRechargeResponse(rechargeOrderDetailsDos);
            return new BasePaginationResponse<OrderHistoryResponseBo>(orderHistoryBo.getPageNum(), orderHistoryBo.getPageSize(),
                    rechargeOrderDetailsDos.getTotal(), response);
        }
    }

    private List<OrderHistoryResponseBo> buildRechargeResponse(
            List<RechargeOrderDetailsDO> rechargeOrderDOList) {
        return rechargeOrderDOList.stream()
                .map(rechargeOrder -> OrderHistoryResponseBo.builder()
                        .amount(rechargeOrder.getRechargeAmount())
                        .appealStatus(0)
                        .coin(rechargeOrder.getFiatCurrency())
                        .createTime(rechargeOrder.getCreatedAt())
                        .orderId(rechargeOrder.getOrderId())
                        .status(rechargeOrder.getDetailsStatus())
                        .channelType(rechargeOrder.getChannelType())
                        .channelName(rechargeOrder.getChannelName())
                        .totalFee(rechargeOrder.getPlatformFeeAmount())
                        .build())
                .collect(Collectors.toList());
    }

}
