package com.siupay.openapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * validator type
 */
@Getter
@AllArgsConstructor
public enum ValidatorType {
    /**
     * kyc Validator
     */
    KYC_VALIDATOR,

    /**
     * SWITCH VALIDATOR
     */
    SWITCH_VALIDATOR;
}
