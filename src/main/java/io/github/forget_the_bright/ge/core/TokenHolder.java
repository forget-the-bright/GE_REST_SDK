package io.github.forget_the_bright.ge.core;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.forget_the_bright.ge.config.ApiConfig;
import io.github.forget_the_bright.ge.constant.OAuthApiEnum;
import io.github.forget_the_bright.ge.constant.attach.ApiModule;
import io.github.forget_the_bright.ge.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Token 管理类，使用 {@link CacheHolder} 实现 Token 缓存和自动刷新。
 * <p>
 * 该类负责管理访问 Token 的缓存，包括获取、刷新和清除 Token。
 *
 * @author wanghao (helloworlwh @ 163.com)
 * @since 2025-03-03
 */
@Slf4j
@Component
public class TokenHolder {

    /**
     * 缓存中存储 Token 的键。
     */
    private static final String TOKEN_KEY = "ge:access_token";

    /**
     * 默认提前刷新 Token 的时间缓冲，单位为毫秒。
     */
    private static final long DEFAULT_EXPIRE_BUFFER = 5000; // 提前 5 秒刷新 Token

    /**
     * API 配置对象，包含认证相关信息。
     */
    private static ApiConfig config;

    /**
     * 缓存持有者实例，用于存储和管理 Token。
     */
    private static CacheHolder<String, String> cacheHolder;

    /**
     * 构造函数，初始化缓存持有者和 API 配置。
     *
     * @param cacheHolder 缓存持有者实例，用于存储和管理 Token。
     * @param config      API 配置对象，包含认证相关信息。
     */
    @Autowired
    public TokenHolder(CacheHolder<String, String> cacheHolder, ApiConfig config) {
        this.cacheHolder = cacheHolder;
        this.config = config;
    }

    /**
     * 获取有效的 Token。
     * <p>
     * 如果缓存中存在有效的 Token，则返回该 Token；否则，刷新 Token 并返回新的 Token。
     *
     * @return 有效的 Token
     */
    public static synchronized String getValidToken() {
        String token = cacheHolder.get(TOKEN_KEY);
        if (StrUtil.isBlank(token)) {
            token = refreshToken();
        }
        return token;
    }

    /**
     * 刷新 Token。
     * <p>
     * 调用认证接口获取新的 Token，并更新缓存。
     *
     * @return 新的 Token
     * @throws ApiException 如果刷新 Token 失败，则抛出异常
     */
    private static synchronized String refreshToken() {
        try {
            // 调用认证接口获取新的 Token
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("grant_type", "password"); // 模式类型: 密码
            paramMap.put("username", config.getUsername()); // 账号
            paramMap.put("password", config.getPassword()); // 密码

            JSONObject tokenResponse = ApiClient.execute(ApiModule.OAUTH, OAuthApiEnum.GET_TOKEN, paramMap);

            String newToken = tokenResponse.getString("access_token");
            long expiresIn = ObjectUtil.defaultIfNull(tokenResponse.getLong("expires_in"), config.getTokenExpireSeconds());

            // 更新缓存
            cacheHolder.put(TOKEN_KEY, newToken, TimeUnit.SECONDS.toMillis(expiresIn) - DEFAULT_EXPIRE_BUFFER);
            log.info("Token 刷新成功，有效期剩余: {} 秒", expiresIn);

            return newToken;
        } catch (Exception e) {
            log.error("刷新 Token 失败", e);
            throw new ApiException("刷新 Token 失败: " + e.getMessage(), e);
        }
    }

    /**
     * 设置 Token。
     * <p>
     * 将新的 Token 存入缓存，并设置过期时间。
     *
     * @param token 新的 Token
     */
    public void setToken(String token) {
        // 更新缓存
        cacheHolder.put(TOKEN_KEY, token, TimeUnit.SECONDS.toMillis(config.getTokenExpireSeconds()) - DEFAULT_EXPIRE_BUFFER);
    }

    /**
     * 清除 Token 缓存。
     * <p>
     * 从缓存中移除 Token。
     */
    public static void clearToken() {
        cacheHolder.remove(TOKEN_KEY);
        log.info("Token 缓存已清除");
    }

    public static synchronized void clearToken(String token) {
        String realToken = cacheHolder.get(TOKEN_KEY);
        if (StrUtil.isEmpty(realToken)) {
            return;
        }
        if (!realToken.equals(token)) {
            return;
        }
        cacheHolder.remove(TOKEN_KEY);
        log.info("Token 缓存已清除");
    }
}
