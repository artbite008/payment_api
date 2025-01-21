package com.siupay.openapi.v1.service.impl;

import com.google.common.collect.Maps;
import com.siupay.common.api.dto.PaymentAmount;
import com.siupay.common.api.dto.response.GenericResponse;
import com.siupay.common.api.exception.ErrorCode;
import com.siupay.common.api.exception.PaymentError;
import com.siupay.common.api.utils.UserContextUtils;
import com.siupay.common.lib.enums.ChannelEnum;
import com.siupay.common.lib.enums.TradeType;
import com.siupay.common.lib.json.JsonUtils;
import com.siupay.core.BindInstrumentApi;
import com.siupay.core.PayinOrderQueryApi;
import com.siupay.core.PaymentServiceApi;
import com.siupay.core.dto.*;
import com.siupay.enums.PayinOrderStatusEnum;
import com.siupay.instrument.PaymentInstrumentApi;
import com.siupay.instrument.PaymentInstrumentPretreatedApi;
import com.siupay.instrument.dto.PaymentInstrumentBO;
import com.siupay.instrument.dto.address.Address;
import com.siupay.instrument.dto.billing.Billing;
import com.siupay.instrument.dto.card.Card;
import com.siupay.instrument.dto.reponse.PaymentInstrumentBindResponse;
import com.siupay.instrument.dto.request.PaymentInstrumentPretreatedQuery;
import com.siupay.instrument.dto.request.PaymentInstrumentUpdate;
import com.siupay.instrument.enums.BindStatus;
import com.siupay.instrument.enums.CardType;
import com.siupay.openapi.util.StringConvertInteger;
import com.siupay.openapi.v1.constants.RequestExtFields;
import com.siupay.openapi.v1.enums.RiskLocalBizTypeEnum;
import com.siupay.openapi.v1.manager.OrderBaseManager;
import com.siupay.openapi.v1.service.BindCardAuthorizationValidateService;
import com.siupay.openapi.v1.service.CardFacadeService;
import com.siupay.openapi.v1.service.PaymentCoreService;
import com.siupay.openapi.v1.service.RiskService;
import com.siupay.openapi.v1.bo.request.*;
import com.siupay.openapi.v1.bo.response.*;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class PaymentCoreServiceImpl implements PaymentCoreService {


    @Autowired
    private BindInstrumentApi bindInstrumentApi;
    @Autowired
    private PaymentServiceApi paymentServiceApi;
    @Autowired
    private PayinOrderQueryApi payinOrderQueryApi;
    @Autowired
    private RiskService riskService;
    @Autowired
    private PaymentInstrumentApi paymentInstrumentApi;
    @Autowired
    private PaymentInstrumentPretreatedApi paymentInstrumentPretreatedApi;
    @Autowired
    private BindCardAuthorizationValidateService cardAuthorizationValidateService;

    @Autowired
    private OrderBaseManager orderBaseManager;

    private String PIR_PREFIX = "pir_"; // payin_order prefix

    private String MASK_NUMBER_FORMAT = " **** ";

    @Autowired
    private CardFacadeService cardService;

    public boolean newVersionOrderId(String orderId) {
        return orderId.startsWith(PIR_PREFIX);
    }

    @Override
    public GenericResponse<ChannelCardInfoResponse> authAndSaveCardInfo(BindCardInfoRequest cardInfoRequest, String userId) {
//        if(!cardAuthorizationValidateService.credentialValidate(cardInfoRequest,userId)) {
//            return GenericResult.fail(ErrorCode.SERVER_ERROR.getCode(),ErrorCode.SERVER_ERROR.getMsg());
//        }
//        String originSessionId = riskService.consumeSessionId(UserContextUtils.getUserId(), cardInfoRequest.getRiskSessionId(), RiskLocalBizTypeEnum.CARD);
//        if (StringUtils.isEmpty(originSessionId)){
//            return GenericResult.fail(ErrorCode.SERVER_ERROR.getCode(),ErrorCode.SERVER_ERROR.getMsg());
//        }
        if (cardInfoRequest.getEmail() == null || cardInfoRequest.getEmail().isEmpty()) {
            return GenericResponse.fail(ErrorCode.SERVER_ERROR.getCode(),"email is mandatory.");
        }
        PaymentApiInstrumentQuery query = new PaymentApiInstrumentQuery();
        query.setEmail(cardInfoRequest.getEmail());
        GenericResponse<PaymentInstrumentBindResponse> result = cardService.cardBindCheck(query);
//        if (BindStatus.BINDED.name().equalsIgnoreCase(result.getData().getBindStatus().name())) {
//            return GenericResponse.fail(ErrorCode.SERVER_ERROR.getCode(),"email 已经绑定.");
//        }
        cardInfoRequest.setRiskSessionId("123");
        BindInstrumentRequest bindInstrumentRequest = new BindInstrumentRequest();
        buildBindInstrumentRequest(cardInfoRequest,bindInstrumentRequest);
        log.info("bind instrument to payment-core request is {}", JsonUtils.toJson(bindInstrumentRequest));
        Either<PaymentError, BindInstrumentResponse> either = bindInstrumentApi.bind(bindInstrumentRequest);
        if (either.isLeft()){
            return GenericResponse.fail(ErrorCode.SERVER_ERROR.getCode(),ErrorCode.SERVER_ERROR.getMsg());
        }else {
            ChannelCardInfoResponse channelCardInfoResponse = new ChannelCardInfoResponse();
            buildChannelCardInfoResponse(either.get(),channelCardInfoResponse);
            return GenericResponse.success(channelCardInfoResponse);
        }
    }

    @Override
    public GenericResponse<CreditCardPreRechargeResponse> preRecharge(BasePrRechargeRequest basePrRechargeRequest) {

//        String originSessionId = riskService.consumeSessionId(UserContextUtils.getUserId(), (String) basePrRechargeRequest.getExt().getOrDefault("SESSION_ID", null), RiskLocalBizTypeEnum.PRECHARGE);
//        if (StringUtils.isEmpty(originSessionId)){
//            return GenericResult.fail(DepositErrorCode.RISK_RESULT_REJECT.getCode(),DepositErrorCode.RISK_RESULT_REJECT.getDescription());
//        }
        CreatePayinOrderRequest createPayinOrderRequest = new CreatePayinOrderRequest();
        buildCreatePayinOrderRequest(createPayinOrderRequest,basePrRechargeRequest, UUID.randomUUID().toString());
        String paymentInstrumentId = createPayinOrderRequest.getConfirm().getPaymentInstrumentId();
        Either<PaymentError, CreatePayinOrderResponse> either = paymentServiceApi.create(createPayinOrderRequest);
        if (either.isLeft()){
            return GenericResponse.fail(either.getLeft().getCode(),either.getLeft().getMsg());
        }else {
            //deposit服务中order阶段已经做了预授权能够返回3ds跳转链接
            CreditCardPreRechargeResponse creditCardPreRechargeResponse = new CreditCardPreRechargeResponse();
            PayinOrderConfirmRequest payinOrderConfirmRequest = new PayinOrderConfirmRequest();
            payinOrderConfirmRequest.setOrderId(either.get().getOrderId());
            Either<PaymentError, PayinOrderConfirmResponse> confirmEither = paymentServiceApi.confirm(payinOrderConfirmRequest);
            if (confirmEither.isLeft()){
                return GenericResponse.fail(confirmEither.getLeft().getCode(),confirmEither.getLeft().getMsg());
            }
            buildCreditCardPreRechargeResponse(either.get(),creditCardPreRechargeResponse,confirmEither.get());
            //设置默认卡
            Either<PaymentError, Boolean> defaultEither = paymentInstrumentApi.updatePaymentInstrument(PaymentInstrumentUpdate.builder()
                    .instrumentId(paymentInstrumentId)
                    .defaultInstrument(Boolean.TRUE)
                    .build());
            if (defaultEither.isLeft()) {
                log.error("[PaymentCoreServiceImpl.preRecharge] set default card error,paymentInstrumentId = {},orderId = {}",
                        paymentInstrumentId, payinOrderConfirmRequest.getOrderId());
            }
            return GenericResponse.success(creditCardPreRechargeResponse);
        }
    }

    @Override
    public GenericResponse<OrderConfirmResponse> orderConfirm(OrderConfirmRequest orderConfirmRequest) {
        //deposit服务中confirm阶段其实只是查询，checkout要重新上分，但是在老order接口已经confirm做了
        PayinOrderConfirmRequest payinOrderConfirmRequest = new PayinOrderConfirmRequest();
        payinOrderConfirmRequest.setOrderId(orderConfirmRequest.getOrderId());
        Either<PaymentError, PayinOrderDto> either = payinOrderQueryApi.payinOrderQuery(orderConfirmRequest.getOrderId());
        if (either.isLeft()){
            return GenericResponse.fail(either.getLeft().getCode(),either.getLeft().getMsg());
        }else {
            PayinOrderDto payinOrder = either.get();
            if (!payinOrder.getOrderType().equals(TradeType.RECHARGE.name())){
                return GenericResponse.fail(ErrorCode.SERVER_ERROR.getCode(),ErrorCode.SERVER_ERROR.getMsg());
            }
            OrderConfirmResponse orderConfirmResponse = new OrderConfirmResponse();
            orderConfirmResponse.setOrderId(payinOrder.getOrderId());
            orderConfirmResponse.setCreatedAt(payinOrder.getCreateTime());
            orderConfirmResponse.setFiatCurrency(payinOrder.getPayinAmount().getCurrency());
            orderConfirmResponse.setRechargeAmount(payinOrder.getPayinAmount().getAmount());
            orderConfirmResponse.setAccountRechargebackAmount(payinOrder.getDepositFiatAmount().getAmount());
            orderConfirmResponse.setTotalFeeAmount(payinOrder.getFeeAmount().getAmount());

            //(0.进行中1.成功2.失败)
            orderConfirmResponse.setStatus(StringConvertInteger.payinStatusConvert(PayinOrderStatusEnum.valueOf(payinOrder.getStatus())));
            ChannelEnum channelEnum = ChannelEnum.get(payinOrder.getChannelId());
            orderConfirmResponse.setChannelName(channelEnum.name());
            orderConfirmResponse.setChannelType(orderBaseManager.getPaymentMethodEnName(channelEnum));
            return GenericResponse.success(orderConfirmResponse);
        }
    }

    @Override
    public GenericResponse<PreTradeResponse> preTrade(BuyCryptoBasedBankCardRequest request) {

        String originSessionId = riskService.consumeSessionId(UserContextUtils.getUserId(), (String) request.getExt().getOrDefault("SESSION_ID", null), RiskLocalBizTypeEnum.BYCRPPTO);
        if (StringUtils.isEmpty(originSessionId)){
            return GenericResponse.fail(ErrorCode.SERVER_ERROR.getCode(),ErrorCode.SERVER_ERROR.getMsg());
        }

        CreatePayinOrderRequest createPayinOrderRequest = new CreatePayinOrderRequest();
        buildCreatePayinOrderRequest(createPayinOrderRequest,request,originSessionId);
        String paymentInstrumentId = createPayinOrderRequest.getConfirm().getPaymentInstrumentId();
        Either<PaymentError, CreatePayinOrderResponse> either = paymentServiceApi.create(createPayinOrderRequest);
        if (either.isLeft()){
            return GenericResponse.fail(either.getLeft().getCode(),either.getLeft().getMsg());
        }else {
            //deposit服务中order阶段已经做了预授权能够返回3ds跳转链接
            PreTradeResponse preTradeResponse = new PreTradeResponse();
            PayinOrderConfirmRequest payinOrderConfirmRequest = new PayinOrderConfirmRequest();
            payinOrderConfirmRequest.setOrderId(either.get().getOrderId());
            Either<PaymentError, PayinOrderConfirmResponse> confirmEither = paymentServiceApi.confirm(payinOrderConfirmRequest);
            if (confirmEither.isLeft()){
                return GenericResponse.fail(confirmEither.getLeft().getCode(),confirmEither.getLeft().getMsg());
            }
            buildCreditCardPreRechargeResponse(either.get(),preTradeResponse,confirmEither.get());
            //设置默认卡
            Either<PaymentError, Boolean> defaultEither = paymentInstrumentApi.updatePaymentInstrument(PaymentInstrumentUpdate.builder()
                    .instrumentId(paymentInstrumentId)
                    .defaultInstrument(Boolean.TRUE)
                    .build());
            if (defaultEither.isLeft()) {
                log.error("[PaymentCoreServiceImpl.preTrade] set default card error,paymentInstrumentId = {},orderId = {}",
                        paymentInstrumentId, payinOrderConfirmRequest.getOrderId());
            }
            return GenericResponse.success(preTradeResponse);
        }
    }

    @Override
    public GenericResponse<TradeOrderDetailsResponse> tradeConfirm(TradeConfirmRequest request) {
        //deposit服务中confirm阶段其实只是查询，checkout要重新上分，但是在老order接口已经confirm做了
        PayinOrderConfirmRequest payinOrderConfirmRequest = new PayinOrderConfirmRequest();
        payinOrderConfirmRequest.setOrderId(request.getOrderId());

        Either<PaymentError, PayinOrderDto> either = payinOrderQueryApi.payinOrderQuery(request.getOrderId());
        if (either.isLeft()){
            return GenericResponse.fail(either.getLeft().getCode(),either.getLeft().getMsg());
        }else {

            PayinOrderDto payinOrder = either.get();
            if (!payinOrder.getOrderType().equals(TradeType.BUY.name())){
                return GenericResponse.fail(ErrorCode.SERVER_ERROR.getCode(),ErrorCode.SERVER_ERROR.getMsg());
            }
            TradeOrderDetailsResponse tradeOrderDetailsResponse = new TradeOrderDetailsResponse();
            tradeOrderDetailsResponse.setOrderId(payinOrder.getOrderId());
            //(0.进行中1.成功2.失败)
            tradeOrderDetailsResponse.setOrderStatus(StringConvertInteger.payinStatusConvert(PayinOrderStatusEnum.valueOf(payinOrder.getStatus())));
            tradeOrderDetailsResponse.setBizSource(StringConvertInteger.bizSourceConvert(payinOrder.getPaymentMethod()));
            tradeOrderDetailsResponse.setCryptoQuantity(payinOrder.getDepositCryptoAmount().getAmount());
            tradeOrderDetailsResponse.setCryptoCurrency(payinOrder.getDepositCryptoAmount().getCurrency());
            tradeOrderDetailsResponse.setFee(payinOrder.getFeeAmount().getAmount());
            tradeOrderDetailsResponse.setFiatAmount(payinOrder.getPayinAmount().getAmount());
            tradeOrderDetailsResponse.setFiatCurrency(payinOrder.getPayinAmount().getCurrency());
            ChannelEnum channelEnum = ChannelEnum.get(payinOrder.getChannelId());
            tradeOrderDetailsResponse.setPaymentName(orderBaseManager.getPaymentMethodEnName(channelEnum));

            BigDecimal price = payinOrder.getDepositFiatAmount().getAmount().divide(payinOrder.getDepositCryptoAmount().getAmount(),2, RoundingMode.DOWN);
            tradeOrderDetailsResponse.setPrice(price);
            tradeOrderDetailsResponse.setTradeType(StringConvertInteger.tradeTypeConvert(TradeType.BUY));
            tradeOrderDetailsResponse.setCreatedAt(payinOrder.getCreateTime());
            Card card = queryPaymentInstrumentInfo(payinOrder.getPaymentInstrumentId());
            if (Objects.nonNull(card)){
                if (StringUtils.isEmpty(card.getIssuerBank())){
                    tradeOrderDetailsResponse.setIssuingBank(card.getIssuer());
                }else {
                    tradeOrderDetailsResponse.setIssuingBank(card.getIssuerBank());
                }
                tradeOrderDetailsResponse.setBindingCard(card.getCardMaskNumber());
            }
            return GenericResponse.success(tradeOrderDetailsResponse);
        }
    }

    @Override
    public GenericResponse<QueryPayinOrderResponse> queryByPayinOrderId(String orderId){
        //deposit服务中confirm阶段其实只是查询，checkout要重新上分，但是在老order接口已经confirm做了
        PayinOrderConfirmRequest payinOrderConfirmRequest = new PayinOrderConfirmRequest();
        payinOrderConfirmRequest.setOrderId(orderId);
        Either<PaymentError, PayinOrderDto> either = payinOrderQueryApi.payinOrderQuery(orderId);
        if (either.isLeft()){
            return GenericResponse.fail(either.getLeft().getCode(),either.getLeft().getMsg());
        }else {
            PayinOrderDto payinOrder = either.get();
            if (!payinOrder.getOrderType().equals(TradeType.RECHARGE.name())){
                return GenericResponse.fail(ErrorCode.SERVER_ERROR.getCode(),ErrorCode.SERVER_ERROR.getMsg());
            }
            QueryPayinOrderResponse orderConfirmResponse = new QueryPayinOrderResponse();
            orderConfirmResponse.setOrderId(payinOrder.getOrderId());
            orderConfirmResponse.setCreatedAt(payinOrder.getCreateTime());
            orderConfirmResponse.setFiatCurrency(payinOrder.getPayinAmount().getCurrency());
            orderConfirmResponse.setRechargeAmount(payinOrder.getPayinAmount().getAmount());
            orderConfirmResponse.setAccountRechargebackAmount(payinOrder.getDepositFiatAmount().getAmount());
            orderConfirmResponse.setTotalFeeAmount(payinOrder.getFeeAmount().getAmount());

            //(0.进行中1.成功2.失败)
            orderConfirmResponse.setStatus(payinOrder.getStatus());
            ChannelEnum channelEnum = ChannelEnum.get(payinOrder.getChannelId());
            orderConfirmResponse.setChannelName(channelEnum.name());
            orderConfirmResponse.setChannelType(orderBaseManager.getPaymentMethodEnName(channelEnum));
            return GenericResponse.success(orderConfirmResponse);
        }
    }


    private Card queryPaymentInstrumentInfo(String paymentInstrumentId){

        if (!StringUtils.isEmpty(paymentInstrumentId)){
            PaymentInstrumentPretreatedQuery request = new PaymentInstrumentPretreatedQuery(paymentInstrumentId);
            log.info("query payment instrument by id request is {}",JsonUtils.toJson(request));
            Either<PaymentError, PaymentInstrumentBO> either = paymentInstrumentPretreatedApi.queryByInstrumentId(request);
            log.info("query payment instrument by id response is {}",JsonUtils.toJson(either));
            if (either.isRight()){
                return either.get().getCard();
            }
        }
        return null;
    }

    private void buildBindInstrumentRequest(BindCardInfoRequest cardInfoRequest, BindInstrumentRequest bindInstrumentRequest){
        //todo 前端对应ChannelType字段，deposit配置里payment_method = channel_id
        //channel_type是查询列表返回的deposit payment_method;
        bindInstrumentRequest.setPaymentMethod(cardInfoRequest.getChannelType());
        bindInstrumentRequest.setEmail(cardInfoRequest.getEmail());
        PaymentInstrumentDto paymentInstrumentDto = new PaymentInstrumentDto();
        paymentInstrumentDto.setCardPanMaskToken(cardInfoRequest.getCardToken());
        paymentInstrumentDto.setEmail(cardInfoRequest.getEmail());
        Card card = new Card();
        Billing billing = new Billing();
        Address address = new Address();
        if (cardInfoRequest.getCard() != null) {
            BeanUtils.copyProperties(cardInfoRequest.getCard(), card);
        }
        if (cardInfoRequest.getCard() != null && cardInfoRequest.getCard().getBilling() != null) {
            BeanUtils.copyProperties(cardInfoRequest.getCard().getBilling(), billing);
            if(cardInfoRequest.getCard().getBilling().getAddress() != null) {
                BeanUtils.copyProperties(cardInfoRequest.getCard().getBilling().getAddress(), address);
            }
        }

        card.setPrefix6(Optional.ofNullable(card.getBin()).orElse(card.getPrefix6()));
        card.setCardMaskNumber(Optional.ofNullable(card.getPrefix6()).orElse("")+ MASK_NUMBER_FORMAT + Optional.ofNullable(card.getLast4()).orElse("") );
        card.setCardType(CardType.valueOf(cardInfoRequest.getCardType()));
        card.setFirstName(Optional.ofNullable(card.getFirstName()).orElse(cardInfoRequest.getFirstName()));
        card.setLastName(Optional.ofNullable(card.getLastName()).orElse(cardInfoRequest.getLastName()));
        card.setScheme(Optional.ofNullable(card.getScheme()).orElse(cardInfoRequest.getScheme()));
        card.setExpireYear(Optional.ofNullable(card.getExpireYear()).orElse(cardInfoRequest.getExpireYear()));
        card.setExpireMonth(Optional.ofNullable(card.getExpireMonth()).orElse(cardInfoRequest.getExpireMonth()));

        address.setAddress1(Optional.ofNullable(card.getBilling()).map(Billing::getAddress).map(Address::getAddress1).orElse(cardInfoRequest.getAddress1()));
        address.setAddress2(Optional.ofNullable(card.getBilling()).map(Billing::getAddress).map(Address::getAddress2).orElse(""));
        address.setAddress3(Optional.ofNullable(card.getBilling()).map(Billing::getAddress).map(Address::getAddress3).orElse(""));
        address.setCountry(Optional.ofNullable(card.getBilling()).map(Billing::getAddress).map(Address::getCountry).orElse(cardInfoRequest.getCountry()));
        address.setPostalCode(Optional.ofNullable(card.getBilling()).map(Billing::getAddress).map(Address::getPostalCode).orElse(cardInfoRequest.getPostalCode()));
        address.setCity(Optional.ofNullable(card.getBilling()).map(Billing::getAddress).map(Address::getCity).orElse(cardInfoRequest.getCity()));
        address.setState(Optional.ofNullable(card.getBilling()).map(Billing::getAddress).map(Address::getState).orElse(cardInfoRequest.getState()));
        billing.setAddress(address);

        billing.setName(Optional.ofNullable(card.getFirstName()).orElse(cardInfoRequest.getFirstName()));
        billing.setPhone(Optional.ofNullable(card.getBilling()).map(Billing::getPhone).orElse(""));
        card.setBilling(billing);
        paymentInstrumentDto.setCard(card);
        bindInstrumentRequest.setPaymentInstrument(paymentInstrumentDto);

        AdditionalDataDto additionalDataDto = new AdditionalDataDto();
        additionalDataDto.setClientFrom(cardInfoRequest.getClientFrom());
        additionalDataDto.setBizSessionId(cardInfoRequest.getRiskSessionId());

        Map<String, Object> metaData = Optional.ofNullable(cardInfoRequest.getExt()).orElse(Maps.newHashMap());
        additionalDataDto.setAppVersion((String) metaData.get(RequestExtFields.APP_VERSION));
        additionalDataDto.setDeviceId((String) metaData.get(RequestExtFields.DEVICE_ID));
        additionalDataDto.setDeviceVersion((String) metaData.get(RequestExtFields.DEVICE_VERSION));
        additionalDataDto.setFingerId((String) metaData.get(RequestExtFields.FINGER_ID));
        additionalDataDto.setFingerPid((String) metaData.get(RequestExtFields.FINGER_PID));

        bindInstrumentRequest.setAdditionalData(additionalDataDto);

    }
    private void buildChannelCardInfoResponse(BindInstrumentResponse bindInstrumentResponse,ChannelCardInfoResponse channelCardInfoResponse){
        Card card = bindInstrumentResponse.getPaymentInstrument().getCard();
        channelCardInfoResponse.setChannel(bindInstrumentResponse.getChannelId());
        channelCardInfoResponse.setId(bindInstrumentResponse.getPaymentInstrument().getInstrumentId());
        channelCardInfoResponse.setCardType(card.getCardType().name());
        channelCardInfoResponse.setFirstName(card.getFirstName());
        channelCardInfoResponse.setLastName(card.getLastName());
        channelCardInfoResponse.setPostfix4(card.getLast4());
        channelCardInfoResponse.setPrefix6(card.getBin());
        channelCardInfoResponse.setIssuingBank(card.getIssuerBank());
        channelCardInfoResponse.setScheme(card.getScheme());
        channelCardInfoResponse.setCountry(card.getIssuerCountry());
    }

    private void buildCreatePayinOrderRequest(CreatePayinOrderRequest createPayinOrderRequest,BasePrRechargeRequest basePrRechargeRequest,String bizSessionId){
        createPayinOrderRequest.setOrderType(TradeType.RECHARGE.name());
        //充值法币不需要设置order
        createPayinOrderRequest.setOrder(new OrderDataDto());
        PaymentAmount paymentAmount = new PaymentAmount();
        paymentAmount.setAmount(basePrRechargeRequest.getRechargeAmount());
        paymentAmount.setCurrency(basePrRechargeRequest.getFiatCurrency());
        createPayinOrderRequest.setPaymentAmount(paymentAmount);
        PayinConfirmDto payinConfirmDto = new PayinConfirmDto();
        payinConfirmDto.setPaymentInstrumentId((String) basePrRechargeRequest.getExt().get(RequestExtFields.CHECKOUT_CARD_ID));
        payinConfirmDto.setPaymentMethod(basePrRechargeRequest.getChannelType());
        createPayinOrderRequest.setConfirm(payinConfirmDto);
        AdditionalDataDto additionalDataDto = new AdditionalDataDto();
        additionalDataDto.setClientFrom(basePrRechargeRequest.getClientFrom());
        additionalDataDto.setBizSessionId(bizSessionId);

        additionalDataDto.setAppVersion((String) basePrRechargeRequest.getExt().get(RequestExtFields.APP_VERSION));
        additionalDataDto.setDeviceId((String) basePrRechargeRequest.getExt().get(RequestExtFields.DEVICE_ID));
        additionalDataDto.setDeviceVersion((String) basePrRechargeRequest.getExt().get(RequestExtFields.DEVICE_VERSION));
        additionalDataDto.setFingerId((String) basePrRechargeRequest.getExt().get(RequestExtFields.FINGER_ID));
        additionalDataDto.setFingerPid((String) basePrRechargeRequest.getExt().get(RequestExtFields.FINGER_PID));

        createPayinOrderRequest.setAdditionalData(additionalDataDto);
    }

    private void buildCreatePayinOrderRequest(CreatePayinOrderRequest createPayinOrderRequest,BuyCryptoBasedBankCardRequest buyCryptoBasedBankCardRequest,String bizSessionId){
        createPayinOrderRequest.setOrderType(TradeType.BUY.name());
        //快捷法币需要设置order
        OrderDataDto orderDataDto = new OrderDataDto();
        orderDataDto.setCryptoCurrency(buyCryptoBasedBankCardRequest.getCryptoCurrency());
        createPayinOrderRequest.setOrder(orderDataDto);
        PaymentAmount paymentAmount = new PaymentAmount();
        paymentAmount.setAmount(buyCryptoBasedBankCardRequest.getFiatAmount());
        paymentAmount.setCurrency(buyCryptoBasedBankCardRequest.getFiatCurrency());
        createPayinOrderRequest.setPaymentAmount(paymentAmount);
        PayinConfirmDto payinConfirmDto = new PayinConfirmDto();
        payinConfirmDto.setPaymentInstrumentId((String) buyCryptoBasedBankCardRequest.getExt().get(RequestExtFields.CHECKOUT_CARD_ID));
        payinConfirmDto.setPaymentMethod(buyCryptoBasedBankCardRequest.getChannelType());
        createPayinOrderRequest.setConfirm(payinConfirmDto);

        AdditionalDataDto additionalDataDto = new AdditionalDataDto();
        additionalDataDto.setClientFrom(buyCryptoBasedBankCardRequest.getClientFrom());
        additionalDataDto.setBizSessionId(bizSessionId);
        additionalDataDto.setAppVersion((String) buyCryptoBasedBankCardRequest.getExt().get(RequestExtFields.APP_VERSION));
        additionalDataDto.setDeviceId((String) buyCryptoBasedBankCardRequest.getExt().get(RequestExtFields.DEVICE_ID));
        additionalDataDto.setDeviceVersion((String) buyCryptoBasedBankCardRequest.getExt().get(RequestExtFields.DEVICE_VERSION));
        additionalDataDto.setFingerId((String) buyCryptoBasedBankCardRequest.getExt().get(RequestExtFields.FINGER_ID));
        additionalDataDto.setFingerPid((String) buyCryptoBasedBankCardRequest.getExt().get(RequestExtFields.FINGER_PID));

        createPayinOrderRequest.setAdditionalData(additionalDataDto);
    }

    private void buildCreditCardPreRechargeResponse(CreatePayinOrderResponse createPayinOrderResponse,CreditCardPreRechargeResponse creditCardPreRechargeResponse,PayinOrderConfirmResponse payinOrderConfirmResponse){
        creditCardPreRechargeResponse.setRechargeStatus(payinOrderConfirmResponse.getStatus());
        creditCardPreRechargeResponse.setOrderId(payinOrderConfirmResponse.getOrderId());
        if (!StringUtils.isEmpty(payinOrderConfirmResponse.getNextAction().getRedirectUrl()))  {
            creditCardPreRechargeResponse.setRedirectUrl(payinOrderConfirmResponse.getNextAction().getRedirectUrl());
        }
    }

    private void buildCreditCardPreRechargeResponse(CreatePayinOrderResponse createPayinOrderResponse,PreTradeResponse preTradeResponse,PayinOrderConfirmResponse payinOrderConfirmResponse){
        preTradeResponse.setTradeStatus(payinOrderConfirmResponse.getStatus());
        preTradeResponse.setOrderId(payinOrderConfirmResponse.getOrderId());
        if (!StringUtils.isEmpty(payinOrderConfirmResponse.getNextAction().getRedirectUrl())) {
            preTradeResponse.setRedirectUrl(payinOrderConfirmResponse.getNextAction().getRedirectUrl());
        }
    }
}
