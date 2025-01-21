package com.siupay.openapi.v1.transformer;

import com.siupay.openapi.v1.bo.response.TradeOrderDetailsResponse;
import org.springframework.beans.BeanUtils;

public class DepositTradeOrderDetailsResponseTransformer {
    public static TradeOrderDetailsResponse toV1(TradeOrderDetailsResponse depositTrade){
        TradeOrderDetailsResponse target = new TradeOrderDetailsResponse();
        BeanUtils.copyProperties(depositTrade, target);
        return target;
    }
}
