package io.github.forget_the_bright.ge.core;

import cn.hutool.cache.impl.TimedCache;
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
 * Token 管理类，使用 Hutool 的 TimedCache 实现 Token 缓存和自动刷新
 */
@Slf4j
@Component
public class TokenHolder {

    private static final String TOKEN_KEY = "access_token";
    private static final long DEFAULT_EXPIRE_BUFFER = 5000; // 提前 5 秒刷新 Token

    private static ApiConfig config;
    private static TimedCache<String, String> tokenCache;

    @Autowired
    public TokenHolder(ApiConfig config) {
        this.config = config;
        // 初始化 TimedCache，设置缓存过期时间为 Token 有效期减去缓冲时间
        this.tokenCache = new TimedCache<>(TimeUnit.SECONDS.toMillis(config.getTokenExpireSeconds()) - DEFAULT_EXPIRE_BUFFER);
        // 设置缓存过期监听器，自动刷新 Token
        this.tokenCache.setListener((key, token) -> refreshToken());
    }

    /**
     * 获取有效的 Token
     *
     * @return 有效的 Token
     */
    public static String getValidToken() {
        String token = tokenCache.get(TOKEN_KEY, false);
        if (StrUtil.isBlank(token)) {
            token = refreshToken();
        }
        return token;
    }

    /**
     * 刷新 Token
     *
     * @return 新的 Token
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
            tokenCache.put(TOKEN_KEY, newToken, TimeUnit.SECONDS.toMillis(expiresIn) - DEFAULT_EXPIRE_BUFFER);
            log.info("Token 刷新成功，有效期剩余: {} 秒", expiresIn);

            return newToken;
        } catch (Exception e) {
            log.error("刷新 Token 失败", e);
            throw new ApiException("刷新 Token 失败: " + e.getMessage(), e);
        }
    }
    public void setToken(String token){
        // 更新缓存
        tokenCache.put(TOKEN_KEY, token, TimeUnit.SECONDS.toMillis(config.getTokenExpireSeconds()) - DEFAULT_EXPIRE_BUFFER);
    }
    /**
     * 清除 Token 缓存
     */
    public static void clearToken() {
        tokenCache.remove(TOKEN_KEY);
        log.info("Token 缓存已清除");
    }
}