
package io.github.forget_the_bright.ge.core;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.*;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import io.github.forget_the_bright.ge.entity.HistorianUnit;
import io.github.forget_the_bright.ge.entity.response.base.DataItem;
import io.github.forget_the_bright.ge.entity.response.base.Sample;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
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
    public static Map<String, List<String>> convertDataByTagNames(List<DataItem> data) {
        // 使用Java 8 Stream API根据标签名收集数据项，对于每个标签名，提取其样本列表的所有值
        Map<String, List<String>> collect = data.stream().collect(Collectors.toMap(DataItem::getTagName, dataItem -> {
            // 处理样本列表可能为空的情况，如果为空或null，则使用空列表避免空指针异常
            List<String> values = Optional.ofNullable(dataItem.getSamples())
                    .orElse(ListUtil.empty())
                    .stream().map(Sample::getValue).collect(Collectors.toList());
            return values;
        }));
        return collect;
    }

    /**
     * 根据数据项列表转换时间戳为Date对象列表
     * 此方法主要用于从数据项集合中提取时间戳，并将其转换为Date对象列表
     * 如果数据项为空或第一个数据项的样本为空，则返回空列表
     *
     * @param data 数据项列表，包含时间戳信息
     * @return Date对象列表，表示转换后的时间
     */
    public static List<Date> convertTimeByTagNames(List<DataItem> data) {
        // 使用流处理，安全地处理可能为空的情况，并将时间戳转换为Date对象
        List<Date> dataList = Optional.ofNullable(data.get(0).getSamples())
                .orElse(ListUtil.empty())
                .stream()
                .map(Sample::getTimeStamp)
                .collect(Collectors.toList());
        return dataList;
    }

    /**
     * 根据数据项列表和指定格式转换时间戳为字符串列表
     * 此方法主要用于从数据项集合中提取时间戳，并将其按照指定的格式转换为字符串列表
     * 如果数据项为空或第一个数据项的样本为空，则返回空列表
     *
     * @param data   数据项列表，包含时间戳信息
     * @param format 日期格式字符串，用于格式化时间戳
     * @return 字符串列表，表示按照指定格式转换后的时间
     */
    public static List<String> convertFormatTimeByTagNames(List<DataItem> data, String format) {
        // 使用流处理，安全地处理可能为空的情况，并将时间戳转换为指定格式的字符串
        List<String> dataList = Optional.ofNullable(data.get(0).getSamples())
                .orElse(ListUtil.empty())
                .stream()
                .map(Sample::getTimeStamp)
                .map(date -> DateUtil.format(date, format))
                .collect(Collectors.toList());
        return dataList;
    }

    /**
     * 根据数据项列表转换时间戳为默认格式的字符串列表
     * 此方法主要用于从数据项集合中提取时间戳，并将其按照默认格式（规范日期时间格式）转换为字符串列表
     * 如果数据项为空或第一个数据项的样本为空，则返回空列表
     * 此方法调用了另一个重载方法，使用默认的日期格式
     *
     * @param data 数据项列表，包含时间戳信息
     * @return 字符串列表，表示按照默认格式转换后的时间
     */
    public static List<String> convertFormatTimeByTagNames(List<DataItem> data) {
        // 调用重载方法，使用默认的日期格式
        return convertFormatTimeByTagNames(data, DatePattern.NORM_DATETIME_PATTERN);
    }

    /**
     * 根据给定的日期和时间单位计算历史单元的开始日期、结束日期、计数和间隔时间
     *
     * @param metaDate     起始日期，如果为null，则使用当前日期作为起始日期
     * @param total        总时间的量值
     * @param totalUnit    总时间的时间单位
     * @param interval     间隔时间的量值
     * @param intervalUnit 间隔时间的时间单位
     * @return 返回一个包含开始日期、结束日期、计数和间隔时间毫秒数的HistorianUnit对象
     */
    public static HistorianUnit calculateCountAndTimes(Date metaDate, int total, TimeUnit totalUnit, int interval, TimeUnit intervalUnit) {
        // 确定结束日期，如果metaDate为null，则使用当前日期
        Date end = Optional.ofNullable(metaDate).orElse(new DateTime());
        Date begin = null;
        int count = 0;

        // 计算开始日期，通过结束日期向前偏移总时间得到
        begin = DateUtil.offset(end, convertToDateField(totalUnit), -total);

        // 计算计数，通过总时间除以间隔时间得到
        long totalDurationInMinutes = totalUnit.toMinutes(total);
        long intervalDurationInMinutes = intervalUnit.toMinutes(interval);
        count = (int) (totalDurationInMinutes / intervalDurationInMinutes);

        // 计算间隔时间的毫秒数
        long intervalMs = intervalUnit.toMillis(interval);

        // 返回包含开始日期、结束日期、计数和间隔时间毫秒数的HistorianUnit对象
        return new HistorianUnit().setCount(count).setBegin(begin).setEnd(end).setIntervalMs(intervalMs);
    }

    public static HistorianUnit calculateCountAndTimes(int total, TimeUnit totalUnit, int interval, TimeUnit intervalUnit) {
        return calculateCountAndTimes(null, total, totalUnit, interval, intervalUnit);
    }

    /**
     * 将给定的时间单位转换为DateField对象
     * 此方法用于处理时间单位的转换，将其转换为DateField对象，以便在日期操作中使用
     *
     * @param timeUnit 时间单位，用于表示要转换的原始时间值
     * @return DateField对象，表示转换后的日期字段
     */
    public static DateField convertToDateField(TimeUnit timeUnit) {
        switch (timeUnit) {
            case DAYS:
                return DateField.DAY_OF_YEAR;
            case HOURS:
                return DateField.HOUR;
            case MINUTES:
                return DateField.MINUTE;
            case SECONDS:
                return DateField.SECOND;
            case MILLISECONDS:
                return DateField.MILLISECOND;
            default:
                throw new IllegalArgumentException("Unsupported TimeUnit for conversion to DateField");
        }
    }
}
