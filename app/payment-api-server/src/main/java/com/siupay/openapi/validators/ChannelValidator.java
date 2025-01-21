package com.siupay.openapi.validators;

import java.util.Objects;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.siupay.common.api.exception.ErrorCode;
import com.siupay.common.api.exception.PaymentException;
import com.siupay.common.lib.enums.ChannelEnum;
import com.siupay.openapi.bo.ValidatorBO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

/**
 * @program: payment-api
 * @description: channel validator
 * @author: Sandy
 * @create: 2022-02-16
 **/
@Component
@Order(20)
@Slf4j
public class ChannelValidator implements Validator<ValidatorBO> {

    @Override
    public void validator(ValidatorBO param) {
        Assert.isNull(param, "channel param validator is null!");
        ChannelEnum channelEnum = ChannelEnum.get(param.getChannelId());
        if (Objects.isNull(channelEnum) || ChannelEnum.UNKNOWN.equals(channelEnum)) {
            log.error("channel validator fail, param: {}", JSON.toJSONString(param));
            throw new PaymentException(ErrorCode.PARAM_ERROR, ErrorCode.PARAM_ERROR.getMsg());
        }
    }
}
