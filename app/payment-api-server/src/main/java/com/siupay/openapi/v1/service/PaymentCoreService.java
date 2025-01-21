package com.siupay.openapi.v1.service;

import com.siupay.common.api.dto.response.GenericResponse;
import com.siupay.openapi.v1.bo.request.*;
import com.siupay.openapi.v1.bo.response.*;

public interface PaymentCoreService {

    GenericResponse<ChannelCardInfoResponse> authAndSaveCardInfo(BindCardInfoRequest cardInfoRequest, String userId);

    GenericResponse<CreditCardPreRechargeResponse> preRecharge(BasePrRechargeRequest basePrRechargeRequest);

    GenericResponse<OrderConfirmResponse> orderConfirm(OrderConfirmRequest orderConfirmRequest);

    GenericResponse<PreTradeResponse> preTrade(BuyCryptoBasedBankCardRequest request);

    GenericResponse<TradeOrderDetailsResponse> tradeConfirm(TradeConfirmRequest request);

    GenericResponse<QueryPayinOrderResponse> queryByPayinOrderId(String orderId);

}
