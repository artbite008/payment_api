package com.siupay.openapi.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.siupay.common.api.exception.ErrorCode;
import com.siupay.common.api.exception.PaymentException;
import com.siupay.openapi.service.ApolloConfigService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApolloConfigServiceImpl implements ApolloConfigService {

    @Autowired
    private Map<String, String> elements;

    @Override
    public Map<String, Map<String, Object>> getPayinAccountElement(String channelId) {
        if (!elements.containsKey(channelId)) {
            throw new PaymentException(ErrorCode.SYSTEM_CONFIG_ERROR);
        }

        return readPayInAccountElement(channelId);
    }

    /**
     * 获取payin页面属性配置信息
     * @param channelId
     * @return
     */
    private Map<String, Map<String, Object>> readPayInAccountElement(String channelId) {
        String accountElements = elements.get(channelId);
        return JSON.parseObject(accountElements, new TypeReference<Map<String, Map<String, Object>>>() {
        });
    }
}
