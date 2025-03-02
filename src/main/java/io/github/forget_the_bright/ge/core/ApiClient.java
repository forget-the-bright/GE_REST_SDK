package io.github.forget_the_bright.ge.core;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.forget_the_bright.ge.config.ApiConfig;
import io.github.forget_the_bright.ge.constant.child.ApiModule;
import io.github.forget_the_bright.ge.constant.child.ParamPosition;
import io.github.forget_the_bright.ge.exception.ApiException;
import org.springframework.http.HttpMethod;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用API客户端
 */
@Slf4j
@Component
public class ApiClient {

    private final ApiConfig config;

    @Autowired
    public ApiClient(ApiConfig config) {
        this.config = config;
    }

    /**
     * 执行API请求
     *
     * @param module  模块枚举
     * @param apiEnum 接口枚举
     * @param <T>     返回类型
     * @return 响应结果
     */
    public <T> T execute(ApiModule module, Enum<?> apiEnum, Object body) {
        return execute(module, apiEnum, null, body);
    }

    public <T> T execute(ApiModule module, Enum<?> apiEnum, Map<String, Object> params) {
        return execute(module, apiEnum, params, null);
    }

    public <T> T execute(ApiModule module, Enum<?> apiEnum, Map<String, Object> params, Object body) {
        // 1. 构建完整URL
        String fullUrl = buildUrl(module, apiEnum);

        // 2. 创建请求
        HttpRequest request = createRequest(apiEnum, fullUrl, params, body);

        // 3. 添加认证信息
        addAuthHeader(request);

        // 4. 执行请求并处理响应
        return (T) handleResponse(request.execute());
    }

    /**
     * 构建完整URL
     */
    private String buildUrl(ApiModule module, Enum<?> apiEnum) {
        String basePath = config.getEndpoints().getOrDefault(module.getCode(), "");
        String apiPath = getPathFromEnum(apiEnum);
        return StrUtil.format("{}{}{}", config.getBaseUrl(), basePath, apiPath);
    }

    /**
     * 创建HTTP请求
     */
    private HttpRequest createRequest(Enum<?> apiEnum, String url, Map<String, Object> params, Object body) {
        Method method = getMethodFromEnum(apiEnum);
        ParamPosition primaryParamPosition = getPrimaryParamPositionFromEnum(apiEnum);
        ParamPosition secondaryParamPosition = getSecondaryParamPositionFromEnum(apiEnum);

        HttpRequest request = new HttpRequest(url)
                .method(method)
                .contentType("application/json");

        fillMessage(request, primaryParamPosition, url, params, body);
        fillMessage(request, secondaryParamPosition, url, params, body);
        return request;
    }

    private void fillMessage(HttpRequest request, ParamPosition paramPosition, String url, Map<String, Object> params, Object body) {
        // 根据参数位置处理参数
        switch (paramPosition) {
            case QUERY:
                if (ObjectUtil.isEmpty(params)) {
                    throw new ApiException("params参数不能为空");
                } else if (params instanceof Map) {
                    request.form(params);
                } else {
                    throw new ApiException("Query参数必须是Map类型");
                }
                break;
            case PATH:
                if (ObjectUtil.isEmpty(params)) {
                    throw new ApiException("params参数不能为空");
                } else if (params instanceof Map) {
                    request.setUrl(replacePathParams(url, params));
                } else {
                    throw new ApiException("Path参数必须是Map类型");
                }
                break;
            case BODY:

                if (ObjectUtil.isEmpty(body)) {
                    throw new ApiException("body参数不能为空");
                } else if (body instanceof String) {
                    request.body((String) body);
                } else {
                    request.body(JSONObject.toJSONString(body));
                }
                break;
            case FORM:
                if (ObjectUtil.isEmpty(params)) {
                    throw new ApiException("params参数不能为空");
                } else if (params instanceof Map) {
                    request.form(params);
                } else {
                    throw new ApiException("Form参数必须是Map类型");
                }
                break;
            case NONE:
                break;
            default:
                throw new ApiException("不支持的参数位置类型: " + paramPosition);
        }
    }

