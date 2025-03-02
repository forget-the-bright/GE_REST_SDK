package io.github.forget_the_bright.ge.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

// ApiConfig.java
@Data
@ConfigurationProperties(prefix = "ge.datacollection")
public class ApiConfig {
    private String baseUrl;
    private String clientId;
    private String clientSecret;
    private String authType = "bearer";
    private Map<String, String> endpoints = new HashMap<>();
}