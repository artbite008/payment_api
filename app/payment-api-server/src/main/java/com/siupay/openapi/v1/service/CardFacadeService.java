package com.siupay.openapi.v1.service;

import com.siupay.common.api.dto.response.GenericResponse;
import com.siupay.instrument.dto.PaymentInstrumentBO;
import com.siupay.instrument.dto.reponse.PaymentInstrumentBindResponse;
import com.siupay.openapi.v1.bo.request.CardBindingUpdateRequest;
import com.siupay.openapi.v1.bo.request.PaymentApiInstrumentQuery;
import com.siupay.openapi.v1.bo.response.ChannelCardInfoResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CardFacadeService {
    GenericResponse<Boolean> deleteCard(CardBindingUpdateRequest request, String uid);
    GenericResponse<List<ChannelCardInfoResponse>> listCardByUserId(String channelType, String uid);
    GenericResponse<Boolean> updateBinding(@RequestBody @Validated CardBindingUpdateRequest request);
    GenericResponse<Integer> bindLimit();
    // TODO Demo 先暂时使用instrument 的DTO返回到前端
    GenericResponse<PaymentInstrumentBindResponse> cardBindCheck (PaymentApiInstrumentQuery query);
    // TODO Demo 先暂时使用instrument 的DTO返回到前端
    GenericResponse<List<PaymentInstrumentBO>> cardList (PaymentApiInstrumentQuery query);
}
