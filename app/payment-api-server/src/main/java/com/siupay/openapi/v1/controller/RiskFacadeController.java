package com.siupay.openapi.v1.controller;

import com.siupay.common.api.dto.response.GenericResponse;
import com.siupay.common.api.exception.ErrorCode;
import com.siupay.common.api.exception.PaymentException;
import com.siupay.common.api.utils.UserContextUtils;
import com.siupay.openapi.annotation.RateLimiter;
import com.siupay.openapi.enums.RateLimiterStrategyEnum;
import com.siupay.openapi.v1.bo.RiskSessionResponseBO;
import com.siupay.openapi.v1.bo.request.RiskCardRequest;
import com.siupay.openapi.v1.bo.request.RiskSessionRequest;
import com.siupay.openapi.v1.bo.response.RiskSessionResponse;
import com.siupay.openapi.v1.manager.RiskManager;
import com.siupay.openapi.v1.service.BindCardAuthorizationService;
import com.siupay.openapi.v1.service.RiskService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@RestController
@Api(value = "session 服务")
@Slf4j
@CrossOrigin
public class RiskFacadeController {

    @Autowired
    private RiskManager riskManager;
    @Autowired
    private RiskService riskService;
    @Autowired
    private BindCardAuthorizationService bindCardAuthorizationService;

    @GetMapping(value = { "/pmtapi/v1/session_id/create"})
    public GenericResponse<RiskSessionResponse> getSessionId(RiskSessionRequest request) {
        // PCI 绑卡
        if ("pci".equals(request.getBizType())) {
            return GenericResponse.success(bindCardAuthorizationService.authorization(UserContextUtils.getUserId()));
        }
        RiskSessionResponseBO riskSessionInfoBO = riskManager.getRiskSessionInfo(UserContextUtils.getUserId(), request.getBizType());
        if (isNull(riskSessionInfoBO)) {
            throw new PaymentException(ErrorCode.BUSINESS_ERROR);
        }
        RiskSessionResponse result = new RiskSessionResponse();
        BeanUtils.copyProperties(riskSessionInfoBO,result);
        return GenericResponse.success(result);
    }

    @GetMapping(value = { "/pmtapi/v1/payin_order/risk/detect"})
    @RateLimiter(strategy = RateLimiterStrategyEnum.ORDER_RATE_LIMITER_STRATEGY)
    public GenericResponse<Boolean> checkCardRisk(RiskCardRequest riskCardRequest) {

        return GenericResponse.success(Boolean.TRUE);
    }

    @PostMapping(value = { "/pmtapi/v1/payin_order/risk/detect"})
    @RateLimiter(strategy = RateLimiterStrategyEnum.ORDER_RATE_LIMITER_STRATEGY)
    public GenericResponse<Boolean> checkCardRiskPost(@RequestBody RiskCardRequest riskCardRequest) {
        return GenericResponse.success(Boolean.TRUE);
    }
}
