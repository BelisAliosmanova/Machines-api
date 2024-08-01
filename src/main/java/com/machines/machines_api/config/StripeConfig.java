package com.machines.machines_api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "server.backend.stripe")
public class StripeConfig {
    private String apiKey;
    private String successUrl;
    private String cancelUrl;
}
