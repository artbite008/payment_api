package com.siupay.openapi.validators;

import com.siupay.openapi.bo.ValidatorBO;

/**
 * @program: payment-api
 * @description: base validator
 * @author: Sandy
 **/
public interface Validator<T extends ValidatorBO> {
    /**
     * call validator
     * 
     * @param param
     * @return
     */
    void validator(T param);
}
