package io.github.forget_the_bright.ge.core;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.github.forget_the_bright.ge.config.ApiConfig;
import io.github.forget_the_bright.ge.exception.ApiException;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Token 管理类，使用 Hutool 的 TimedCache 实现 Token 缓存和自动刷新
 */
@Slf4j
public class TokenHolder {

    private static final String TOKEN_KEY = "access_token";
    private static final long DEFAULT_EXPIRE_BUFFER = 5000; // 提前 5 秒刷新 Token

    private final ApiConfig config;
    private final TimedCache<String, String> tokenCache;

    public TokenHolder(ApiConfig config) {
        this.config = config;
        // 初始化 TimedCache，设置缓存过期时间为 Token 有效期减去缓冲时间
        this.tokenCache = new TimedCache<>(TimeUnit.SECONDS.toMillis(config.getTokenExpireSeconds() - DEFAULT_EXPIRE_BUFFER));
        // 设置缓存过期监听器，自动刷新 Token
        this.tokenCache.setListener((key, token) -> refreshToken());
    }

    /**
     * 获取有效的 Token
     *
     * @return 有效的 Token
     */
    public String getValidToken() {
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
    private synchronized String refreshToken() {
        try {
            // 调用认证接口获取新的 Token
            String tokenUrl = config.getAuthUrl();
            HttpRequest request = HttpRequest.post(tokenUrl)
                    .form("grant_type", "client_credentials")
                    .form("client_id", config.getClientId())
                    .form("client_secret", config.getClientSecret());

            HttpResponse response = request.execute();
            if (response.getStatus() != HttpStatus.HTTP_OK) {
                throw new ApiException("获取 Token 失败: " + response.getStatus() + " - " + response.body());
            }

            JSONObject tokenResponse = JSONUtil.parseObj(response.body());
            String newToken = tokenResponse.getStr("access_token");
            long expiresIn = tokenResponse.getLong("expires_in", config.getTokenExpireSeconds());

            // 更新缓存
            tokenCache.put(TOKEN_KEY, newToken, TimeUnit.SECONDS.toMillis(expiresIn) - DEFAULT_EXPIRE_BUFFER);
            log.info("Token 刷新成功，有效期剩余: {} 秒", expiresIn);

            return newToken;
        } catch (Exception e) {
            log.error("刷新 Token 失败", e);
            throw new ApiException("刷新 Token 失败: " + e.getMessage(), e);
        }
    }

    /**
     * 清除 Token 缓存
     */
    public void clearToken() {
        tokenCache.remove(TOKEN_KEY);
        log.info("Token 缓存已清除");
    }
}