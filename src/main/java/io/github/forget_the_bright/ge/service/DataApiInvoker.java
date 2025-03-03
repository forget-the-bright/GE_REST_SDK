package io.github.forget_the_bright.ge.service;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.forget_the_bright.ge.constant.DataApiEnum;
import io.github.forget_the_bright.ge.constant.child.ApiModule;
import io.github.forget_the_bright.ge.core.ApiClient;
import io.github.forget_the_bright.ge.entity.data.SampledEntity;
import io.github.forget_the_bright.ge.entity.data.TagDataCreationEntity;
import io.github.forget_the_bright.ge.entity.data.TrendEntity;
import io.github.forget_the_bright.ge.entity.tags.TagNamesEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    public static JSONObject getCalculatedByRequestParam(String tagNames,
                                                         Integer count,
                                                         Date start,
                                                         Date end,
                                                         String calculationMode,
                                                         Long intervalMs) {
        // 创建一个参数映射，用于存储所有请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("tagNames", tagNames);
        params.put("count", count);
        // 格式化日期参数，确保时间格式符合API要求
        params.put("start", DateUtil.format(start, DatePattern.UTC_FORMAT));
        params.put("end", DateUtil.format(end, DatePattern.UTC_FORMAT));
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
    public static JSONObject getCurrentValueByRequestParam(String tagNames) {
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
    public static JSONObject getCurrentValuePost(TagNamesEntity tagNamesEntity) {
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
    public static JSONObject getCurrentValueByPathVariable(String tagNames) {
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
    public static JSONObject getInterpolatedByRequestParam(String tagNames, Date start, Date end, Integer count, Long intervalMs) {
        Map<String, Object> params = new HashMap<>();
        params.put("tagNames", tagNames);
        params.put("start", DateUtil.format(start, DatePattern.UTC_FORMAT));
        params.put("end", DateUtil.format(end, DatePattern.UTC_FORMAT));
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
    public static JSONObject getInterpolatedByRequestParamPost(TagNamesEntity tagNamesEntity, Date start, Date end, Integer count, Long intervalMs) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", DateUtil.format(start, DatePattern.UTC_FORMAT));
        params.put("end", DateUtil.format(end, DatePattern.UTC_FORMAT));
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
    public static JSONObject getInterpolatedByPathVariablePost(TagNamesEntity tagNamesEntity, Date start, Date end, Integer count, Long intervalMs) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", DateUtil.format(start, DatePattern.UTC_FORMAT));
        params.put("end", DateUtil.format(end, DatePattern.UTC_FORMAT));
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
    public static JSONObject getInterpolatedByPathVariable(String tagNames, Date start, Date end, Integer count, Long intervalMs) {
        Map<String, Object> params = new HashMap<>();
        params.put("tagNames", tagNames);
        params.put("start", DateUtil.format(start, DatePattern.UTC_FORMAT));
        params.put("end", DateUtil.format(end, DatePattern.UTC_FORMAT));
        params.put("count", count);
        params.put("intervalMs", intervalMs);

        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_INTERPOLATED_BY_PATH_VARIABLE,
                params
        );
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
    public static JSONObject getRawDataByRequestParam(String tagNames, Date start, Date end, String direction, Integer count) {
        Map<String, Object> params = new HashMap<>();
        params.put("tagNames", tagNames);
        params.put("start", DateUtil.format(start, DatePattern.UTC_FORMAT));
        params.put("end", DateUtil.format(end, DatePattern.UTC_FORMAT));
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
    public static JSONObject getRawDataByRequestParamPost(TagNamesEntity tagNamesEntity, Date start, Date end, String direction, Integer count) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", DateUtil.format(start, DatePattern.UTC_FORMAT));
        params.put("end", DateUtil.format(end, DatePattern.UTC_FORMAT));
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
    public static JSONObject getRawDataByPathVariablePost(TagNamesEntity tagNamesEntity, Date start, Date end, String direction, Integer count) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", DateUtil.format(start, DatePattern.UTC_FORMAT));
        params.put("end", DateUtil.format(end, DatePattern.UTC_FORMAT));
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
    public static JSONObject getRawDataByPathVariable(String tagNames, Date start, Date end, String direction, Integer count) {
        Map<String, Object> params = new HashMap<>();
        params.put("tagNames", tagNames);
        params.put("start", DateUtil.format(start, DatePattern.UTC_FORMAT));
        params.put("end", DateUtil.format(end, DatePattern.UTC_FORMAT));
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
     * @param calculationMode 计算模式，用于指定数据处理的方式
     * @param count           请求数量，表示希望获取的数据点数量
     * @param direction       方向，用于指定数据查询的方向，如正序或倒序
     * @param end             结束时间，指定查询时间范围的结束点
     * @param filterExpression 过滤表达式，用于指定数据过滤条件
     * @param filterMode      过滤模式，用于指定过滤表达式的应用方式
     * @param intervalMs      时间间隔（毫秒），指定数据点之间的时间间隔
     * @param queryModifier   查询修饰符，用于特殊查询需求的处理
     * @param returnDataFields 返回数据字段，指定需要返回的数据字段
     * @param samplingMode    采样模式，用于指定数据采样的方式
     * @param start           开始时间，指定查询时间范围的起始点
     * @param tagNames        标签名称，指定查询的标签名称
     * @return 返回包含采样数据的JSONObject对象
     */
    public static JSONObject getSampledByRequestParam(Integer calculationMode,
                                                      Integer count,
                                                      Integer direction,
                                                      Date end,
                                                      String filterExpression,
                                                      Integer filterMode,
                                                      Long intervalMs,
                                                      Long queryModifier,
                                                      Integer returnDataFields,
                                                      Integer samplingMode,
                                                      Date start,
                                                      String tagNames
    ) {
        // 创建参数映射，用于存储所有请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("calculationMode", calculationMode);
        // 格式化日期参数，确保日期格式符合API要求
        params.put("start", DateUtil.format(start, DatePattern.UTC_FORMAT));
        params.put("end", DateUtil.format(end, DatePattern.UTC_FORMAT));
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
    public static JSONObject getSampledByRequestParamPost(SampledEntity sampledEntity) {
        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_SAMPLED_BY_REQUEST_PARAM_POST,
                sampledEntity
        );
    }


    /**
     * 根据请求参数获取趋势数据
     *
     * 该方法用于构建请求参数并调用API接口获取特定条件下的趋势数据它封装了API调用的细节，
     * 包括参数的构建和API的执行过程
     *
     * @param calculationMode 计算模式，用于指定数据处理的方式
     * @param count 请求的数据点数量
     * @param direction 数据查询的方向，例如向前或向后
     * @param end 查询结束时间
     * @param filterExpression 过滤表达式，用于筛选数据
     * @param filterMode 过滤模式，指定如何应用过滤表达式
     * @param intervalMs 数据点之间的时间间隔，单位为毫秒
     * @param queryModifier 查询修饰符，可能影响查询的性能或结果
     * @param samplingMode 采样模式，指定数据采样方式
     * @param start 查询开始时间
     * @param statisticsItemFilter 统计项过滤器，用于筛选统计项
     * @param tagNames 标签名称，用于标识数据
     * @return 返回包含趋势数据的JSONObject对象
     */
    public static JSONObject getTrendDataByRequestParam(Integer calculationMode,
                                                        Integer count,
                                                        Integer direction,
                                                        Date end,
                                                        String filterExpression,
                                                        Integer filterMode,
                                                        Long intervalMs,
                                                        Long queryModifier,
                                                        Integer samplingMode,
                                                        Date start,
                                                        String statisticsItemFilter,
                                                        String tagNames) {
        // 创建一个参数映射，用于存储所有请求参数
        Map<String, Object> params = new HashMap<>();
        // 将计算模式放入参数映射
        params.put("calculationMode", calculationMode);
        // 将开始和结束时间按照UTC格式转换并放入参数映射
        params.put("start", DateUtil.format(start, DatePattern.UTC_FORMAT));
        params.put("end", DateUtil.format(end, DatePattern.UTC_FORMAT));
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
    public static JSONObject getTrendDataByRequestParamPost(TrendEntity trendEntity) {
        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.GET_TREND_DATA_BY_REQUEST_PARAM_POST,
                trendEntity
        );
    }

}
