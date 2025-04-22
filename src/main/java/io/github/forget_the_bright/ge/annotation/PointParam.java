package io.github.forget_the_bright.ge.annotation;

import io.github.forget_the_bright.ge.constant.common.ValueType;

import java.lang.annotation.*;

/**
 * 用于标记和提供点参数信息的注解
 * 该注解主要用于字段或注解类型，以指定与特定点相关的参数信息
 * 它允许开发者指定参数的默认值，工作坊信息，站点编号以及值类型
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PointParam {
    /**
     * 参数的默认值 点位值
     * 默认情况下，参数值为空字符串
     *
     * @return 参数的默认值
     */
    String value() default "";

    /**
     * 车间的标识
     * 用于指定参数所属的车间
     * 默认情况下，车间为空字符串
     *
     * @return 工作坊的标识
     */
    String workshop() default "";

    /**
     * 站点编号
     * 用于指定参数在特定工作坊中的站点编号
     * 默认情况下，站点编号为空字符串
     *
     * @return 站点编号
     */
    String stationNo() default "";

    /**
     * 值类型
     * 用于指定参数的类型
     * 默认情况下，值类型为PV（Process Variable）
     *
     * @return 值类型
     */
    ValueType type() default ValueType.NULL;

}
