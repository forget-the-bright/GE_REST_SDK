package io.github.forget_the_bright.ge.core;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.*;
import cn.hutool.http.*;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.github.forget_the_bright.ge.config.ApiConfig;
import io.github.forget_the_bright.ge.constant.attach.ApiModule;
import io.github.forget_the_bright.ge.constant.attach.ParamPosition;
import io.github.forget_the_bright.ge.exception.ApiException;

import cn.hutool.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用API客户端，提供RESTful API请求的统一处理
 *
 * <p>支持动态URL构建、多类型参数处理、认证机制和响应处理等核心功能</p>
 */
@Slf4j
@Component
public class ApiClient {

    private static ApiConfig config;

    /**
     * 构造API客户端实例
     *
     * @param config API配置信息，包含基础URL、客户端凭证等
     */
    @Autowired
    public ApiClient(ApiConfig config) {
        this.config = config;
    }

    /**
     * 执行带请求体的API调用
     *
     * @param module  API模块配置
     * @param apiEnum API接口枚举定义
     * @param body    请求体对象
     * @return 解析后的JSON响应
     */
    public static <T> T execute(ApiModule module, Enum<?> apiEnum, Object body) {
        return execute(module, apiEnum, null, body);
    }

    /**
     * 执行带参数的API调用
     *
     * @param module  API模块配置
     * @param apiEnum API接口枚举定义
     * @param params  请求参数集合
     * @return 解析后的JSON响应
     */
    public static <T> T execute(ApiModule module, Enum<?> apiEnum, Map<String, Object> params) {
        return execute(module, apiEnum, params, null);
    }

    /**
     * 执行无参数和请求体的API调用
     *
     * @param module  API模块配置
     * @param apiEnum API接口枚举定义
     * @return 解析后的JSON响应
     */
    public static <T> T execute(ApiModule module, Enum<?> apiEnum) {
        return execute(module, apiEnum, null, null);
    }

    /**
     * 执行API调用
     *
     * @param module  API模块配置
     * @param apiEnum API接口枚举定义
     * @param params  请求参数集合
     * @param body    请求体对象
     * @return 解析后的JSON响应
     */
    public static <T> T execute(ApiModule module, Enum<?> apiEnum, Map<String, Object> params, Object body) {
        String fullUrl = buildUrl(module, apiEnum);
        HttpRequest request = createRequest(apiEnum, fullUrl, params, body);
        addAuthHeader(request, module);
        return (T) handleResponse(request, apiEnum);
    }

    /**
     * 构建完整URL
     *
     * @param module  API模块配置
     * @param apiEnum API接口枚举定义
     * @return 完整请求URL
     */
    private static String buildUrl(ApiModule module, Enum<?> apiEnum) {
        String contextPath = module.getContextPath();
        String apiPath = getPathFromEnum(apiEnum);
        return StrUtil.format("{}{}{}", config.getBaseUrl(), contextPath, apiPath);
    }

    /**
     * 创建HTTP请求对象
     *
     * @param apiEnum API接口枚举定义
     * @param url     完整请求URL
     * @param params  请求参数集合
     * @param body    请求体对象
     * @return 配置完成的HttpRequest对象
     */
    private static HttpRequest createRequest(Enum<?> apiEnum, String url, Map<String, Object> params, Object body) {
        Method method = getMethodFromEnum(apiEnum);
        ParamPosition primaryParamPosition = getPrimaryParamPositionFromEnum(apiEnum);
        ParamPosition secondaryParamPosition = getSecondaryParamPositionFromEnum(apiEnum);

        HttpRequest request = new HttpRequest(url)
                .setConnectionTimeout(config.getConnectionTimeout())
                .setReadTimeout(config.getReadTimeout())
                .method(method);

        fillMessage(request, primaryParamPosition, url, params, body);
        fillMessage(request, secondaryParamPosition, url, params, body);
        return request;
    }

