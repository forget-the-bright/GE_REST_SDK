package io.github.forget_the_bright.ge.core;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.http.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.forget_the_bright.ge.config.ApiConfig;
import io.github.forget_the_bright.ge.constant.child.ApiModule;
import io.github.forget_the_bright.ge.constant.child.ParamPosition;
import io.github.forget_the_bright.ge.exception.ApiException;

import cn.hutool.core.util.StrUtil;
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

    private static ApiConfig config;

    @Autowired
    public ApiClient(ApiConfig config) {
        this.config = config;
    }

    /**
     * 执行API请求
     *
     * @param module  模块枚举
     * @param apiEnum 接口枚举
     * @return 响应结果
     */
    public static JSONObject execute(ApiModule module, Enum<?> apiEnum, Object body) {
        return execute(module, apiEnum, null, body);
    }

    public static JSONObject execute(ApiModule module, Enum<?> apiEnum, Map<String, Object> params) {
        return execute(module, apiEnum, params, null);
    }
    public static JSONObject execute(ApiModule module, Enum<?> apiEnum) {
        return execute(module, apiEnum, null, null);
    }
    public static JSONObject execute(ApiModule module, Enum<?> apiEnum, Map<String, Object> params, Object body) {
        // 1. 构建完整URL
        String fullUrl = buildUrl(module, apiEnum);

        // 2. 创建请求
        HttpRequest request = createRequest(apiEnum, fullUrl, params, body);

        // 3. 添加认证信息
        addAuthHeader(request, module);

        // 4. 执行请求并处理响应
        return handleResponse(request);
    }

    /**
     * 构建完整URL
     */
    private static String buildUrl(ApiModule module, Enum<?> apiEnum) {
        String contextPath = module.getContextPath();
        String apiPath = getPathFromEnum(apiEnum);
        return StrUtil.format("{}{}{}", config.getBaseUrl(), contextPath, apiPath);
    }

    /**
     * 创建HTTP请求
     */
    private static HttpRequest createRequest(Enum<?> apiEnum, String url, Map<String, Object> params, Object body) {
        Method method = getMethodFromEnum(apiEnum);
        ParamPosition primaryParamPosition = getPrimaryParamPositionFromEnum(apiEnum);
        ParamPosition secondaryParamPosition = getSecondaryParamPositionFromEnum(apiEnum);

        HttpRequest request = new HttpRequest(url)
                .method(method);

        fillMessage(request, primaryParamPosition, url, params, body);
        fillMessage(request, secondaryParamPosition, url, params, body);
        return request;
    }

    private static void fillMessage(HttpRequest request, ParamPosition paramPosition, String url, Map<String, Object> params, Object body) {
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
    private static void addAuthHeader(HttpRequest request, ApiModule module) {
        String prefix = module.getAuthType().getType();
        String subfix = "";
        switch (module.getAuthType()) {
            case BASIC: {
                subfix = Base64.encode(config.getClientId() + ":" + config.getClientSecret());
                break;
            }
            case BEARER: {
                // 这里可以实现Bearer Token的逻辑
                subfix = TokenHolder.getValidToken();
                break;
            }
            default:
                new ApiException("不支持的认证类型:" + module.getAuthType());
                log.warn("不支持的认证类型: {}", module.getAuthType());
                break;
        }
        String authorization = StrUtil.format("{} {}", prefix, subfix);
        request.header("Authorization", authorization);
    }

    /**
     * 处理HTTP响应
     * <p>
     * 此方法主要用于处理HTTP请求的响应结果，包括验证响应状态码和解析响应体
     * 当接收到未授权状态时，会清除保存的Token
     * 如果响应状态码不是HTTP_OK，则抛出ApiException异常
     *
     * @param request HTTP请求对象
     * @return 解析后的JSON对象
     * @throws ApiException 当响应状态码不是HTTP_OK时抛出此异常
     */
    private static JSONObject handleResponse(HttpRequest request) {
        HttpResponse response = request.execute();
        log.debug("API请求: {} {}\n{}", request.getMethod(), request.getUrl(), request);
        // 检查是否需要重新认证
        if (response.getStatus() == HttpStatus.HTTP_UNAUTHORIZED) {
            TokenHolder.clearToken();
        }
        // 检查响应状态码是否为HTTP_OK
        if (response.getStatus() != HttpStatus.HTTP_OK) {
            throw new ApiException("API调用失败: " + response.getStatus() + " - " + response.body());
        }
        // 获取响应体
        String responseBody = response.body();
        // 记录API响应日志
        log.debug("API响应: {}", responseBody);
        // 解析并返回JSON对象
        return JSONObject.parseObject(responseBody);
    }

    /**
     * 从枚举中获取路径
     */
    private static String getPathFromEnum(Enum<?> apiEnum) {
        try {
            java.lang.reflect.Method getPath = ReflectUtil.getMethod(apiEnum.getClass(), "getPath");
            return Convert.toStr(getPath.invoke(apiEnum));
        } catch (Exception e) {
            throw new ApiException("无法从枚举中获取路径", e);
        }
    }

    /**
     * 从枚举中获取HTTP方法
     */
    private static Method getMethodFromEnum(Enum<?> apiEnum) {
        try {
            java.lang.reflect.Method getMethod = ReflectUtil.getMethod(apiEnum.getClass(), "getMethod");
            Method httpMethod = (Method) getMethod.invoke(apiEnum);
            return httpMethod;
        } catch (Exception e) {
            throw new ApiException("无法从枚举中获取HTTP方法", e);
        }
    }

    /**
     * 从枚举中获取参数位置
     */
    private static ParamPosition getPrimaryParamPositionFromEnum(Enum<?> apiEnum) {
        try {
            return (ParamPosition) apiEnum.getClass().getMethod("getPrimaryParamPosition").invoke(apiEnum);
        } catch (Exception e) {
            throw new ApiException("无法从枚举中获取参数位置", e);
        }
    }

    private static ParamPosition getSecondaryParamPositionFromEnum(Enum<?> apiEnum) {
        try {
            return (ParamPosition) apiEnum.getClass().getMethod("getSecondaryParamPosition").invoke(apiEnum);
        } catch (Exception e) {
            throw new ApiException("无法从枚举中获取参数位置", e);
        }
    }

    /**
     * 从枚举中获取返回类型
     */
    private static Class<?> getReturnType(Enum<?> apiEnum) {
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