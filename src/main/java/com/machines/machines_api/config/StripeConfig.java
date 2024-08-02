package com.machines.machines_api.config;

import com.stripe.Stripe;
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
    private String webhookSecret;

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
        Stripe.apiKey = this.apiKey;
    }
}
