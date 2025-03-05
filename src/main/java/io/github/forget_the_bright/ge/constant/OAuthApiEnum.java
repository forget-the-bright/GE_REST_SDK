package io.github.forget_the_bright.ge.constant;

import io.github.forget_the_bright.ge.constant.attach.ParamPosition;
import lombok.Getter;
import lombok.ToString;
import cn.hutool.http.Method;
/**
 * OAuth 模块接口枚举 (OAuth API Enum)
 * <p>
 * 该枚举类定义了 OAuth 模块的所有接口及其相关信息。
 */
@ToString
@Getter
public enum OAuthApiEnum {

    /**
     * 获取 OAuth Token 接口
     * <p>
     * 此接口允许通过用户名和密码等信息获取 OAuth Token。
     */
    GET_TOKEN(
            "获取 OAuth Token",
            "/uaa/oauth/token",
            Method.POST,
            ParamPosition.QUERY, // 使用 QUERY 参数传递认证信息
            ParamPosition.NONE,
            null,null
    );

    // 成员变量
    private final String desc; // 接口描述
    private final String path; // 接口路径
    private final Method method; // 请求方法
    private final ParamPosition primaryParamPosition; // 主要参数位置
    private final ParamPosition secondaryParamPosition; // 次要参数位置
    private final Class<?> entityType; //body请求体对于的实体类型，没有就是null
    private final Class<?> resultType; // 返回值实体类

    // 构造函数
    OAuthApiEnum(String desc, String path, Method method,
                 ParamPosition primaryParamPosition, ParamPosition secondaryParamPosition,
                 Class<?> entityType,
                 Class<?> resultType) {
        this.desc = desc;
        this.path = path;
        this.method = method;
        this.primaryParamPosition = primaryParamPosition;
        this.secondaryParamPosition = secondaryParamPosition;
        this.entityType = entityType;
        this.resultType = resultType;
    }
}
