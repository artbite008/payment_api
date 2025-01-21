package com.siupay.openapi.v1.controller;

import com.google.common.collect.Maps;
import com.siupay.common.api.dto.response.GenericResponse;
import com.siupay.common.api.utils.UserContextUtils;
import com.siupay.common.lib.json.JsonUtils;
import com.siupay.openapi.annotation.RateLimiter;
import com.siupay.openapi.enums.RateLimiterStrategyEnum;
import com.siupay.openapi.util.RiskParamUtil;
import com.siupay.openapi.v1.bo.request.*;
import com.siupay.openapi.v1.bo.response.*;
import com.siupay.openapi.v1.service.PaymentCoreService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@CrossOrigin
public class PaymentCoreController {


    @Autowired
    private PaymentCoreService paymentCoreService;

    @Autowired
    private RedissonClient redissonClient;

    @ApiOperation("验证卡信息")
    @PostMapping(value = {"/pmtapi/v1/card/attach"})
//    @RateLimiter(strategy = RateLimiterStrategyEnum.ORDER_RATE_LIMITER_STRATEGY)
    public GenericResponse<ChannelCardInfoResponse> authorizeCard(
            @Validated @RequestBody BindCardInfoRequest cardInfoRequest) {
        log.info("authorizeCard request:{}", JsonUtils.toJson(cardInfoRequest));
        Map<String, Object> extraMap = Optional.ofNullable(cardInfoRequest.getExt()).orElse(Maps.newHashMap());
//        RiskParamUtil.putRiskExtra(extraMap);
        cardInfoRequest.setExt(extraMap);

        GenericResponse<ChannelCardInfoResponse> result = paymentCoreService.authAndSaveCardInfo(cardInfoRequest,UserContextUtils.getUserId());
        log.info("authorizeCard response:{}", JsonUtils.toJson(result));
        return result;
    }

    @ApiOperation("验证email信息")
    @GetMapping(value = {"/pmtapi/v1/email/exist"})
    public GenericResponse<Boolean> validateEmailExist(
            @Validated @RequestParam("email") String email) {
        RMapCache<String, LocalDateTime> rmap = redissonClient.getMapCache("emailValidation");
        if(rmap.containsKey(email)){
            return GenericResponse.success(rmap.get(email).plusMinutes(30).isAfter(LocalDateTime.now()));
        }
        else{
            rmap.put(email, LocalDateTime.now(), 30, TimeUnit.MINUTES);
            return GenericResponse.success(false);
        }

    }


    /**
     * 信用卡类充值下单接口
     */
    @ApiOperation("信用卡类充值下单接口")
    @PostMapping(value = {"/pmtapi/v1/payin_order/create"},params = {"recharge"})
//    @RateLimiter(strategy = RateLimiterStrategyEnum.ORDER_RATE_LIMITER_STRATEGY)
    public GenericResponse<CreditCardPreRechargeResponse> creditCardPreRecharge(
            @Validated @RequestBody BasePrRechargeRequest basePrRechargeRequest) {

        Map<String, Object> extraMap = Optional.ofNullable(basePrRechargeRequest.getExt()).orElse(Maps.newHashMap());
        RiskParamUtil.putRiskExtra(extraMap);
        basePrRechargeRequest.setExt(extraMap);

        GenericResponse<CreditCardPreRechargeResponse> result = paymentCoreService.preRecharge(basePrRechargeRequest);
        return result;

    }

    /**
     * 前端回调查询订单状态
     *
     * @param request
     * @return
     */
    @ApiOperation("前端回调确认订单状态")
    @PostMapping(value = {"/pmtapi/v1/payin_order/confirm"},params = {"recharge"})
    public GenericResponse<OrderConfirmResponse> orderConfirm(@Validated @RequestBody OrderConfirmRequest request){
        GenericResponse<OrderConfirmResponse> result = paymentCoreService.orderConfirm(request);
        return result;
    }

    @ApiOperation("信用卡买币预授权接口")
    @PostMapping(value = {"/pmtapi/v1/payin_order/create"},params = {"buy"})
    @RateLimiter(strategy = RateLimiterStrategyEnum.ORDER_RATE_LIMITER_STRATEGY)
    public GenericResponse<PreTradeResponse> buyCryptoCurrency(@Validated @RequestBody BuyCryptoBasedBankCardRequest request){
        Map<String, Object> extraMap = Optional.ofNullable(request.getExt()).orElse(Maps.newHashMap());
        RiskParamUtil.putRiskExtra(extraMap);
        request.setExt(extraMap);

        GenericResponse<PreTradeResponse> result = paymentCoreService.preTrade(request);
        return result;
    }

    @ApiOperation("前端回调确认交易订单状态")
    @PostMapping(value = {"/pmtapi/v1/payin_order/confirm"},params = {"buy"})
    public GenericResponse<TradeOrderDetailsResponse> tradeConfirm(@RequestBody @Validated TradeConfirmRequest request) {
        return paymentCoreService.tradeConfirm(request);
    }

    @ApiOperation("前端查询订单状态")
    @GetMapping(value = {"/pmtapi/v1/payin_order/{orderId}"})
    public GenericResponse<QueryPayinOrderResponse> queryByPayinOrderId(@PathVariable("orderId")String orderId){
        GenericResponse<QueryPayinOrderResponse> result = paymentCoreService.queryByPayinOrderId(orderId);
        return result;
    }
}
