package com.siupay.openapi.v1.service;

import com.siupay.openapi.v1.bo.request.BindCardInfoRequest;

public interface BindCardAuthorizationValidateService {
    boolean credentialValidate(BindCardInfoRequest request, String userId);
}