    private static final Pattern PATH_PARAM_PATTERN = Pattern.compile("\\{(.*?)\\}");

    /**
     * 替换URL中的路径参数
     *
     * @param url    原始URL
     * @param params 路径参数Map
     * @return 替换后的URL
     */
    public static String replacePathParams(String url, Map<?, ?> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }

        Matcher matcher = PATH_PARAM_PATTERN.matcher(url);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String paramName = matcher.group(1);
            Object paramValue = params.get(paramName);
            if (paramValue == null) {
                throw new ApiException("缺少路径参数: " + paramName);
            }
            matcher.appendReplacement(result, paramValue.toString());
        }
        matcher.appendTail(result);
        return result.toString();
    }

    /**
     * 添加认证头
     */
    private void addAuthHeader(HttpRequest request) {
        if ("basic".equalsIgnoreCase(config.getAuthType())) {
            String authHeader = "Basic " + Base64.encode(config.getClientId() + ":" + config.getClientSecret());
            request.header("Authorization", authHeader);
        } else if ("bearer".equalsIgnoreCase(config.getAuthType())) {
            // 这里可以实现Bearer Token的逻辑
            request.header("Authorization", "Bearer " + getToken());
        }
    }

    /**
     * 处理响应
     */
    private JSONObject handleResponse(HttpResponse response) {
        if (response.getStatus() != HttpStatus.HTTP_OK) {
            throw new ApiException("API调用失败: " + response.getStatus() + " - " + response.body());
        }

        String responseBody = response.body();
        log.debug("API响应: {}", responseBody);

        return JSONObject.parseObject(responseBody);
    }

    /**
     * 获取Token（示例）
     */
    private String getToken() {
        // 这里可以实现Token的获取和刷新逻辑
        return "your_access_token";
    }

    /**
     * 从枚举中获取路径
     */
    private String getPathFromEnum(Enum<?> apiEnum) {
        try {
            return (String) apiEnum.getClass().getMethod("getPath").invoke(apiEnum);
        } catch (Exception e) {
            throw new ApiException("无法从枚举中获取路径", e);
        }
    }

    /**
     * 从枚举中获取HTTP方法
     */
    private Method getMethodFromEnum(Enum<?> apiEnum) {
        try {
            HttpMethod httpMethod = (HttpMethod) apiEnum.getClass().getMethod("getMethod").invoke(apiEnum);
            return Method.valueOf(httpMethod.name());
        } catch (Exception e) {
            throw new ApiException("无法从枚举中获取HTTP方法", e);
        }
    }

    /**
     * 从枚举中获取参数位置
     */
    private ParamPosition getPrimaryParamPositionFromEnum(Enum<?> apiEnum) {
        try {
            return (ParamPosition) apiEnum.getClass().getMethod("getPrimaryParamPosition").invoke(apiEnum);
        } catch (Exception e) {
            throw new ApiException("无法从枚举中获取参数位置", e);
        }
    }

    private ParamPosition getSecondaryParamPositionFromEnum(Enum<?> apiEnum) {
        try {
            return (ParamPosition) apiEnum.getClass().getMethod("getSecondaryParamPosition").invoke(apiEnum);
        } catch (Exception e) {
            throw new ApiException("无法从枚举中获取参数位置", e);
        }
    }

    /**
     * 从枚举中获取返回类型
     */
    private Class<?> getReturnType(Enum<?> apiEnum) {
        try {
            String entityType = (String) apiEnum.getClass().getMethod("getEntityType").invoke(apiEnum);
            if (entityType == null) {
                return JSONObject.class;
            }
            return Class.forName("com.ge.datacollection.model." + entityType);
        } catch (Exception e) {
            throw new ApiException("无法从枚举中获取返回类型", e);
        }
    }
}