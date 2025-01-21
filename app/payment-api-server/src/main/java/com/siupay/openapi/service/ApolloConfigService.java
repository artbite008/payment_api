package com.siupay.openapi.service;

import java.util.Map;

public interface ApolloConfigService {


    /**
     * 根据渠道id获取渠道配置信息
     * @param channelId
     * @return
     */
    Map<String, Map<String, Object>> getPayinAccountElement(String channelId);

}
