package io.github.forget_the_bright.ge.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.forget_the_bright.ge.constant.DataApiEnum;
import io.github.forget_the_bright.ge.constant.attach.ApiModule;
import io.github.forget_the_bright.ge.constant.common.*;
import io.github.forget_the_bright.ge.core.ApiClient;
import io.github.forget_the_bright.ge.core.ApiUtil;
import io.github.forget_the_bright.ge.entity.HistorianUnit;
import io.github.forget_the_bright.ge.entity.request.data.SampledEntity;
import io.github.forget_the_bright.ge.entity.request.data.TagDataCreationEntity;
import io.github.forget_the_bright.ge.entity.request.data.TrendEntity;
import io.github.forget_the_bright.ge.entity.request.tags.TagNamesEntity;
import io.github.forget_the_bright.ge.entity.response.DataResult;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class DataApiInvoker {


    /**
     * 根据请求参数获取计算结果
     * <p>
     * 该方法用于构建特定的请求参数，并调用API接口获取计算结果
     * 主要用于数据处理和计算场景，其中需要指定标签名称、数量、时间范围、计算模式和时间间隔
     *
     * @param tagNames        标签名称，用于标识特定的数据集
     * @param count           数据记录的数量,每个计算间隔内的存档值计数。
     * @param start           计算开始时间
     * @param end             计算结束时间
     * @param calculationMode 计算模式，决定了数据处理的方式
     * @param intervalMs      时间间隔，用于分段计算的毫秒数
     * @return 返回计算结果的JSON对象
     */
    public static DataResult getCalculatedByRequestParam(String tagNames,
                                                         Integer count,
                                                         Date start,
                                                         Date end,
                                                         CalculationMode calculationMode,
                                                         Long intervalMs) {
        // 创建一个参数映射，用于存储所有请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("tagNames", tagNames);
        params.put("count", count);
        // 格式化日期参数，确保时间格式符合API要求
        params.put("start", ApiUtil.isNullExec(start, () -> DateUtil.format(start, DatePattern.UTC_FORMAT)));
        params.put("end", ApiUtil.isNullExec(end, () -> DateUtil.format(end, DatePattern.UTC_FORMAT)));
        params.put("calculationMode", calculationMode);
        params.put("intervalMs", intervalMs);

        // 调用API客户端的execute方法，传入参数并获取计算结果
        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_CALCULATED_BY_REQUEST_PARAM,
                params
        );
    }

    /**
     * 通过请求参数和请求体获取计算数据
     *
     * @param tagNamesEntity  包含标签名称的实体对象
     * @param count           数据记录的数量,每个计算间隔内的存档值计数。
     * @param start           计算开始时间
     * @param end             计算结束时间
     * @param calculationMode 计算模式，决定了数据处理的方式
     * @param intervalMs      时间间隔，用于分段计算的毫秒数
     * @return 返回计算结果的DataResult对象
     */
    public static DataResult getCalculatedByRequestParamPost(TagNamesEntity tagNamesEntity, Integer count,
                                                             Date start,
                                                             Date end,
                                                             CalculationMode calculationMode,
                                                             Long intervalMs) {
        // 创建一个参数映射，用于存储所有请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("count", count);
        // 格式化日期参数，确保时间格式符合API要求
        params.put("start", ApiUtil.isNullExec(start, () -> DateUtil.format(start, DatePattern.UTC_FORMAT)));
        params.put("end", ApiUtil.isNullExec(end, () -> DateUtil.format(end, DatePattern.UTC_FORMAT)));
        params.put("calculationMode", calculationMode);
        params.put("intervalMs", intervalMs);
        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_CALCULATED_BY_REQUEST_PARAM_POST,
                params,
                tagNamesEntity
        );
    }

    /**
     * 通过路径变量和请求体获取计算数据
     *
     * @param tagNamesEntity  包含标签名称的实体对象
     * @param start           计算开始时间
     * @param end             计算结束时间
     * @param calculationMode 计算模式，决定了数据处理的方式
     * @param count           数据记录的数量,每个计算间隔内的存档值计数。
     * @param intervalMs      时间间隔，用于分段计算的毫秒数
     * @return 返回计算结果的DataResult对象
     */
    public static DataResult getCalculatedByPathVariablePost(TagNamesEntity tagNamesEntity, Date start, Date end, CalculationMode calculationMode, Integer count, Long intervalMs) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", ApiUtil.isNullExec(start, () -> DateUtil.format(start, DatePattern.UTC_FORMAT)));
        params.put("end", ApiUtil.isNullExec(end, () -> DateUtil.format(end, DatePattern.UTC_FORMAT)));
        params.put("calculationMode", calculationMode);
        params.put("count", count);
        params.put("intervalMs", intervalMs);

        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_CALCULATED_BY_PATH_VARIABLE_POST,
                params,
                tagNamesEntity
        );
    }

    /**
     * 通过路径变量获取计算数据
     *
     * @param tagNames        标签名称，用于标识特定的数据集
     * @param start           计算开始时间
     * @param end             计算结束时间
     * @param calculationMode 计算模式，决定了数据处理的方式
     * @param count           数据记录的数量,每个计算间隔内的存档值计数。
     * @param intervalMs      时间间隔，用于分段计算的毫秒数
     * @return 返回计算结果的DataResult对象
     */
    public static DataResult getCalculatedByPathVariable(String tagNames, Date start, Date end, CalculationMode calculationMode, Integer count, Long intervalMs) {
        Map<String, Object> params = new HashMap<>();
        params.put("tagNames", tagNames);
        params.put("start", ApiUtil.isNullExec(start, () -> DateUtil.format(start, DatePattern.UTC_FORMAT)));
        params.put("end", ApiUtil.isNullExec(end, () -> DateUtil.format(end, DatePattern.UTC_FORMAT)));
        params.put("calculationMode", calculationMode);
        params.put("count", count);
        params.put("intervalMs", intervalMs);

        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_CALCULATED_BY_PATH_VARIABLE,
                params
        );
    }

    /**
     * 根据请求参数配置查询结果
     *
     * @param maxDataQueryResultSize 最大数据查询结果大小
     * @return 返回配置结果的JSON对象
     */
    public static JSONObject configureQueryResultByRequestParam(Integer maxDataQueryResultSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("maxDataQueryResultSize", maxDataQueryResultSize);

        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.CONFIGURE_QUERY_RESULT_BY_REQUEST_PARAM,
                params
        );
    }

    /**
     * 根据路径变量配置查询结果
     *
     * @param maxDataQueryResultSize 最大数据查询结果大小
     * @return 返回配置结果的JSON对象
     */
    public static JSONObject configureQueryResultByPathVariable(Integer maxDataQueryResultSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("maxDataQueryResultSize", maxDataQueryResultSize);
        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.CONFIGURE_QUERY_RESULT_BY_PATH_VARIABLE,
                params
        );
    }

    /**
     * 创建数据点位标签数据
     *
     * @param tagDataCreationEntity 包含标签数据创建信息的实体对象
     * @return 返回创建结果的JSON对象
     */
    public static JSONObject createTagData(TagDataCreationEntity tagDataCreationEntity) {
        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.CREATE_TAG_DATA,
                tagDataCreationEntity
        );
    }

    /**
     * 根据请求参数查询当前值数据
     *
     * @param tagNames 标签名称
     * @return 返回当前值数据的JSON对象
     */
    public static DataResult getCurrentValueByRequestParam(String tagNames) {
        Map<String, Object> params = new HashMap<>();
        params.put("tagNames", tagNames);

        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_CURRENT_VALUE_BY_REQUEST_PARAM,
                params
        );
    }

    /**
     * 查询当前值数据
     *
     * @param tagNamesEntity 包含标签名称的实体对象
     * @return 返回当前值数据的JSON对象
     */
    public static DataResult getCurrentValuePost(TagNamesEntity tagNamesEntity) {
        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_CURRENT_VALUE_POST,
                tagNamesEntity
        );
    }

    /**
     * 根据路径变量查询当前值数据
     *
     * @param tagNames 标签名称
     * @return 返回当前值数据的JSON对象
     */
    public static DataResult getCurrentValueByPathVariable(String tagNames) {
        Map<String, Object> params = new HashMap<>();
        params.put("tagNames", tagNames);
        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_CURRENT_VALUE_BY_PATH_VARIABLE,
                params
        );
    }

    /**
     * 根据请求参数查询标签列表的插值
     *
     * @param tagNames   标签名称
     * @param start      查询开始时间
     * @param end        查询结束时间
     * @param count      数据记录的数量
     * @param intervalMs 时间间隔，用于分段计算的毫秒数
     * @return 返回插值数据的JSON对象
     */
    public static DataResult getInterpolatedByRequestParam(String tagNames, Date start, Date end, Integer count, Long intervalMs) {
        Map<String, Object> params = new HashMap<>();
        params.put("tagNames", tagNames);
        params.put("start", ApiUtil.isNullExec(start, () -> DateUtil.format(start, DatePattern.UTC_FORMAT)));
        params.put("end", ApiUtil.isNullExec(end, () -> DateUtil.format(end, DatePattern.UTC_FORMAT)));
        params.put("count", count);
        params.put("intervalMs", intervalMs);

        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_INTERPOLATED_BY_REQUEST_PARAM,
                params
        );
    }

    /**
     * 查询标签列表的插值
     *
     * @param tagNamesEntity 包含标签名称的实体对象
     * @param start          查询开始时间
     * @param end            查询结束时间
     * @param count          数据记录的数量
     * @param intervalMs     时间间隔，用于分段计算的毫秒数
     * @return 返回插值数据的JSON对象
     */
    public static DataResult getInterpolatedByRequestParamPost(TagNamesEntity tagNamesEntity, Date start, Date end, Integer count, Long intervalMs) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", ApiUtil.isNullExec(start, () -> DateUtil.format(start, DatePattern.UTC_FORMAT)));
        params.put("end", ApiUtil.isNullExec(end, () -> DateUtil.format(end, DatePattern.UTC_FORMAT)));
        params.put("count", count);
        params.put("intervalMs", intervalMs);

        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_INTERPOLATED_BY_REQUEST_PARAM_POST,
                params,
                tagNamesEntity
        );
    }

    /**
     * 查询标签列表的插值
     *
     * @param tagNamesEntity 包含标签名称的实体对象
     * @param start          查询开始时间
     * @param end            查询结束时间
     * @param count          数据记录的数量
     * @param intervalMs     时间间隔，用于分段计算的毫秒数
     * @return 返回插值数据的JSON对象
     */
    public static DataResult getInterpolatedByPathVariablePost(TagNamesEntity tagNamesEntity, Date start, Date end, Integer count, Long intervalMs) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", ApiUtil.isNullExec(start, () -> DateUtil.format(start, DatePattern.UTC_FORMAT)));
        params.put("end", ApiUtil.isNullExec(end, () -> DateUtil.format(end, DatePattern.UTC_FORMAT)));
        params.put("count", count);
        params.put("intervalMs", intervalMs);

        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_INTERPOLATED_BY_PATH_VARIABLE_POST,
                params,
                tagNamesEntity
        );
    }

    /**
     * 根据路径变量查询标签列表的插值
     *
     * @param tagNames   标签名称
     * @param start      查询开始时间
     * @param end        查询结束时间
     * @param count      数据记录的数量
     * @param intervalMs 时间间隔，用于分段计算的毫秒数
     * @return 返回插值数据的JSON对象
     */
    public static DataResult getInterpolatedByPathVariable(String tagNames, Date start, Date end, Integer count, Long intervalMs) {
        Map<String, Object> params = new HashMap<>();
        params.put("tagNames", tagNames);
        params.put("start", ApiUtil.isNullExec(start, () -> DateUtil.format(start, DatePattern.UTC_FORMAT)));
        params.put("end", ApiUtil.isNullExec(end, () -> DateUtil.format(end, DatePattern.UTC_FORMAT)));
        params.put("count", count);
        params.put("intervalMs", intervalMs);

        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_INTERPOLATED_BY_PATH_VARIABLE,
                params
        );
    }

    /**
     * 根据插值方式获取历史数据
     *
     * @param tagNames 标签名称，用于指定需要查询的数据类型
     * @param historianUnit 历史数据单元，包含查询的时间范围、数据点数量和时间间隔
     * @return 返回查询到的历史数据结果
     */
    public static DataResult getHistorianDataByInterpolated(String tagNames, HistorianUnit historianUnit) {
        return getInterpolatedByRequestParamPost(
                new TagNamesEntity().setTagNames(tagNames),
                historianUnit.getBegin(),
                historianUnit.getEnd(),
                historianUnit.getCount(),
                historianUnit.getIntervalMs());
    }

    /**
     * 根据插值方式获取历史数据，通过总时间长度和间隔来计算查询参数
     *
     * @param tagNames 标签名称，用于指定需要查询的数据类型
     * @param total 总时间长度的值
     * @param totalUnit 总时间长度的时间单位
     * @param interval 间隔的时间长度值
     * @param intervalUnit 间隔的时间长度的时间单位
     * @return 返回查询到的历史数据结果
     */
    public static DataResult getHistorianDataByInterpolated(String tagNames, int total, TimeUnit totalUnit, int interval, TimeUnit intervalUnit) {
        HistorianUnit historianUnit = ApiUtil.calculateCountAndTimes(total, totalUnit, interval, intervalUnit);
        return getInterpolatedByRequestParamPost(
                new TagNamesEntity().setTagNames(tagNames),
                historianUnit.getBegin(),
                historianUnit.getEnd(),
                historianUnit.getCount(),
                historianUnit.getIntervalMs());
    }

    /**
     * 根据插值方式获取历史数据，通过特定日期和总时间长度、间隔来计算查询参数
     *
     * @param tagNames 标签名称，用于指定需要查询的数据类型
     * @param metaDate 特定日期，用于计算查询的时间范围
     * @param total 总时间长度的值
     * @param totalUnit 总时间长度的时间单位
     * @param interval 间隔的时间长度值
     * @param intervalUnit 间隔时间长度的时间单位
     * @return 返回查询到的历史数据结果
     */
    public static DataResult getHistorianDataByInterpolated(String tagNames, Date metaDate, int total, TimeUnit totalUnit, int interval, TimeUnit intervalUnit) {
        HistorianUnit historianUnit = ApiUtil.calculateCountAndTimes(metaDate, total, totalUnit, interval, intervalUnit);
        return getInterpolatedByRequestParamPost(
                new TagNamesEntity().setTagNames(tagNames),
                historianUnit.getBegin(),
                historianUnit.getEnd(),
                historianUnit.getCount(),
                historianUnit.getIntervalMs());
    }

    /**
     * 根据总和标签获取区间值
     * 该方法用于计算指定标签在给定时间区间内的变化量，即结束时间的值减去开始时间的值
     * 主要用于处理以_SUM结尾的标签，这些标签代表了某种累积或总计值
     *
     * @param tagNames 标签名称列表，多个标签名之间用分号分隔
     * @param start    开始日期时间
     * @param end      结束日期时间
     * @return 返回一个映射，键为标签名，值为该标签在指定时间区间内的变化量字符串
     */
    public static Map<String, String> getIntervalValueBySumTag(String tagNames, Date start, Date end) {
        // 将标签名称字符串分割并转换为列表
        List<String> tagNameLists = Arrays.stream(tagNames.split(";")).collect(Collectors.toList());

        // 检查每个标签名称是否以_SUM结尾，如果不是，则抛出异常
        tagNameLists.forEach(tagName -> {
            if (!StrUtil.endWith(tagName, "_SUM")) {
                throw new RuntimeException("tagName must end with _SUM");
            }
        });

        // 处理开始时间，如果start为null，则使用当前时间
        Date startBegin = ApiUtil.isNullExec(start, () -> start);
        // 计算开始时间的结束点，即开始时间加上1秒
        Date startEnd = ApiUtil.isNullExec(start, () -> DateUtil.offsetSecond(start, 1));

        // 处理结束时间，如果end为null，则使用当前时间
        Date endBegin = ApiUtil.isNullExec(end, () -> end);
        // 计算结束时间的结束点，即结束时间加上1秒
        Date endEnd = ApiUtil.isNullExec(end, () -> DateUtil.offsetSecond(end, 1));

        // 获取开始时间区间的数据
        DataResult interpolatedBegin = getInterpolatedByRequestParamPost(new TagNamesEntity().setTagNames(tagNames), startBegin, startEnd, 1, 1L);
        // 将获取的数据转换为映射，键为标签名，值为标签值的双精度浮点数
        Map<String, BigDecimal> beginSum = ApiUtil.convertOneDataByTagNames(interpolatedBegin.getData())
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> {
                    return Convert.toBigDecimal(StrUtil.trim(entry.getValue().toString()));
                }));

        // 获取结束时间区间的数据
        DataResult interpolatedEnd = getInterpolatedByRequestParamPost(new TagNamesEntity().setTagNames(tagNames), endBegin, endEnd, 1, 1L);
        // 同样将获取的数据转换为映射
        Map<String, BigDecimal> endSum = ApiUtil.convertOneDataByTagNames(interpolatedEnd.getData())
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> {
                    return Convert.toBigDecimal(StrUtil.trim(entry.getValue().toString()));
                }));

        // 计算每个标签在指定时间区间内的变化量，并将结果转换为映射
        Map<String, String> intervalValues = tagNameLists
                .stream()
                .collect(
                        Collectors.toMap(
                                tagName -> tagName,
                                tagName -> Convert.toStr(endSum.get(tagName).subtract(beginSum.get(tagName)))
                        )
                );

        // 返回计算结果
        return intervalValues;
    }

    /**
     * 根据请求参数查询原始数据
     *
     * @param tagNames  标签名称
     * @param start     查询开始时间
     * @param end       查询结束时间
     * @param direction 查询方向 指定从存档中采样数据的方向（从起始时间向前或向后）。默认值为向前 (0)。
     * @param count     数据记录的数量
     * @return 返回原始数据的JSON对象
     */
    public static DataResult getRawDataByRequestParam(String tagNames, Date start, Date end, Direction direction, Integer count) {
        Map<String, Object> params = new HashMap<>();
        params.put("tagNames", tagNames);
        params.put("start", ApiUtil.isNullExec(start, () -> DateUtil.format(start, DatePattern.UTC_FORMAT)));
        params.put("end", ApiUtil.isNullExec(end, () -> DateUtil.format(end, DatePattern.UTC_FORMAT)));
        params.put("direction", direction);
        params.put("count", count);

        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_RAW_DATA_BY_REQUEST_PARAM,
                params
        );
    }

    /**
     * 查询原始数据
     *
     * @param tagNamesEntity 包含标签名称的实体对象
     * @param start          查询开始时间
     * @param end            查询结束时间
     * @param direction      查询方向 指定从存档中采样数据的方向（从起始时间向前或向后）。默认值为向前 (0)。
     * @param count          数据记录的数量
     * @return 返回原始数据的JSON对象
     */
    public static DataResult getRawDataByRequestParamPost(TagNamesEntity tagNamesEntity, Date start, Date end, Direction direction, Integer count) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", ApiUtil.isNullExec(start, () -> DateUtil.format(start, DatePattern.UTC_FORMAT)));
        params.put("end", ApiUtil.isNullExec(end, () -> DateUtil.format(end, DatePattern.UTC_FORMAT)));
        params.put("direction", direction);
        params.put("count", count);

        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_RAW_DATA_BY_REQUEST_PARAM_POST,
                params,
                tagNamesEntity
        );
    }

    /**
     * 查询原始数据
     *
     * @param tagNamesEntity 包含标签名称的实体对象
     * @param start          查询开始时间
     * @param end            查询结束时间
     * @param direction      查询方向 指定从存档中采样数据的方向（从起始时间向前或向后）。默认值为向前 (0)。
     * @param count          数据记录的数量
     * @return 返回原始数据的JSON对象
     */
    public static DataResult getRawDataByPathVariablePost(TagNamesEntity tagNamesEntity, Date start, Date end, Direction direction, Integer count) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", ApiUtil.isNullExec(start, () -> DateUtil.format(start, DatePattern.UTC_FORMAT)));
        params.put("end", ApiUtil.isNullExec(end, () -> DateUtil.format(end, DatePattern.UTC_FORMAT)));
        params.put("direction", direction);
        params.put("count", count);

        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_RAW_DATA_BY_PATH_VARIABLE_POST,
                params,
                tagNamesEntity
        );
    }

    /**
     * 根据路径变量查询原始数据
     *
     * @param tagNames  标签名称
     * @param start     查询开始时间
     * @param end       查询结束时间
     * @param direction 查询方向 指定从存档中采样数据的方向（从起始时间向前或向后）。默认值为向前 (0)。
     * @param count     数据记录的数量
     * @return 返回原始数据的JSON对象
     */
    public static DataResult getRawDataByPathVariable(String tagNames, Date start, Date end, Direction direction, Integer count) {
        Map<String, Object> params = new HashMap<>();
        params.put("tagNames", tagNames);
        params.put("start", ApiUtil.isNullExec(start, () -> DateUtil.format(start, DatePattern.UTC_FORMAT)));
        params.put("end", ApiUtil.isNullExec(end, () -> DateUtil.format(end, DatePattern.UTC_FORMAT)));
        params.put("direction", direction);
        params.put("count", count);


        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_RAW_DATA_BY_PATH_VARIABLE,
                params
        );
    }


    /**
     * 根据请求参数获取采样数据
     * 该方法用于根据多种查询条件，获取经过采样的数据集
     * 主要应用于大数据量处理场景，通过采样可以减少数据量，提高处理效率
     *
     * @param calculationMode  计算模式，用于指定数据处理的方式 {@link CalculationMode}
     * @param count            请求数量，表示希望获取的数据点数量
     * @param direction        方向，用于指定数据查询的方向，如正序或倒序 {@link Direction}
     * @param end              结束时间，指定查询时间范围的结束点
     * @param filterExpression 过滤表达式，用于指定数据过滤条件
     * @param filterMode       过滤模式，用于指定过滤表达式的应用方式 {@link FilterMode}
     * @param intervalMs       时间间隔（毫秒），指定数据点之间的时间间隔
     * @param queryModifier    查询修饰符，用于特殊查询需求的处理
     * @param returnDataFields 返回数据字段，指定需要返回的数据字段{@link ReturnDataFields}
     * @param samplingMode     采样模式，用于指定数据采样的方式 {@link SamplingMode}
     * @param start            开始时间，指定查询时间范围的起始点
     * @param tagNames         标签名称，指定查询的标签名称
     * @return 返回包含采样数据的DataResult对象
     */
    public static DataResult getSampledByRequestParam(CalculationMode calculationMode,
                                                      Integer count,
                                                      Direction direction,
                                                      Date end,
                                                      String filterExpression,
                                                      FilterMode filterMode,
                                                      Long intervalMs,
                                                      Long queryModifier,
                                                      ReturnDataFields returnDataFields,
                                                      SamplingMode samplingMode,
                                                      Date start,
                                                      String tagNames
    ) {
        // 创建参数映射，用于存储所有请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("calculationMode", calculationMode);
        // 格式化日期参数，确保日期格式符合API要求
        params.put("start", ApiUtil.isNullExec(start, () -> DateUtil.format(start, DatePattern.UTC_FORMAT)));
        params.put("end", ApiUtil.isNullExec(end, () -> DateUtil.format(end, DatePattern.UTC_FORMAT)));
        params.put("count", count);
        params.put("direction", direction);
        params.put("filterExpression", filterExpression);
        params.put("filterMode", filterMode);
        params.put("intervalMs", intervalMs);
        params.put("queryModifier", queryModifier);
        params.put("returnDataFields", returnDataFields);
        params.put("samplingMode", samplingMode);
        params.put("tagNames", tagNames);

        // 调用API客户端执行方法，传入数据模块、API枚举和参数映射，获取采样数据
        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_SAMPLED_BY_REQUEST_PARAM,
                params
        );
    }

    /**
     * 查询采样数据
     *
     * @param sampledEntity 包含采样数据信息的实体对象
     * @return 返回采样数据的JSON对象
     */
    public static DataResult getSampledByRequestParamPost(SampledEntity sampledEntity) {
        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_SAMPLED_BY_REQUEST_PARAM_POST,
                sampledEntity
        );
    }


    /**
     * 根据请求参数获取趋势数据
     * <p>
     * 该方法用于构建请求参数并调用API接口获取特定条件下的趋势数据它封装了API调用的细节，
     * 包括参数的构建和API的执行过程
     *
     * @param calculationMode      计算模式，用于指定数据处理的方式 {@link CalculationMode}
     * @param count                请求的数据点数量
     * @param direction            数据查询的方向，例如向前或向后 {@link Direction}
     * @param end                  查询结束时间
     * @param filterExpression     过滤表达式，用于筛选数据
     * @param filterMode           过滤模式，指定如何应用过滤表达式 {@link FilterMode}
     * @param intervalMs           数据点之间的时间间隔，单位为毫秒
     * @param queryModifier        查询修饰符，可能影响查询的性能或结果
     * @param samplingMode         采样模式，指定数据采样方式 {@link SamplingMode}
     * @param start                查询开始时间
     * @param statisticsItemFilter 统计项过滤器，用于筛选统计项
     * @param tagNames             标签名称，用于标识数据
     * @return 返回包含趋势数据的DataResult对象
     */
    public static DataResult getTrendDataByRequestParam(CalculationMode calculationMode,
                                                        Integer count,
                                                        Direction direction,
                                                        Date end,
                                                        String filterExpression,
                                                        FilterMode filterMode,
                                                        Long intervalMs,
                                                        Long queryModifier,
                                                        SamplingMode samplingMode,
                                                        Date start,
                                                        String statisticsItemFilter,
                                                        String tagNames) {
        // 创建一个参数映射，用于存储所有请求参数
        Map<String, Object> params = new HashMap<>();
        // 将计算模式放入参数映射
        params.put("calculationMode", calculationMode);
        // 将开始和结束时间按照UTC格式转换并放入参数映射
        params.put("start", ApiUtil.isNullExec(start, () -> DateUtil.format(start, DatePattern.UTC_FORMAT)));
        params.put("end", ApiUtil.isNullExec(end, () -> DateUtil.format(end, DatePattern.UTC_FORMAT)));
        // 将请求的数据点数量放入参数映射
        params.put("count", count);
        // 将数据查询方向放入参数映射
        params.put("direction", direction);
        // 将过滤表达式放入参数映射
        params.put("filterExpression", filterExpression);
        // 将过滤模式放入参数映射
        params.put("filterMode", filterMode);
        // 将数据点之间的时间间隔放入参数映射
        params.put("intervalMs", intervalMs);
        // 将查询修饰符放入参数映射
        params.put("queryModifier", queryModifier);
        // 将统计项过滤器放入参数映射
        params.put("statisticsItemFilter", statisticsItemFilter);
        // 将采样模式放入参数映射
        params.put("samplingMode", samplingMode);
        // 将标签名称放入参数映射
        params.put("tagNames", tagNames);

        // 调用API客户端的execute方法，传入数据模块、API枚举和参数映射，执行API请求并返回结果
        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_TREND_DATA_BY_REQUEST_PARAM,
                params
        );
    }

    /**
     * 查询趋势数据
     *
     * @param trendEntity 包含趋势数据信息的实体对象
     * @return 返回趋势数据的JSON对象
     */
    public static DataResult getTrendDataByRequestParamPost(TrendEntity trendEntity) {
        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_TREND_DATA_BY_REQUEST_PARAM_POST,
                trendEntity
        );
    }

}
