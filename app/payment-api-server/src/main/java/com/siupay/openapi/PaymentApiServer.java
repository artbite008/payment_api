/*
 * Copyright 2019 Mek Global Limited
 */

package com.siupay.openapi;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import io.vavr.jackson.datatype.VavrModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableEurekaClient
@EnableApolloConfig(value = {"application", "env"})
@EnableFeignClients(basePackages = "com.siupay")
public class PaymentApiServer implements ApplicationListener<ApplicationReadyEvent> {

    public static void main(String[] args) {
        log.info("application starting");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(PaymentApiServer.class, args);
    }

    @Bean("elements")
    @ConfigurationProperties(prefix = "payment.user.channel.elements")
    public Map<String, String> elements() {
        return new HashMap<>();
    }

//    @Autowired
//    public void configureJackson(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
//        jackson2ObjectMapperBuilder.modulesToInstall(VavrModule.class);
//    }

    @Bean
    public ObjectMapper responseCustomer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)
                .configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true)
                .registerModule(new VavrModule(new VavrModule.Settings().useOptionInPlainFormat(true)));
        return objectMapper;
    }


//    @Bean
//    public TracingConcurrencyStrategy tracingConcurrencyStrategy() {
//        return TracingConcurrencyStrategy.register(GlobalTracer.get());
//    }

    @Bean
    public Module vavrModule() {
        return new VavrModule();
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("application is up");
    }
}
