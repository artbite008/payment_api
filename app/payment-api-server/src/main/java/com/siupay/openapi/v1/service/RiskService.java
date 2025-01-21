package com.siupay.openapi.v1.service;

import com.siupay.common.api.dto.response.GenericResponse;
import com.siupay.openapi.v1.bo.request.RiskCardRequest;
import com.siupay.openapi.v1.enums.RiskLocalBizTypeEnum;

/**
 * @program: deposit
 * @description: 风控调用service
 * @author: Sandy
 **/
public interface RiskService {

    /**
     * 获取sessionI getRiskSessionId
     */
    String getRiskSessionInfo(String userId, RiskLocalBizTypeEnum bizType);


    String consumeSessionId(String userId, String sessionId, RiskLocalBizTypeEnum bizType);


    GenericResponse<Boolean> payinRiskDetect(RiskCardRequest riskCardRequest);
}
