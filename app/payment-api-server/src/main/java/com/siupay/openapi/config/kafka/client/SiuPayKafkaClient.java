package com.siupay.openapi.config.kafka.client;

public interface SiuPayKafkaClient {


    void send(String key, Object data);

}
