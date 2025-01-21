package com.siupay.openapi.v1.service;

import com.siupay.openapi.v1.bo.response.RiskSessionResponse;

public interface BindCardAuthorizationService {
    RiskSessionResponse authorization(String userId);
}
