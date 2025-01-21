package com.siupay.openapi.v1.service.impl;

import com.siupay.openapi.v1.bo.request.BindCardInfoRequest;
import com.siupay.openapi.v1.config.PciSwitchProperties;
import com.siupay.openapi.v1.service.BindCardAuthorizationValidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//PA-2558: PCI security enhancement
@Service
@Slf4j
public class BindCardAuthorizationValidateServiceImpl implements BindCardAuthorizationValidateService {
    @Autowired
    private PciSwitchProperties switchProperties;

    @Autowired
    private RsaService rsaService;

    @Override
    public boolean credentialValidate(BindCardInfoRequest request, String userId) {
        if (!"ON".equalsIgnoreCase(switchProperties.getCredentialValidate())) {
            log.warn("BindCardAuthorizationValidateServiceImpl#credentialValidate校验开关关闭");
            return true;
        }
        String text = request.getCardToken() + "__" + userId;
        String plainText = rsaService.decrypt(request.getCredential());
        if (!text.equalsIgnoreCase(plainText)) {
            log.error("BindCardAuthorizationValidateServiceImpl#credentialValidate校验失败: request.credential is {} ", request.getCredential());
            return false;
        }
        return true;
    }
}