    /**
     * 填充请求参数到指定位置
     *
     * @param request       HTTP请求对象
     * @param paramPosition 参数位置枚举
     * @param url           当前请求URL（用于路径参数替换）
     * @param params        请求参数集合
     * @param body          请求体对象
     * @throws ApiException 参数不符合要求时抛出
     */
    private static void fillMessage(HttpRequest request, ParamPosition paramPosition, String url, Map<String, Object> params, Object body) {
        switch (paramPosition) {
            case QUERY:
                if (ObjectUtil.isEmpty(params)) {
                    throw new ApiException("params参数不能为空");
                } else if (params instanceof Map) {
                    if (CollUtil.isNotEmpty(params)) {
                        String finalUrl = HttpUtil.urlWithForm(url, params, CharsetUtil.CHARSET_UTF_8, true);
                        request.setUrl(finalUrl);
                    }
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
                    request.body(JSONObject.toJSONString(body, SerializerFeature.WriteEnumUsingToString));
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
     * @throws ApiException 缺少路径参数时抛出
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
     *
     * @param request HTTP请求对象
     * @param module  API模块配置
     * @throws ApiException 不支持的认证类型时抛出
     */
    private static void addAuthHeader(HttpRequest request, ApiModule module) {
        String prefix = module.getAuthType().getType();
        String subfix = "";
        switch (module.getAuthType()) {
            case BASIC:
                subfix = Base64.encode(config.getClientId() + ":" + config.getClientSecret());
                break;
            case BEARER:
                subfix = TokenHolder.getValidToken();
                break;
            default:
                log.warn("不支持的认证类型: {}", module.getAuthType());
                break;
        }
        String authorization = StrUtil.format("{} {}", prefix, subfix);
        request.header("Authorization", authorization);
    }

    /**
     * 处理HTTP响应结果
     *
     * @param request 已发送的HTTP请求对象
     * @return 解析后的JSON响应
     * @throws ApiException HTTP状态码非200时抛出
     */
    private static Object handleResponse(HttpRequest request, Enum<?> apiEnum) {
        HttpResponse response = request.execute();
        log.debug("API请求: {} {}\n{}", request.getMethod(), request.getUrl(), request);
        if (response.getStatus() == HttpStatus.HTTP_UNAUTHORIZED) {
            TokenHolder.clearToken();
        }
        if (response.getStatus() != HttpStatus.HTTP_OK) {
            throw new ApiException("API调用失败: " + response.getStatus() + " - " + response.body());
        }
        String responseBody = response.body();
        log.debug("API响应: {}", responseBody);
        Class<?> returnType = getReturnType(apiEnum);
        if (returnType == null){
            return JSONObject.parseObject(responseBody);
        }
        return JSONObject.parseObject(responseBody, returnType);
    }

    /**
     * 从枚举实例获取接口路径
     *
     * @param apiEnum API接口枚举定义
     * @return 接口路径字符串
     * @throws ApiException 反射获取路径失败时抛出
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
     * 从枚举实例获取HTTP方法
     *
     * @param apiEnum API接口枚举定义
     * @return HTTP方法枚举
     * @throws ApiException 反射获取方法失败时抛出
     */
    private static Method getMethodFromEnum(Enum<?> apiEnum) {
        try {
            java.lang.reflect.Method getMethod = ReflectUtil.getMethod(apiEnum.getClass(), "getMethod");
            return (Method) getMethod.invoke(apiEnum);
        } catch (Exception e) {
            throw new ApiException("无法从枚举中获取HTTP方法", e);
        }
    }

    /**
     * 从枚举实例获取主参数位置
     *
     * @param apiEnum API接口枚举定义
     * @return 主参数位置枚举
     * @throws ApiException 反射获取参数位置失败时抛出
     */
    private static ParamPosition getPrimaryParamPositionFromEnum(Enum<?> apiEnum) {
        try {
            return (ParamPosition) apiEnum.getClass().getMethod("getPrimaryParamPosition").invoke(apiEnum);
        } catch (Exception e) {
            throw new ApiException("无法从枚举中获取参数位置", e);
        }
    }

    /**
     * 从枚举实例获取次参数位置
     *
     * @param apiEnum API接口枚举定义
     * @return 次参数位置枚举
     * @throws ApiException 反射获取参数位置失败时抛出
     */
    private static ParamPosition getSecondaryParamPositionFromEnum(Enum<?> apiEnum) {
        try {
            return (ParamPosition) apiEnum.getClass().getMethod("getSecondaryParamPosition").invoke(apiEnum);
        } catch (Exception e) {
            throw new ApiException("无法从枚举中获取参数位置", e);
        }
    }

    /**
     * 从枚举实例获取返回类型
     *
     * @param apiEnum API接口枚举定义
     * @return 返回类型Class对象
     * @throws ApiException 反射获取返回类型失败时抛出
     */
    private static Class<?> getReturnType(Enum<?> apiEnum) {
        try {
            Class<?> entityType = (Class<?>) apiEnum.getClass().getMethod("getResultType").invoke(apiEnum);
            return entityType;
        } catch (Exception e) {
            throw new ApiException("无法从枚举中获取返回类型", e);
        }
    }
}
