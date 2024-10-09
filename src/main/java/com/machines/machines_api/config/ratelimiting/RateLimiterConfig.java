package com.machines.machines_api.config.ratelimiting;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:rate-limiter.properties")
public class RateLimiterConfig {
    // This class remains empty
    // It's only used to load the properties
}