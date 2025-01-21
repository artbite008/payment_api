package com.siupay.openapi.validators;

import com.siupay.openapi.constant.Constant;
import com.siupay.openapi.constant.DynamicConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.siupay.openapi.bo.ValidatorBO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

/**
 * @program: payment-api
 * @description: swich validator
 * @author: Sandy
 **/
@Component
@Slf4j
@Order(0)
public class SwitchValidator implements Validator<ValidatorBO> {

    @Autowired
    private DynamicConstants dynamicConstants;

    @Override
    public void validator(ValidatorBO param) {
        Assert.isNull(param, "channel param validator is null!");
        Assert.isTrue(dynamicConstants.getChannelSwitchMap().get(Constant.WALLET_PAY_IN_SWITCH),
                "channel is not open!");
    }
}
