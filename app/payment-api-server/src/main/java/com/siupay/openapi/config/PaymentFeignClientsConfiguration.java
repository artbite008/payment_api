package com.siupay.openapi.config;

import java.io.IOException;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import feign.Logger;
import feign.Request;
import feign.Response;

public class PaymentFeignClientsConfiguration {


    @Bean
    public Logger logger() {
        return new Slf4jLogger();
    }

    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }


    public class Slf4jLogger extends Logger {

        private final org.slf4j.Logger logger;

        public Slf4jLogger() {
            this(Logger.class);
        }

        public Slf4jLogger(Class<?> clazz) {
            this(LoggerFactory.getLogger(clazz));
        }

        public Slf4jLogger(String name) {
            this(LoggerFactory.getLogger(name));
        }

        Slf4jLogger(org.slf4j.Logger logger) {
            this.logger = logger;
        }

        @Override
        protected void logRequest(String configKey, Level logLevel, Request request) {
            if (logger.isInfoEnabled()) {
                super.logRequest(configKey, logLevel, request);
            }
        }

        @Override
        protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime)
                throws IOException {
            if (logger.isInfoEnabled()) {
                return super.logAndRebufferResponse(configKey, logLevel, response, elapsedTime);
            }
            return response;
        }

        @Override
        protected void log(String configKey, String format, Object... args) {
            if (logger.isInfoEnabled()) {
                logger.info(String.format(methodTag(configKey) + format, args));
            }
        }
    }
}
