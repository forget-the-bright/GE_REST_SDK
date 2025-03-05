
package io.github.forget_the_bright.ge.core;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import io.github.forget_the_bright.ge.entity.response.base.DataItem;
import io.github.forget_the_bright.ge.entity.response.base.Sample;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * ApiUtil 类提供了与 API 交互的实用工具方法。
 * <p>
 * 这些方法主要用于简化 API 请求的构建和执行，提供了一些通用的功能，如参数验证、请求构建等。
 * </p>
 *
 * @author wanghao
 * @version 1.0
 * @see io.github.forget_the_bright.ge.core.ApiClient
 * @see io.github.forget_the_bright.ge.constant.DataApiEnum
 * @since 2023-10-01
 */
public class ApiUtil {

    /**
     * 如果对象为 null 或空，则返回 null；否则执行给定的函数并返回其结果。
     *
     * @param <T>  输入对象的类型
     * @param <R>  函数返回值的类型
     * @param obj  输入对象
     * @param func 处理对象的函数
     * @return 如果对象为 null 或空，则返回 null；否则返回函数的执行结果
     */
    public static <T, R> R isNullExec(T obj, Function<T, R> func) {
        if (ObjectUtil.isEmpty(obj)) {
            return null;
        } else {
            return func.apply(obj);
        }
    }

    /**
     * 如果对象不为 null 或空，则执行给定的消费者函数。
     *
     * @param <T>  输入对象的类型
     * @param obj  输入对象
     * @param func 处理对象的消费者函数
     */
    public static <T> void isNullExec(T obj, Consumer<T> func) {
        if (ObjectUtil.isNotEmpty(obj)) {
            func.accept(obj);
        }
    }

    /**
     * 如果对象不为 null 或空，则执行给定的提供者函数并返回其结果；否则返回 null。
     *
     * @param <T>  输入对象的类型
     * @param <R>  提供者函数返回值的类型
     * @param obj  输入对象
     * @param func 提供者函数
     * @return 如果对象不为 null 或空，则返回提供者函数的执行结果；否则返回 null
     */
    public static <T, R> R isNullExec(T obj, Supplier<R> func) {
        if (ObjectUtil.isNotEmpty(obj)) {
            return func.get();
        } else {
            return null;
        }
    }

    /**
     * 根据标签名将列表中的第一个数据项转换为映射
     * 此方法用于处理每个数据项的单个样本值
     * 如果数据项的样本列表为空，将使用默认值"0"
     *
     * @param data 包含多个数据项的列表
     * @return 返回一个映射，其中键是标签名，值是数据项的第一个样本值
     */
    public static Map<String, Object> convertOneDataByTagNames(List<DataItem> data) {
        // 使用Java 8 Stream API根据标签名收集数据项，对于每个标签名，提取其样本列表的第一个值
        Map<String, Object> collect = data.stream().collect(Collectors.toMap(DataItem::getTagName, dataItem -> {
            // 处理样本列表可能为空的情况，如果为空或null，则使用空列表或默认值避免空指针异常
            String value = Optional.ofNullable(dataItem.getSamples())
                    .orElse(ListUtil.empty())
                    .stream()
                    .findFirst()
                    .orElse(new Sample().setValue("0"))
                    .getValue();
            return value;
        }));
        return collect;
    }

    /**
     * 根据标签名将列表中的所有数据项转换为映射
     * 此方法用于处理每个数据项的所有样本值
     * 如果数据项的样本列表为空，将返回一个空列表
     *
     * @param data 包含多个数据项的列表
     * @return 返回一个映射，其中键是标签名，值是数据项的所有样本值的列表
     */
    public static Map<String, Object> convertDataByTagNames(List<DataItem> data) {
        // 使用Java 8 Stream API根据标签名收集数据项，对于每个标签名，提取其样本列表的所有值
        Map<String, Object> collect = data.stream().collect(Collectors.toMap(DataItem::getTagName, dataItem -> {
            // 处理样本列表可能为空的情况，如果为空或null，则使用空列表避免空指针异常
            List<String> values = Optional.ofNullable(dataItem.getSamples())
                    .orElse(ListUtil.empty())
                    .stream().map(Sample::getValue).collect(Collectors.toList());
            return values;
        }));
        return collect;
    }
}
