/*
 * ```
 * Copyright 2019 Mek Global Limited
 * ```
 */



/*
 * ```
 * Copyright 2019 Mek Global Limited
 * ```
 */
package com.siupay.openapi.util.utils;

import com.siupay.common.api.exception.ErrorCode;
import com.siupay.common.api.exception.PaymentException;
import com.siupay.common.api.utils.UserContextUtils;
import com.siupay.common.lib.utils.AssertUtils;
import com.siupay.starter.chaincontext.ChainContextConstants;
import com.siupay.starter.chaincontext.ChainRequestContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * Created by ab on 2018/9/8
 */
@Slf4j
@Component
public class RestClient {


    private static final Logger logger = LoggerFactory.getLogger(RestClient.class);

//    @Value("${mobile.client.responseUris}")
//    private String responseUris = "";

    @Autowired
    private RestTemplateRouter restTemplateRouter;

    // 注册到eureka服务名称
    @Value("${mobile.application.deposit:DEPOSIT}")
    private String appName;

    public String getAppName() {
        return appName;
    }



    public <T> T get(String uri, ParameterizedTypeReference<T> responseType) {
        return get(uri, this.getHeader(), null, responseType, null);
    }


    public  <T> T get(String uri, MultiValueMap<String, String> request, ParameterizedTypeReference<T> responseType) {
        return get(uri, this.getHeader(), request, responseType, null);
    }

    protected <T> T get(String uri, HttpHeaders headers, MultiValueMap<String, String> request,
                        ParameterizedTypeReference<T> responseType, Map<String, ?> uriVariables) {
        HttpEntity<MultiValueMap<String, ?>> requestEntity = new HttpEntity<>(headers);
        //this.encode(request);
        UriComponents builder = UriComponentsBuilder.newInstance().queryParams(request).build();
        uri = uri + builder.toUriString();
        ResponseEntity<T> responseEntity = this.exchange(uri, HttpMethod.GET, requestEntity, responseType, uriVariables);
        return responseEntity.getBody();
    }


    public  <T> ResponseEntity<T> exchange(String uri, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
                                             ParameterizedTypeReference<T> responseType, Map<String, ?> uriVariables) {


        String url = this.getUrl(uri);
        this.printChainContext();
        ResponseEntity<T> responseEntity = null;
        long start = System.currentTimeMillis();
        try {

            if (uriVariables == null) {
                responseEntity = this.getRestTemplate(getAppName(), uri).exchange(url,
                        method,
                        requestEntity,
                        responseType
                );
            } else {
                responseEntity = this.getRestTemplate(getAppName(), uri).exchange(url,
                        method,
                        requestEntity,
                        responseType,
                        uriVariables);
            }
        } catch (RestClientException e) {
            long end = System.currentTimeMillis();
            logger.info("client url exception:{},restTemplate请求耗时(ms):{}", uri, end - start);
            this.printWarn(requestEntity, responseEntity, url);
            throw e;
        }
        this.validateResponse(requestEntity, responseEntity, url);
        return responseEntity;
    }

    protected HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(ChainContextConstants.USER_ID, UserContextUtils.getUserId());
        return headers;

    }

    public <T> void validateResponse(HttpEntity<?> requestEntity, ResponseEntity<T> responseEntity, String url) {
        if (requestEntity == null) {
            throw new PaymentException(ErrorCode.SERVER_ERROR);
        }

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            logger.warn(" call [{}]http status [{}}]", url, responseEntity.getStatusCode());
            throw new PaymentException(ErrorCode.SERVER_ERROR);
        }

        T result = responseEntity.getBody();


    }

    private void printWarn(HttpEntity<?> requestEntity, ResponseEntity responseEntity, String url) {
        logger.warn("call {} fail. request => {}.response => {}", url, requestEntity, responseEntity);
    }

    private void printChainContext() {
        logger.debug("chain context==>{}", ChainRequestContext.getCurrentContext());
    }



    protected String getUrl(String uri) {
        AssertUtils.notBlank(uri, "MobileBizErrorCodeEnum.PARAM_NOT_EMPTY");
        if (!uri.startsWith("/")) {
            uri = "/" + uri;
        }
        return "http://" + this.getAppName() + uri;
    }


    protected RestTemplate getRestTemplate(String appName, String uri) {
        return restTemplateRouter.route(appName, uri);
    }
}



