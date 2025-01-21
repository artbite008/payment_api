package com.siupay.openapi.v1.service.impl;

import com.siupay.openapi.v1.bo.response.RiskSessionResponse;
import com.siupay.openapi.v1.service.BindCardAuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//PA-2558: PCI security enhancement
@Service
@Slf4j
public class BindCardAuthorizationServiceImpl implements BindCardAuthorizationService {

    @Autowired
    private RsaService rsaService;

    @Override
    public RiskSessionResponse authorization(String userId) {
        long currentTimeMillis = System.currentTimeMillis();
        String sessionId = rsaService.encrypt(currentTimeMillis + "__" + userId);
        RiskSessionResponse response = new RiskSessionResponse();
        response.setCybsUrl(null);
        response.setSessionId(sessionId);
        log.info("currentTimeMillis {},sessionId{}", currentTimeMillis, sessionId);
        return response;
    }
}
