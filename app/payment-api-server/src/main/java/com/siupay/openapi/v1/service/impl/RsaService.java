package com.siupay.openapi.v1.service.impl;

import com.siupay.common.api.exception.ErrorCode;
import com.siupay.common.api.exception.PaymentException;
import com.siupay.openapi.v1.config.RsaProperties;
import com.siupay.openapi.v1.util.RSAEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//PA-2558: PCI security enhancement
@Service
public class RsaService {
    @Autowired
    private RsaProperties properties;

    public String encrypt(String plaintext) {
        try {
            return RSAEncrypt.encrypt(plaintext, properties.getPublicKey());
        } catch (Exception e) {
            throw new PaymentException(ErrorCode.BUSINESS_ERROR, "encrypt error");
        }
    }

    public String decrypt(String plaintext) {
        try {
            return RSAEncrypt.decrypt(plaintext, properties.getPublicKey());
        } catch (Exception e) {
            throw new PaymentException(ErrorCode.BUSINESS_ERROR, "decrypt error");
        }
    }
}
