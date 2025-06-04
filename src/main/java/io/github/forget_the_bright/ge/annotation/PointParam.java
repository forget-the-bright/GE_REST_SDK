package io.github.forget_the_bright.ge.annotation;

import io.github.forget_the_bright.ge.constant.common.ValueType;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

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
     * 获取一组值
     * <p>
     * 此方法用于返回一个字符串数组，数组中包含了特定的值集合这些值可以是预定义的，
     * 从其他来源获取的，或者是通过特定逻辑计算得出的此方法不接受任何参数，
     * 且返回的数组可以直接用于进一步的处理或展示
     *
     * @return String[] 返回一个字符串数组，包含了一组特定的值
     */
    String[] values() default {};

    /**
     * 默认提供一个空字符串数组，用于获取字段的值
     * 这个方法主要用于获取与字段相关的值的数组
     * 默认值为空数组，意味着在没有特别配置的情况下，它将返回一个空的字符串数组
     */
    String[] fieldValues() default {};

    /**
     * 默认提供一个空字符串，用于指定字段的名称
     * 这个方法主要用于指定或获取字段的名称
     * 默认值为空字符串，表示在没有特别配置时，它将返回一个空的字符串
     */
    String fieldName() default "";

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

    String simpleExpression() default "";

    int scale() default 2;

    /**
     * PV 走瞬时值接口
     * SUM 走累计值接口
     */
    String queryType() default "PV";

    /**
     * 计算累计的开始小时
     */
    int sumMetaHour() default 8;

    /**
     * 计算累计的时间单位
     */
    TimeUnit sumMetaUnit() default TimeUnit.DAYS;

    /**
     * 累计值接口的间隔时间
     */
    int sumMetaInterval() default 1;

    /**
     * 值类型
     * 用于指定参数的类型
     * 默认情况下，值类型为PV（Process Variable）
     *
     * @return 值类型
     */
    ValueType type() default ValueType.NULL;

}
