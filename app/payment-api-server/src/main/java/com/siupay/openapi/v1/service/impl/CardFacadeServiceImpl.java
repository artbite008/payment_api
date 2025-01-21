package com.siupay.openapi.v1.service.impl;

import com.siupay.common.api.dto.response.GenericResponse;
import com.siupay.common.api.exception.ErrorCode;
import com.siupay.common.api.exception.PaymentError;
import com.siupay.common.lib.enums.PaymentMethod;
import com.siupay.common.lib.json.JsonUtils;
import com.siupay.instrument.PaymentInstrumentApi;
import com.siupay.instrument.PaymentInstrumentValidatorApi;
import com.siupay.instrument.dto.PaymentInstrumentBO;
import com.siupay.instrument.dto.reponse.PaymentInstrumentBindResponse;
import com.siupay.instrument.dto.request.PaymentInstrumentCommonQuery;
import com.siupay.instrument.dto.request.PaymentInstrumentQuery;
import com.siupay.instrument.dto.request.PaymentInstrumentUpdate;
import com.siupay.instrument.enums.BindStatus;
import com.siupay.openapi.v1.bo.request.CardBindingUpdateRequest;
import com.siupay.openapi.v1.bo.request.PaymentApiInstrumentQuery;
import com.siupay.openapi.v1.bo.response.ChannelCardInfoResponse;
import com.siupay.openapi.v1.constants.DictCode;
import com.siupay.openapi.v1.service.CardFacadeService;
import com.siupay.openapi.v1.service.CommonService;
import com.siupay.openapi.v1.transformer.PaymentInstrumentTransformer;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CardFacadeServiceImpl implements CardFacadeService {
    @Autowired
    private PaymentInstrumentApi paymentInstrumentApi;

    @Autowired
    private PaymentInstrumentValidatorApi validatorApi;

    @Autowired
    private CommonService commonService;

    @Override
    public GenericResponse<Boolean> deleteCard(CardBindingUpdateRequest request, String uid) {
        log.info("#CardFacadeServiceImpl#deleteCard request is {},uid {}", JsonUtils.toJson(request),uid);
        PaymentInstrumentQuery query = new PaymentInstrumentQuery();
        query.setUid(uid);
        Either<PaymentError, List<PaymentInstrumentBO>> instrumentBOS = paymentInstrumentApi.queryPaymentInstruments(query);
        if (instrumentBOS.isLeft()) {
            return GenericResponse.fail(instrumentBOS.getLeft().getCode(),instrumentBOS.getLeft().getMsg());
        }
        List<PaymentInstrumentBO> list = instrumentBOS.get();
        if (list == null || list.isEmpty() || !list.stream().map(PaymentInstrumentBO::getInstrumentId).collect(Collectors.toList()).contains(request.getCardId())) {
            return GenericResponse.fail(instrumentBOS.getLeft().getCode(),instrumentBOS.getLeft().getMsg());
        }
        PaymentInstrumentUpdate update = new PaymentInstrumentUpdate();
        update.setInstrumentId(request.getCardId());
        update.setBindingStatus(BindStatus.UNBIND);
        Either<PaymentError, Boolean> deleteResult = paymentInstrumentApi.updatePaymentInstrument(update);
        if (deleteResult.isLeft()) {
            return GenericResponse.fail(instrumentBOS.getLeft().getCode(),instrumentBOS.getLeft().getMsg());
        }
        log.info("#CardFacadeServiceImpl#deleteCard response {}", JsonUtils.toJson(deleteResult.get()));
        return GenericResponse.success(deleteResult.get());
    }

    @Override
    public GenericResponse<List<ChannelCardInfoResponse>> listCardByUserId(String channelType, String uid) {
        log.info("#CardFacadeServiceImpl#listCardByUserId channelType is {},uid {}", channelType,uid);
        PaymentInstrumentQuery query = new PaymentInstrumentQuery();
        query.setUid(uid);
        query.setPaymentMethod(PaymentMethod.valueOf(channelType));
        query.setBindStatus(BindStatus.BINDED);
        Either<PaymentError, List<PaymentInstrumentBO>> instruments = paymentInstrumentApi.queryPaymentInstruments(query);
        if (instruments.isLeft()) {
            return GenericResponse.fail(instruments.getLeft().getCode(),instruments.getLeft().getMsg());
        }
        if (instruments.get() == null || instruments.get().isEmpty()) {
            return GenericResponse.success(new ArrayList<>());
        }
        List<ChannelCardInfoResponse> responses = instruments.get().stream().map(PaymentInstrumentTransformer::toChannelCardInfoResponse).collect(Collectors.toList());
        packSchemeUrl(responses);
        log.info("#CardFacadeServiceImpl#listCardByUserId response {}", JsonUtils.toJson(responses));
        return GenericResponse.success(responses);
    }

    @Override
    public GenericResponse<Boolean> updateBinding(CardBindingUpdateRequest request) {
        log.info("#CardFacadeServiceImpl#updateBinding request {}", JsonUtils.toJson(request));
        BindStatus bindStatus = request.getStatus() == 1 ? BindStatus.BINDED : BindStatus.UNBIND;
        PaymentMethod paymentMethod = PaymentMethod.valueOf(request.getChannelType());
        PaymentInstrumentUpdate update = new PaymentInstrumentUpdate();
        update.setInstrumentId(request.getCardId());
        update.setPaymentMethod(paymentMethod);
        update.setBindingStatus(bindStatus);
        Either<PaymentError, Boolean> result = paymentInstrumentApi.updatePaymentInstrument(update);
        if (result.isLeft()) {
            return GenericResponse.fail(ErrorCode.SERVER_ERROR.getCode(), ErrorCode.SERVER_ERROR.getMsg());
        }
        // 绑卡时,最新绑定的卡设置为默认卡
        if (bindStatus == BindStatus.BINDED) {
            update = new PaymentInstrumentUpdate();
            update.setInstrumentId(request.getCardId());
            update.setPaymentMethod(paymentMethod);
            update.setDefaultInstrument(true);
            paymentInstrumentApi.updatePaymentInstrument(update);
        }
        log.info("#CardFacadeServiceImpl#updateBinding response {}", JsonUtils.toJson(result.get()));
        return GenericResponse.success(result.get());
    }

    @Override
    public GenericResponse<Integer> bindLimit() {
        log.info("#CardFacadeServiceImpl#bindLimit ");
        Either<PaymentError, Integer> result = validatorApi.bindLimit();
        if(result.isLeft()) {
            return  GenericResponse.fail(ErrorCode.SERVER_ERROR.getCode(),ErrorCode.SERVER_ERROR.getMsg());
        } else {
            log.info("#CardFacadeServiceImpl#bindLimit response {}",JsonUtils.toJson(result));
            return GenericResponse.success(result.get());
        }
    }

    private void packSchemeUrl(List<ChannelCardInfoResponse> channelCardInfoResponses) {
        List<String> schemesList = channelCardInfoResponses.stream()
                .map(ChannelCardInfoResponse::getScheme)
                .distinct()
                .collect(Collectors.toList());
        Map<String, String> schemeUrlMap = commonService.getEnabledKeyValueMapByCodes(DictCode.SCHEME_URL, schemesList);
        if (MapUtils.isEmpty(schemeUrlMap)) {
            return;
        }
        for (ChannelCardInfoResponse channelCardInfoRespons : channelCardInfoResponses) {
            channelCardInfoRespons.setSchemeUrl(schemeUrlMap.get(channelCardInfoRespons.getScheme()));
        }
    }

    @Override
    public GenericResponse<PaymentInstrumentBindResponse> cardBindCheck(PaymentApiInstrumentQuery query) {
        if (query.getEmail() == null || query.getEmail().isEmpty()) {
            return GenericResponse.fail(ErrorCode.SERVER_ERROR.getCode(), "email is mandatory.");
        }
        String pattern = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(query.getEmail());
        if (!m.matches()) {
            return GenericResponse.fail(ErrorCode.SERVER_ERROR.getCode(), "email is invalid.");
        }

        PaymentInstrumentCommonQuery instrumentQuery = PaymentInstrumentCommonQuery.builder()
                .email(query.getEmail())
                .uid(query.getUid())
                .phone(query.getPhone())
                .build();
        Either<PaymentError, PaymentInstrumentBindResponse> response = paymentInstrumentApi.bindStatusCheck(instrumentQuery);
        log.info("CardFacadeServiceImpl#cardBindCheck instrument response {}",JsonUtils.toJson(response));
        if (response.isRight()) {
            return GenericResponse.success(response.get());
        } else {
            return GenericResponse.fail(ErrorCode.SERVER_ERROR.getCode(), response.getLeft().getMsg());
        }
    }

    @Override
    public GenericResponse<List<PaymentInstrumentBO>> cardList(PaymentApiInstrumentQuery query) {
        if (query.getEmail() == null || query.getEmail().isEmpty()) {
            return GenericResponse.fail(ErrorCode.SERVER_ERROR.getCode(), "email is mandatory.");
        }
        String pattern = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(query.getEmail());
        if (!m.matches()) {
            return GenericResponse.fail(ErrorCode.SERVER_ERROR.getCode(), "email is invalid.");
        }
        PaymentInstrumentCommonQuery instrumentQuery = PaymentInstrumentCommonQuery.builder()
                .email(query.getEmail())
                .uid(query.getUid())
                .phone(query.getPhone())
                .build();
        Either<PaymentError, List<PaymentInstrumentBO>> response = paymentInstrumentApi.instrumentList(instrumentQuery);
        log.info("CardFacadeServiceImpl#cardBindCheck instrument response {}",JsonUtils.toJson(response));
        if (response.isRight()) {
            return GenericResponse.success(response.get());
        } else {
            return GenericResponse.fail(ErrorCode.SERVER_ERROR.getCode(), response.getLeft().getMsg());
        }
    }
}
