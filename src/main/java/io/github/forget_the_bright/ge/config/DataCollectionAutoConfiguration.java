package io.github.forget_the_bright.ge.config;

import io.github.forget_the_bright.ge.core.ApiClient;
import io.github.forget_the_bright.ge.core.TokenHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 自动配置类，用于创建API客户端和令牌持有者Bean
 *
 * <p>该类在满足以下条件时生效：
 * 1. 类路径中存在ApiClient类
 * 2. 未手动定义相同类型的Bean
 *
 * @see ApiConfig 配置属性类，包含API连接相关参数
 * @author wanghao
 * @version 1.0
 * @since 2025-03-03
 */
@Configuration
@EnableConfigurationProperties(ApiConfig.class)
@ConditionalOnClass(ApiClient.class)
public class DataCollectionAutoConfiguration {

    /**
     * 创建API客户端实例Bean
     *
     * @param config API配置属性对象，包含服务端地址、超时设置等参数
     * @return 初始化完成的API客户端实例，已注入配置参数
     * @ConditionalOnMissingBean 当容器中不存在ApiClient类型Bean时创建
     */
    @Bean
    @ConditionalOnMissingBean
    public ApiClient apiClient(ApiConfig config) {
        return new ApiClient(config);
    }

    /**
     * 创建令牌持有者实例Bean
     *
     * @param config API配置属性对象，包含认证令牌刷新策略等参数
     * @return 初始化完成的令牌管理实例，已注入配置参数
     * @ConditionalOnMissingBean 当容器中不存在TokenHolder类型Bean时创建
     */
    @Bean
    @ConditionalOnMissingBean
    public TokenHolder tokenHolder(ApiConfig config) {
        return new TokenHolder(config);
    }
}
