package com.siupay.openapi.v1.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pci.authorization.switch")
@Data
@RefreshScope
public class PciSwitchProperties {
    private String credentialValidate;
}
