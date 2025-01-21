package com.siupay.openapi.util.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author darren
 */
@Component
public class RestTemplateRouter {
    @Autowired
    protected RestTemplate restTemplate;

//    @Autowired
//    @Qualifier("longReadTimeRestTemplate")
//    protected RestTemplate longReadTimeRestTemplate;

    public RestTemplate route(String appName, String uri) {
//        List<String> longTimeUrls = LongTimeClientUrls.LONG_TIME_MAPS.get(appName);
//        if (CollectionUtils.isNotEmpty(longTimeUrls) && longTimeUrls.contains(uri)) {
//            return longReadTimeRestTemplate;
//        }
        return restTemplate;
    }

}
