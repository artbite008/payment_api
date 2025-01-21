package com.siupay.openapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @author: Ywain
 * @date: 2021/12/20
 * @Description:
 */
@Configuration
public class PaymentApiInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private AccessLimitInterceptor accessLimitInterceptor;
    @Autowired
    private RateLimiterInterceptor rateLimiterInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(accessLimitInterceptor);
        registry.addInterceptor(rateLimiterInterceptor);
    }

}
