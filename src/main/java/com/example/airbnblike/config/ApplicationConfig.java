package com.example.airbnblike.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class ApplicationConfig {

    @Value("${domain}")
    private String domain;

    @Bean
    public String getDomain() {
        return domain;
    }
}
