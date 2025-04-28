
package io.github.forget_the_bright.ge.core;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.*;
import cn.hutool.core.map.BiMap;
import cn.hutool.core.util.*;
import com.alibaba.fastjson.JSONObject;
import io.github.forget_the_bright.ge.annotation.PointParam;
import io.github.forget_the_bright.ge.constant.common.Quality;
import io.github.forget_the_bright.ge.constant.common.ValueType;
import io.github.forget_the_bright.ge.entity.HistorianUnit;
import io.github.forget_the_bright.ge.entity.request.tags.TagNamesEntity;
import io.github.forget_the_bright.ge.entity.response.DataResult;
import io.github.forget_the_bright.ge.entity.response.base.DataItem;
import io.github.forget_the_bright.ge.entity.response.base.Sample;
import io.github.forget_the_bright.ge.exception.ApiException;
import io.github.forget_the_bright.ge.service.DataApiInvoker;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
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

    //region 空处理方法
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
    //endregion

    //region 点位数据 数据结构转换
    /**
     * 根据标签名将列表中的第一个数据项转换为映射
     * 此方法用于处理每个数据项的单个样本值
     * 如果数据项的样本列表为空，将使用默认值"0"
     *
     * @param data 包含多个数据项的列表
     * @return 返回一个映射，其中键是标签名，值是数据项的第一个样本值
     */
    public static Map<String, Object> convertOneDataByTagNames(List<DataItem> data) {
        return convertOneDataByTagNames(data, "0");
    }

    public static Map<String, Object> convertOneDataByTagNames(List<DataItem> data, String defaultValue) {
        return convertOneDataByTagNames(data, "0", 0);
    }

    /**
     * 根据标签名称转换单个数据项到一个映射中
     * 此方法用于处理一组数据项，每个数据项包含多个标签和对应的值通过指定的标签名称，
     * 方法将每个数据项中的标签值与数据项关联起来，如果标签不存在，则使用默认值
     *
     * @param data         包含多个DataItem对象的列表，每个DataItem对象包含一组标签和值
     * @param defaultValue 如果指定的标签在数据项中不存在时，使用的默认值
     * @param scale        保留有效数字的位数
     * @return 返回一个映射，其中键是数据项中的标签值（如果存在），值是数据项本身
     */
    public static Map<String, Object> convertOneDataByTagNames(List<DataItem> data, String defaultValue, Integer scale) {
        // 使用Java 8 Stream API根据标签名收集数据项，对于每个标签名，提取其样本列表的第一个值
        Map<String, Object> collect = data.stream().collect(Collectors.toMap(DataItem::getTagName, dataItem -> {
            // 处理样本列表可能为空的情况，如果为空或null，则使用空列表或默认值避免空指针异常
            Sample sample = Optional.ofNullable(dataItem.getSamples())
                    .orElse(ListUtil.empty())
                    .stream()
                    .findFirst()
                    .orElse(new Sample().setQuality(Quality.GOOD).setValue(defaultValue));
            if (sample.getQuality() == Quality.BAD) {
                sample.setValue(defaultValue);
            }
            String value = sample.getValue();
            if (!defaultValue.equals(value) && scale > 0) { // 如果值不是默认值，则保留指定位数
                value = retainSignificantDecimals(value, scale);
            }
            return value;
        }, (oldValue, newValue) -> newValue));
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
                    .stream()
                    .map(Sample::getValue).collect(Collectors.toList());
            return values;
        }, (oldValue, newValue) -> newValue));
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
    //endregion

    //region 计算历史单元,获取查询时间各个参数
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
    public static HistorianUnit calculateCountAndTimes(Date metaDate, int total, TimeUnit totalUnit, int interval, TimeUnit intervalUnit, Boolean isRound) {
        // 确定结束日期，如果metaDate为null，则使用当前日期
        Date end = Optional.ofNullable(metaDate).orElse(new DateTime());
        if (Optional.ofNullable(isRound).orElse(Boolean.FALSE)) {
            end = getDateForRoundTime(end, intervalUnit, interval);
        }
        Date begin = null;
        int count = 0;

        // 计算开始日期，通过结束日期向前偏移总时间得到
        begin = DateUtil.offset(end, convertToDateField(totalUnit), -total);

        // 计算计数，通过总时间除以间隔时间得到
        long totalDurationInMinutes = totalUnit.toSeconds(total);
        long intervalDurationInMinutes = intervalUnit.toSeconds(interval);
        count = (int) (totalDurationInMinutes / intervalDurationInMinutes);

        // 计算间隔时间的毫秒数
        long intervalMs = intervalUnit.toMillis(interval);

        // 返回包含开始日期、结束日期、计数和间隔时间毫秒数的HistorianUnit对象
        return new HistorianUnit().setCount(count).setBegin(begin).setEnd(end).setIntervalMs(intervalMs);
    }

    public static HistorianUnit calculateCountAndTimes(Date metaDate, int total, TimeUnit totalUnit, int interval, TimeUnit intervalUnit) {
        return calculateCountAndTimes(metaDate, total, totalUnit, interval, intervalUnit, Boolean.FALSE);
    }

    public static HistorianUnit calculateCountAndTimes(int total, TimeUnit totalUnit, int interval, TimeUnit intervalUnit, Boolean isRound) {
        return calculateCountAndTimes(null, total, totalUnit, interval, intervalUnit, isRound);
    }

    public static HistorianUnit calculateCountAndTimes(int total, TimeUnit totalUnit, int interval, TimeUnit intervalUnit) {
        return calculateCountAndTimes(null, total, totalUnit, interval, intervalUnit, Boolean.FALSE);
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

    /**
     * 根据指定的时间单位，获取舍入时间后的日期对象
     * 此方法用于获取当前日期时间，在指定时间单位上进行舍入后的结果
     *
     * @param timeUnit 时间单位，决定了时间舍入的精度
     * @return 舍入时间后的日期对象
     */
    public static Date getDateForRoundTime(TimeUnit timeUnit) {
        return getDateForRoundTime(new Date(), timeUnit, 5);
    }

    /**
     * 根据指定的时间单位和舍入单位，获取舍入时间后的日期对象
     * 此方法允许指定当前日期时间，在指定时间单位上进行自定义步长的舍入
     *
     * @param timeUnit 时间单位，决定了时间舍入的精度
     * @param unit     舍入的步长单位，表示在指定时间单位上的增量
     * @return 舍入时间后的日期对象
     */
    public static Date getDateForRoundTime(TimeUnit timeUnit, Integer unit) {
        return getDateForRoundTime(new Date(), timeUnit, unit);
    }

    /**
     * 根据指定的日期、时间单位和舍入单位，获取舍入时间后的日期对象
     * 此方法允许对给定的日期时间，在指定时间单位上进行自定义步长的舍入
     *
     * @param date     需要进行舍入的日期时间对象
     * @param timeUnit 时间单位，决定了时间舍入的精度
     * @param unit     舍入的步长单位，表示在指定时间单位上的增量
     * @return 舍入时间后的日期对象
     */
    public static Date getDateForRoundTime(Date date, TimeUnit timeUnit, Integer unit) {
        // 如果传入的日期不为空，则更新为当前日期时间
        if (ObjectUtil.isNotEmpty(date)) {
            date = new Date();
        }
        String formateStr = "";
        String dateStr = "";
        Integer payload = 0;

        // 根据不同的时间单位，设置日期格式字符串和获取当前时间单位内的偏移量
        if (timeUnit == TimeUnit.SECONDS) {
            formateStr = DatePattern.NORM_DATETIME_PATTERN;
            dateStr = DateUtil.format(date, DatePattern.NORM_DATETIME_MINUTE_PATTERN);
            payload = DateUtil.second(date);
        } else if (timeUnit == TimeUnit.MINUTES) {
            formateStr = DatePattern.NORM_DATETIME_MINUTE_PATTERN;
            dateStr = DateUtil.format(date, "yyyy-MM-dd HH");
            payload = DateUtil.minute(date);
        } else if (timeUnit == TimeUnit.HOURS) {
            return DateUtil.beginOfHour(date);
        } else if (timeUnit == TimeUnit.DAYS) {
            return DateUtil.parse(DateUtil.formatDateTime(date) + " 08:00:00", DatePattern.NORM_DATETIME_PATTERN);
        }

        // 如果单位大于60或小于等于0，则不进行舍入，直接返回原日期对象
        if (unit > 60 || unit <= 0) {
            return date;
        }
        // 计算在当前时间单位内，偏移量与舍入单位的模数，以确定如何进行舍入
        int unitMod = (payload % unit);
        int load = payload - unitMod;

        // 将计算后的偏移量格式化为两位字符串，并拼接到日期字符串中
        String resultStr = StrUtil.padPre(Convert.toStr(load), 2, "0");
        dateStr += ":" + resultStr;
        // 根据最终的日期字符串和日期格式，解析并返回新的日期对象
        return DateUtil.parse(dateStr, formateStr);
    }

    //endregion

    //region 根据类注解的点位参数，填充对象属性
    /**
     * 根据时间参数填充对象的值
     * 该方法通过反射和映射关系，将特定时间点的数据填充到对象的相应字段中
     *
     * @param obj  要填充数据的对象
     * @param date 用于查询数据的时间点
     */
    public static void fillValForObjByPointParam(Object obj, Date date) {
        fillValForObjByPointParam(obj, date, "0");
    }

    public static void fillValForObjByPointParam(Object obj, Date date, String defaultValue) {
        // 获取对象的类信息
        Class<?> aClass = obj.getClass();
        // 获取类中字段与时间序列点的映射关系
        LinkedHashMap<Field, String> fieldPointByPointParam = getFieldPointByPointParam(aClass);
        // 创建一个双向映射，以便于后续根据字段或时间序列点获取对应关系
        BiMap<Field, String> biMap = new BiMap<>(fieldPointByPointParam);
        // 将所有时间序列点合并为一个字符串，用于查询
        String points = fieldPointByPointParam.values().stream().collect(Collectors.joining(";"));
        // 调用API根据时间范围和时间序列点获取插值数据
        DataResult interpolatedByRequestParamPost = DataApiInvoker
                .getInterpolatedByRequestParamPost(
                        new TagNamesEntity().setTagNames(points),
                        date,
                        date,
                        1,
                        1L);
        // 将查询到的数据转换为一个映射，便于后续填充到对象中
        Map<String, Object> valueMap = convertOneDataByTagNames(interpolatedByRequestParamPost.getData(), defaultValue);
        // 遍历数据映射，将数据填充到对象的相应字段中
        for (Map.Entry<String, Object> entry : valueMap.entrySet()) {
            // 根据时间序列点获取对应的字段
            Field field = biMap.getKey(entry.getKey());
            if (field != null) {
                // 获取字段值
                Object value = entry.getValue();
                String fieldValue = ObjectUtil.defaultIfNull(ReflectUtil.getFieldValue(obj, field), "").toString();
                // 字段值为空,就赋值
                if (StrUtil.isEmpty(fieldValue.toString())) {
                    // 使用反射将值设置到对象的字段中
                    ReflectUtil.setFieldValue(obj, field, value);
                }
            }
        }
    }

    /**
     * 获取指定类中所有被PointParam注解的字段及其对应的点位号参数
     * 该方法首先获取类级别的PointParam注解信息，然后获取所有字段级别的PointParam注解信息，
     * 并根据这些信息构建一个映射，键为字段，值为按照固定格式组成的点位号字符串
     *
     * @param aClass 要扫描的类
     * @return 返回一个 LinkedHashMap，键是被 PointParam 注解的字段，值是格式化后的点位号字符串
     * @throws ApiException 如果发现点位号重复，则抛出异常
     */
    public static LinkedHashMap<Field, String> getFieldPointByPointParam(Class<?> aClass) {
        // 打印类上的所有注释
        Annotation[] annotations = aClass.getAnnotations();
        String workshop = null;
        String stationNo = null;
        ValueType valueType = null;
        // 遍历类注解，寻找PointParam注解并提取其中的值
        for (Annotation annotation : annotations) {
            if (annotation instanceof PointParam) {
                PointParam pointParam = (PointParam) annotation;
                if (StrUtil.isNotEmpty(pointParam.workshop())) {
                    workshop = pointParam.workshop();
                }
                if (StrUtil.isNotEmpty(pointParam.stationNo())) {
                    stationNo = pointParam.stationNo();
                }
                if (pointParam.type() != ValueType.NULL) {
                    valueType = pointParam.type();
                }
            }
        }
        // 获取类中的所有字段
        List<Field> fields = ListUtil.of(ReflectUtil.getFields(aClass));
        // 定义点位号的格式模板
        String template = "{}{}{}{}";
        // 过滤出所有被PointParam注解的字段
        List<Field> collect = fields.stream().filter(field -> field.isAnnotationPresent(PointParam.class)).collect(Collectors.toList());
        LinkedHashMap<Field, String> pointFieldMap = new LinkedHashMap<>();
        // 遍历被PointParam注解的字段，构建点位号字符串
        for (Field field : collect) {
            PointParam annotation = field.getAnnotation(PointParam.class);
            String workshopField = workshop;
            String stationNoField = stationNo;
            ValueType typeField = valueType;
            String value = annotation.value();
            // 如果字段级别的注解有值，则使用字段级别的值
            if (StrUtil.isNotEmpty(annotation.workshop())) {
                workshopField = annotation.workshop();
            }
            if (StrUtil.isNotEmpty(annotation.stationNo())) {
                stationNoField = annotation.stationNo();
            }
            if (annotation.type() != ValueType.NULL) {
                typeField = annotation.type();
            }
            // 格式化点位号字符串
            workshopField = StrUtil.isBlank(workshopField) ? "" : workshopField + ".";
            stationNoField = StrUtil.isBlank(stationNoField) ? "" : stationNoField + ".";
            String type = typeField == null ? "" : "_" + typeField.name();
            String pointStr = StrUtil.format(template, workshopField, stationNoField, value, type);
            pointFieldMap.put(field, pointStr);
        }
        // 检查是否有重复的点位号，如果有则抛出异常
        List<String> errorMsg = pointFieldMap
                .entrySet()
                .stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue))
                .values()
                .stream()
                .filter(list -> list.size() > 1)
                .map(list -> {
                    String value = list.get(0).getValue();
                    String filedNames = list.stream().map(entry -> entry.getKey().getName()).collect(Collectors.joining(","));
                    return StrUtil.format("[{}]点位号重复在以下字段中：[{}]", value, filedNames);
                }).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(errorMsg)) {
            throw new ApiException(JSONObject.toJSONString(errorMsg));
        }
        // 返回字段与点位号的映射
        return pointFieldMap;
    }


    public static <T> void fillValForObjsByPointParam(List<T> objs) {
        fillValForObjsByPointParam(objs, new Date(), "0");
    }

    /**
     * 根据时间序列点参数为对象列表填充值
     *
     * @param objs         对象列表，需要填充值的列表
     * @param date         日期，用于获取数据的基准时间
     * @param defaultValue 默认值，当获取的数据为空时使用的值
     */
    public static <T> void fillValForObjsByPointParam(List<T> objs, Date date, String defaultValue) {
        // 检查对象列表是否为空，为空则直接返回
        if (CollUtil.isEmpty(objs)) return;
        // 检查日期是否为空，为空则使用当前日期
        if (ObjectUtil.isEmpty(date)) date = new Date();

        // 获取第一个对象用于后续获取类信息
        T obj = objs.get(0);
        // 获取对象的类信息
        Class<?> aClass = obj.getClass();
        // 获取类中字段与时间序列点的映射关系
        LinkedHashMap<Field, List<String>> fieldPointsByPointParam = getFieldPointsByPointParam(aClass);
        String tagNames = fieldPointsByPointParam.values().stream().flatMap(list -> list.stream()).collect(Collectors.joining(";"));
        // 调用API根据时间范围和时间序列点获取插值数据
        DataResult interpolatedByRequestParamPost = DataApiInvoker
                .getInterpolatedByRequestParamPost(
                        new TagNamesEntity().setTagNames(tagNames),
                        date,
                        date,
                        1,
                        1L);
        // 将查询到的数据转换为一个映射，便于后续填充到对象中
        Map<String, Object> valueMap = convertOneDataByTagNames(interpolatedByRequestParamPost.getData(), defaultValue);
        // 遍历每个字段及其对应的时间序列点
        fieldPointsByPointParam.forEach((field, points) -> {
            // 获取字段上的PointParam注解
            PointParam annotation = field.getAnnotation(PointParam.class);
            // 获取注解中指定的字段名
            String fieldName = annotation.fieldName();
            // 根据字段名获取字段
            Field fieldByName = ReflectUtil.getField(aClass, fieldName);
            // 如果字段为空，则直接返回
            if (fieldByName == null) return;
            // 将注解中的字段值转换为列表
            List<String> fieldValues = Arrays.stream(annotation.fieldValues()).collect(Collectors.toList());
            // 遍历每个时间序列点和对应的字段值
            for (int i = 0; i < points.size(); i++) {
                String point = points.get(i);
                String fieldValue = fieldValues.get(i);
                // 获取对应时间序列点的数据
                Object objValue = valueMap.get(point);
                // 遍历对象列表，为符合条件的对象填充字段值
                objs.forEach(item -> {
                    // 获取判断字段值
                    Object itemFieldValue = ReflectUtil.getFieldValue(item, fieldByName);
                    // 获取字段值
                    String relFieldValue = ObjectUtil.defaultIfNull(ReflectUtil.getFieldValue(item, field), "").toString();
                    // 如果判断字段值匹配且字段值为空，则填充字段值
                    if (itemFieldValue.equals(fieldValue) && StrUtil.isEmpty(relFieldValue)) {
                        ReflectUtil.setFieldValue(item, field, objValue);
                    }
                });
            }
        });
    }

    /**
     * 根据PointParam注解获取类中字段对应的点位号
     * 该方法首先获取类上标注的PointParam注解信息，然后遍历类中的所有字段
     * 对于每个标注了PointParam注解的字段，根据注解中的信息和类级别的信息生成点位号
     * 如果存在重复的点位号，则抛出异常
     *
     * @param aClass 要处理的类
     * @return 返回一个映射，键是字段，值是该字段对应的点位号列表
     * @throws ApiException 如果存在重复的点位号，则抛出此异常
     */
    public static LinkedHashMap<Field, List<String>> getFieldPointsByPointParam(Class<?> aClass) {
        // 打印类上的所有注释
        Annotation[] annotations = aClass.getAnnotations();
        String workshop = null;
        String stationNo = null;
        ValueType valueType = null;
        // 遍历类注解，寻找PointParam注解并提取其中的值
        for (Annotation annotation : annotations) {
            if (annotation instanceof PointParam) {
                PointParam pointParam = (PointParam) annotation;
                if (StrUtil.isNotEmpty(pointParam.workshop())) {
                    workshop = pointParam.workshop();
                }
                if (StrUtil.isNotEmpty(pointParam.stationNo())) {
                    stationNo = pointParam.stationNo();
                }
                if (pointParam.type() != ValueType.NULL) {
                    valueType = pointParam.type();
                }
            }
        }
        // 获取类中的所有字段
        List<Field> fields = ListUtil.of(ReflectUtil.getFields(aClass));
        // 定义点位号的格式模板
        String template = "{}{}{}{}";
        // 过滤出所有被PointParam注解的字段
        List<Field> collect = fields.stream().filter(field -> {
                    if (field.isAnnotationPresent(PointParam.class)) {
                        PointParam annotation = field.getAnnotation(PointParam.class);
                        String fieldName = annotation.fieldName();
                        String[] fieldValues = annotation.fieldValues();
                        String[] values = annotation.values();
                        if (StrUtil.isNotEmpty(fieldName) && ArrayUtil.isNotEmpty(fieldValues) && ArrayUtil.isNotEmpty(values)) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
        LinkedHashMap<Field, List<String>> pointFieldMap = new LinkedHashMap<>();
        // 遍历被PointParam注解的字段，构建点位号字符串
        for (Field field : collect) {
            PointParam annotation = field.getAnnotation(PointParam.class);
            String workshopField = workshop;
            String stationNoField = stationNo;
            ValueType typeField = valueType;
            String[] values = annotation.values();
            // 如果字段级别的注解有值，则使用字段级别的值
            if (StrUtil.isNotEmpty(annotation.workshop())) {
                workshopField = annotation.workshop();
            }
            if (StrUtil.isNotEmpty(annotation.stationNo())) {
                stationNoField = annotation.stationNo();
            }
            if (annotation.type() != ValueType.NULL) {
                typeField = annotation.type();
            }
            // 格式化点位号字符串
            workshopField = StrUtil.isBlank(workshopField) ? "" : workshopField + ".";
            stationNoField = StrUtil.isBlank(stationNoField) ? "" : stationNoField + ".";
            String type = typeField == null ? "" : "_" + typeField.name();
            List<String> pointStrs = new ArrayList<>();
            for (String value : values) {
                String pointStr = StrUtil.format(template, workshopField, stationNoField, value, type);
                pointStrs.add(pointStr);
            }
            pointFieldMap.put(field, pointStrs);
        }
        // 检查是否有重复的点位号，如果有则抛出异常
        List<String> errorMsg = pointFieldMap
                .entrySet()
                .stream()
                .map(entry -> {
                    if (entry.getValue().size() > 1) {
                        List<String> msgStrs = entry
                                .getValue()
                                .stream()
                                .collect(Collectors.groupingBy(Function.identity()))
                                .values()
                                .stream()
                                .filter(points -> points.size() > 1)
                                .map(points -> StrUtil.format("[{}]点位号重复,次数{}", points.get(0), points.size()))
                                .collect(Collectors.toList());
                        if (CollUtil.isNotEmpty(msgStrs)) {
                            Field key = entry.getKey();
                            return StrUtil.format("字段[{}]出现重复点位: {} ", key.getName(), msgStrs);
                        }
                    }
                    return "";
                })
                .filter(StrUtil::isNotEmpty).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(errorMsg)) {
            throw new ApiException(JSONObject.toJSONString(errorMsg));
        }
        // 返回字段与点位号的映射
        return pointFieldMap;
    }


    /**
     * 保留数字字符串中的指定位数的有效小数。
     * 如果数字字符串不是有效的数字，则直接返回原字符串。
     * 如果数字字符串中没有小数部分，则在末尾补零以确保至少有指定的小数位数。
     *
     * @param number 要处理的数字字符串
     * @param scale  保留的有效小数位数
     * @return 处理后的数字字符串，保留指定位数的有效小数
     */
    public static String retainSignificantDecimals(String number, Integer scale) {
        if (ObjectUtil.isEmpty(scale) || scale <= 0) return number; // 如果scale小于等于0，则直接返回原字符串
        if (StrUtil.isEmpty(number) || !NumberUtil.isNumber(number)) return number; // 如果number不是数字，则直接返回原字符串
        // 将数字转换为字符串（避免科学计数法）
        String numberStr = NumberUtil.toBigDecimal(number).toPlainString();

        // 分割整数部分和小数部分
        if (!StrUtil.contains(numberStr, '.')) {
            // 没有小数部分，直接返回原值或补零
            return numberStr; //StrUtil.padAfter("", scale, "0")
        }
        String[] parts = StrUtil.splitToArray(numberStr, '.');
        String integerPart = parts[0];
        String decimalPart = parts[1];

        int decimalPartLength = decimalPart.length();
        // 如果scale大于小数部分的长度，则直接返回原字符串
        if (scale > decimalPartLength) {
            return numberStr;
        }

        int lastSignificantIndex = -1;

        for (int i = 0; i < decimalPartLength; i++) {
            if (decimalPart.charAt(i) != '0') {
                lastSignificantIndex = i;
                break;
            }
        }
        // 如果没有有效数字，返回整数部分
        if (lastSignificantIndex == -1) {
            return integerPart; //StrUtil.padAfter("", scale, "0")
        }
        // 如果找到了两位有效数字，则截取到该位置
        int subLength = lastSignificantIndex + scale;
        String resultStr = "";
        if (subLength > decimalPartLength) {
            resultStr = numberStr;
        } else {
            resultStr = decimalPart.substring(0, subLength);
        }
        resultStr = StrUtil.trim(resultStr, 1, character -> character == '0');
        return integerPart + "." + resultStr;
    }
    //endregion

}
