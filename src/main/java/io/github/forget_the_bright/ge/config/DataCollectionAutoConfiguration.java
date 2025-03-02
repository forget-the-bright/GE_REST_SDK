package io.github.forget_the_bright.ge.config;

import io.github.forget_the_bright.ge.core.ApiClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// DataCollectionAutoConfiguration.java
@Configuration
@EnableConfigurationProperties(ApiConfig.class)
@ConditionalOnClass(ApiClient.class)
public class DataCollectionAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ApiClient apiClient(ApiConfig config) {
        return new ApiClient(config);
    }

 /*   @Bean
    public AlarmApiInvoker alarmApiInvoker(ApiClient apiClient) {
        return new AlarmApiInvoker(apiClient);
    }*/

    // 其他模块Invoker...
}