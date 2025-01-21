package com.siupay.openapi.v1.controller;

import com.siupay.common.api.dto.response.GenericResponse;
import com.siupay.common.api.utils.UserContextUtils;
import com.siupay.instrument.dto.PaymentInstrumentBO;
import com.siupay.instrument.dto.reponse.PaymentInstrumentBindResponse;
import com.siupay.openapi.v1.bo.request.CardBindingUpdateRequest;
import com.siupay.openapi.v1.bo.request.PaymentApiInstrumentQuery;
import com.siupay.openapi.v1.bo.response.ChannelCardInfoResponse;
import com.siupay.openapi.v1.service.CardFacadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "卡服务列表")
@Slf4j
@CrossOrigin
public class CardFacadeController {

    @Autowired
    private CardFacadeService cardService;

    @PostMapping(value = {"/pmtapi/v1/card/detach"})
    public GenericResponse<Boolean> deleteCard(@RequestBody @Validated CardBindingUpdateRequest request) {
        GenericResponse<Boolean> result = cardService.deleteCard(request, UserContextUtils.getUserId());
        return result;
    }

    @GetMapping(value = {"/pmtapi/v1/cards"})
    public GenericResponse<List<ChannelCardInfoResponse>> listCardByUserId(
            @RequestParam(required = true) @ApiParam(value = "支付方式") String channelType) {
        GenericResponse<List<ChannelCardInfoResponse>> result =cardService.listCardByUserId(channelType, UserContextUtils.getUserId());
        return result;
    }

    @PostMapping(value = {"/pmtapi/v1/user/card_bind_status"})
    public GenericResponse<PaymentInstrumentBindResponse> cardBindCheck(@RequestBody PaymentApiInstrumentQuery query) {
        return cardService.cardBindCheck(query);
    }

    @PostMapping(value = {"/pmtapi/v1/card/list"})
    public GenericResponse<List<PaymentInstrumentBO>> cardList(@RequestBody PaymentApiInstrumentQuery query) {
        return cardService.cardList(query);
    }

    @PostMapping(value = {"/pmtapi/v1/card/set_as_default"})
    public GenericResponse<Boolean> updateBinding(@RequestBody @Validated CardBindingUpdateRequest request) {
        GenericResponse<Boolean> result = cardService.updateBinding(request);
        return result;
    }

    @GetMapping(value = {"/pmtapi/v1/bind/limit"})
    public GenericResponse<Integer> bindLimit() {
        GenericResponse<Integer> result =cardService.bindLimit();
        return result;
    }
}
