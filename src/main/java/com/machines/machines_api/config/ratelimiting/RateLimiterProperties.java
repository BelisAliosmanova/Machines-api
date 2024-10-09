package com.machines.machines_api.config.ratelimiting;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "rate-limiter")
@Getter
@Setter
public class RateLimiterProperties {

    private Map<String, EndpointConfig> endpoints;

    @Getter
    @Setter
    public static class EndpointConfig {
        private int limit;
        private String duration;
    }
}
