package io.github.forget_the_bright.ge.constant;

import io.github.forget_the_bright.ge.constant.attach.ParamPosition;
import io.github.forget_the_bright.ge.entity.request.tags.TagNamesEntity;
import io.github.forget_the_bright.ge.entity.request.data.TagDataCreationEntity;
import io.github.forget_the_bright.ge.entity.request.data.SampledEntity;
import io.github.forget_the_bright.ge.entity.request.data.TrendEntity;

import io.github.forget_the_bright.ge.entity.response.DataResult;
import io.github.forget_the_bright.ge.entity.response.TrendResult;
import lombok.Getter;
import lombok.ToString;
import cn.hutool.http.Method;

@ToString
@Getter
public enum DataApiEnum {

    GET_CALCULATED_BY_REQUEST_PARAM(
            "查询标签列表的计算数据",
            "/v1/datapoints/calculated",
            Method.GET,
            ParamPosition.QUERY,
            ParamPosition.NONE,
            null
            , DataResult.class),
    GET_CALCULATED_BY_REQUEST_PARAM_POST(
            "查询标签列表的计算数据",
            "/v1/datapoints/calculated",
            Method.POST,
            ParamPosition.QUERY,
            ParamPosition.BODY,
            TagNamesEntity.class
            , DataResult.class),
    GET_CALCULATED_BY_PATH_VARIABLE_POST(
            "查询标签列表的计算数据",
            "/v1/datapoints/calculated/{start}/{end}/{calculationMode}/{count}/{intervalMs}",
            Method.POST,
            ParamPosition.PATH,
            ParamPosition.BODY,
            TagNamesEntity.class
            , DataResult.class),
    GET_CALCULATED_BY_PATH_VARIABLE(
            "查询标签列表的计算数据",
            "/v1/datapoints/calculated/{tagNames}/{start}/{end}/{calculationMode}/{count}/{intervalMs}",
            Method.GET,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
            , DataResult.class),
    CONFIGURE_QUERY_RESULT_BY_REQUEST_PARAM(
            "配置查询结果",
            "/v1/datapoints/configuration",
            Method.PUT,
            ParamPosition.QUERY,
            ParamPosition.NONE,
            null
            , null),
    CONFIGURE_QUERY_RESULT_BY_PATH_VARIABLE(
            "配置查询结果",
            "/v1/datapoints/configuration/{maxDataQueryResultSize}",
            Method.PUT,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
            , null),
    CREATE_TAG_DATA(
            "创建数据点位标签数据",
            "/v1/datapoints/create",
            Method.POST,
            ParamPosition.BODY,
            ParamPosition.NONE,
            TagDataCreationEntity.class
            , null),
    GET_CURRENT_VALUE_BY_REQUEST_PARAM(
            "查询当前值数据",
            "/v1/datapoints/currentvalue",
            Method.GET,
            ParamPosition.QUERY,
            ParamPosition.NONE,
            null
            , DataResult.class),
    GET_CURRENT_VALUE_POST(
            "查询当前值数据",
            "/v1/datapoints/currentvalue",
            Method.POST,
            ParamPosition.BODY,
            ParamPosition.NONE,
            TagNamesEntity.class
            , DataResult.class),
    GET_CURRENT_VALUE_BY_PATH_VARIABLE(
            "查询当前值数据",
            "/v1/datapoints/currentvalue/{tagNames}",
            Method.GET,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
            , DataResult.class),
    GET_INTERPOLATED_BY_REQUEST_PARAM(
            "查询标签列表的插值",
            "/v1/datapoints/interpolated",
            Method.GET,
            ParamPosition.QUERY,
            ParamPosition.NONE,
            null
            , DataResult.class),
    GET_INTERPOLATED_BY_REQUEST_PARAM_POST(
            "查询标签列表的插值",
            "/v1/datapoints/interpolated",
            Method.POST,
            ParamPosition.QUERY,
            ParamPosition.BODY,
            TagNamesEntity.class
            , DataResult.class),
    GET_INTERPOLATED_BY_PATH_VARIABLE_POST(
            "查询标签列表的插值",
            "/v1/datapoints/interpolated/{start}/{end}/{count}/{intervalMs}",
            Method.POST,
            ParamPosition.PATH,
            ParamPosition.BODY,
            TagNamesEntity.class
            , DataResult.class),
    GET_INTERPOLATED_BY_PATH_VARIABLE(
            "查询标签列表的插值",
            "/v1/datapoints/interpolated/{tagNames}/{start}/{end}/{count}/{intervalMs}",
            Method.GET,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
            , DataResult.class),
    GET_RAW_DATA_BY_REQUEST_PARAM(
            "查询原始数据",
            "/v1/datapoints/raw",
            Method.GET,
            ParamPosition.QUERY,
            ParamPosition.NONE,
            null
            , DataResult.class),
    GET_RAW_DATA_BY_REQUEST_PARAM_POST(
            "查询原始数据",
            "/v1/datapoints/raw",
            Method.POST,
            ParamPosition.QUERY,
            ParamPosition.BODY,
            TagNamesEntity.class
            , DataResult.class),
    GET_RAW_DATA_BY_PATH_VARIABLE_POST(
            "查询原始数据",
            "/v1/datapoints/raw/{start}/{end}/{direction}/{count}",
            Method.POST,
            ParamPosition.PATH,
            ParamPosition.BODY,
            TagNamesEntity.class
            , DataResult.class),
    GET_RAW_DATA_BY_PATH_VARIABLE(
            "查询原始数据",
            "/v1/datapoints/raw/{tagNames}/{start}/{end}/{direction}/{count}",
            Method.GET,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
            , DataResult.class),
    GET_SAMPLED_BY_REQUEST_PARAM(
            "查询采样数据",
            "/v1/datapoints/sampled",
            Method.GET,
            ParamPosition.QUERY,
            ParamPosition.NONE,
            null
            , DataResult.class),
    GET_SAMPLED_BY_REQUEST_PARAM_POST(
            "查询采样数据",
            "/v1/datapoints/sampled",
            Method.POST,
            ParamPosition.BODY,
            ParamPosition.NONE,
            SampledEntity.class
            , DataResult.class),
    GET_TREND_DATA_BY_REQUEST_PARAM(
            "查询趋势数据",
            "/v1/datapoints/trend",
            Method.GET,
            ParamPosition.QUERY,
            ParamPosition.NONE,
            null
            , TrendResult.class),
    GET_TREND_DATA_BY_REQUEST_PARAM_POST(
            "查询趋势数据",
            "/v1/datapoints/trend",
            Method.POST,
            ParamPosition.BODY,
            ParamPosition.NONE,
            TrendEntity.class, TrendResult.class
    );

    private final String desc;
    private final String path;
    private final Method method;
    private final ParamPosition primaryParamPosition;
    private final ParamPosition secondaryParamPosition;
    private final Class<?> entityType;
    private final Class<?> resultType; // 返回值实体类

    DataApiEnum(String desc, String path, Method method, ParamPosition primaryParamPosition, ParamPosition secondaryParamPosition, Class<?> entityType,
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