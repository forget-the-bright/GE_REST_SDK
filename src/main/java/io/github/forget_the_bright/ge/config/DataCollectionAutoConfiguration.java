package io.github.forget_the_bright.ge.config;

import io.github.forget_the_bright.ge.core.ApiClient;
import io.github.forget_the_bright.ge.core.TokenHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 自动配置类，用于在Spring容器中自动注册API客户端和令牌持有者相关的Bean。
 *
 * <p>该类的主要功能包括：
 * <ul>
 *     <li>根据条件自动创建并注册 {@link ApiClient} 和 {@link TokenHolder} 的实例。</li>
 *     <li>依赖于 {@link ApiConfig} 配置类，提供API连接和服务认证所需参数。</li>
 * </ul>
 *
 * <p>生效条件：
 * <ul>
 *     <li>{@link ConditionalOnClass}: 类路径中必须存在 {@link ApiClient} 类。</li>
 *     <li>{@link EnableConfigurationProperties}: 启用并绑定 {@link ApiConfig} 配置属性。</li>
 *     <li>{@link ConditionalOnMissingBean}: 仅当容器中未手动定义相同类型的Bean时才生效。</li>
 * </ul>
 *
 * @author wanghao
 * @version 1.0
 * @since 2025-03-03
 */
@Configuration
@EnableConfigurationProperties(ApiConfig.class)
@ConditionalOnClass(ApiClient.class)
public class DataCollectionAutoConfiguration implements WebMvcConfigurer {

    /**
     * 创建并注册API客户端的Bean实例。
     *
     * <p>此方法基于传入的配置对象初始化一个 {@link ApiClient} 实例，并将其注入到Spring容器中。
     *
     * @param config API配置对象，包含服务端地址、超时设置等参数。
     *               该对象由 {@link ApiConfig} 提供。
     * @return 初始化完成的API客户端实例。
     * @see ApiConfig 配置类，定义了API客户端所需的各项参数。
     */
    @Bean
    @ConditionalOnMissingBean
    public ApiClient apiClient(ApiConfig config) {
        return new ApiClient(config);
    }

    /**
     * 创建并注册令牌持有者的Bean实例。
     *
     * <p>此方法基于传入的配置对象初始化一个 {@link TokenHolder} 实例，并将其注入到Spring容器中。
     *
     * @param config API配置对象，包含认证令牌刷新策略等参数。
     *               该对象由 {@link ApiConfig} 提供。
     * @return 初始化完成的令牌管理实例。
     * @see ApiConfig 配置类，定义了令牌持有者所需的各项参数。
     */
    @Bean
    @ConditionalOnMissingBean
    public TokenHolder tokenHolder(ApiConfig config) {
        return new TokenHolder(config);
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new EnumParamArgumentResolver());
    }
}
