package com.siupay.openapi.config.kafka.client.impl;

import com.alibaba.fastjson.JSON;
import com.siupay.openapi.config.kafka.client.SiuPayKafkaClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SiuPayKafkaClientImpl implements SiuPayKafkaClient {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public SiuPayKafkaClientImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String key, Object data) {
        kafkaTemplate.send(key, JSON.toJSONString(data));
    }

}