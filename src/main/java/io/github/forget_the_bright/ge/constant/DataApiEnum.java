package io.github.forget_the_bright.ge.constant;

import io.github.forget_the_bright.ge.constant.child.ParamPosition;
import io.github.forget_the_bright.ge.entity.tags.TagNamesEntity;
import io.github.forget_the_bright.ge.entity.data.TagDataCreationEntity;
import io.github.forget_the_bright.ge.entity.data.SampledEntity;
import io.github.forget_the_bright.ge.entity.data.TrendEntity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpMethod;

@ToString
@Getter
public enum DataApiEnum {

    GET_CALCULATED_BY_REQUEST_PARAM(
            "查询标签列表的计算数据",
            "/v1/datapoints/calculated",
            HttpMethod.GET,
            ParamPosition.QUERY,
            ParamPosition.NONE,
            null
    ),
    GET_CALCULATED_BY_REQUEST_PARAM_POST(
            "查询标签列表的计算数据",
            "/v1/datapoints/calculated",
            HttpMethod.POST,
            ParamPosition.QUERY,
            ParamPosition.BODY,
            TagNamesEntity.class
    ),
    GET_CALCULATED_BY_PATH_VARIABLE_POST(
            "查询标签列表的计算数据",
            "/v1/datapoints/calculated/{start}/{end}/{calculationMode}/{count}/{intervalMs}",
            HttpMethod.POST,
            ParamPosition.PATH,
            ParamPosition.BODY,
            TagNamesEntity.class
    ),
    GET_CALCULATED_BY_PATH_VARIABLE(
            "查询标签列表的计算数据",
            "/v1/datapoints/calculated/{tagNames}/{start}/{end}/{calculationMode}/{count}/{intervalMs}",
            HttpMethod.GET,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
    ),
    CONFIGURE_QUERY_RESULT_BY_REQUEST_PARAM(
            "配置查询结果",
            "/v1/datapoints/configuration",
            HttpMethod.PUT,
            ParamPosition.QUERY,
            ParamPosition.NONE,
            null
    ),
    CONFIGURE_QUERY_RESULT_BY_PATH_VARIABLE(
            "配置查询结果",
            "/v1/datapoints/configuration/{maxDataQueryResultSize}",
            HttpMethod.PUT,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
    ),
    CREATE_TAG_DATA(
            "创建数据点位标签数据",
            "/v1/datapoints/create",
            HttpMethod.POST,
            ParamPosition.BODY,
            ParamPosition.NONE,
            TagDataCreationEntity.class
    ),
    GET_CURRENT_VALUE_BY_REQUEST_PARAM(
            "查询当前值数据",
            "/v1/datapoints/currentvalue",
            HttpMethod.GET,
            ParamPosition.QUERY,
            ParamPosition.NONE,
            null
    ),
    GET_CURRENT_VALUE_POST(
            "查询当前值数据",
            "/v1/datapoints/currentvalue",
            HttpMethod.POST,
            ParamPosition.BODY,
            ParamPosition.NONE,
            TagNamesEntity.class
    ),
    GET_CURRENT_VALUE_BY_PATH_VARIABLE(
            "查询当前值数据",
            "/v1/datapoints/currentvalue/{tagNames}",
            HttpMethod.GET,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
    ),
    GET_INTERPOLATED_BY_REQUEST_PARAM(
            "查询标签列表的插值",
            "/v1/datapoints/interpolated",
            HttpMethod.GET,
            ParamPosition.QUERY,
            ParamPosition.NONE,
            null
    ),
    GET_INTERPOLATED_BY_REQUEST_PARAM_POST(
            "查询标签列表的插值",
            "/v1/datapoints/interpolated",
            HttpMethod.POST,
            ParamPosition.QUERY,
            ParamPosition.BODY,
            TagNamesEntity.class
    ),
    GET_INTERPOLATED_BY_PATH_VARIABLE_POST(
            "查询标签列表的插值",
            "/v1/datapoints/interpolated/{start}/{end}/{count}/{intervalMs}",
            HttpMethod.POST,
            ParamPosition.PATH,
            ParamPosition.BODY,
            TagNamesEntity.class
    ),
    GET_INTERPOLATED_BY_PATH_VARIABLE(
            "查询标签列表的插值",
            "/v1/datapoints/interpolated/{tagNames}/{start}/{end}/{count}/{intervalMs}",
            HttpMethod.GET,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
    ),
    GET_RAW_DATA_BY_REQUEST_PARAM(
            "查询原始数据",
            "/v1/datapoints/raw",
            HttpMethod.GET,
            ParamPosition.QUERY,
            ParamPosition.NONE,
            null
    ),
    GET_RAW_DATA_BY_REQUEST_PARAM_POST(
            "查询原始数据",
            "/v1/datapoints/raw",
            HttpMethod.POST,
            ParamPosition.QUERY,
            ParamPosition.BODY,
            TagNamesEntity.class
    ),
    GET_RAW_DATA_BY_PATH_VARIABLE_POST(
            "查询原始数据",
            "/v1/datapoints/raw/{start}/{end}/{direction}/{count}",
            HttpMethod.POST,
            ParamPosition.PATH,
            ParamPosition.BODY,
            TagNamesEntity.class
    ),
    GET_RAW_DATA_BY_PATH_VARIABLE(
            "查询原始数据",
            "/v1/datapoints/raw/{tagNames}/{start}/{end}/{direction}/{count}",
            HttpMethod.GET,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
    ),
    GET_SAMPLED_BY_REQUEST_PARAM(
            "查询采样数据",
            "/v1/datapoints/sampled",
            HttpMethod.GET,
            ParamPosition.QUERY,
            ParamPosition.NONE,
            null
    ),
    GET_SAMPLED_BY_REQUEST_PARAM_POST(
            "查询采样数据",
            "/v1/datapoints/sampled",
            HttpMethod.POST,
            ParamPosition.BODY,
            ParamPosition.NONE,
            SampledEntity.class
    ),
    GET_TREND_DATA_BY_REQUEST_PARAM(
            "查询趋势数据",
            "/v1/datapoints/trend",
            HttpMethod.GET,
            ParamPosition.QUERY,
            ParamPosition.NONE,
            null
    ),
    GET_TREND_DATA_BY_REQUEST_PARAM_POST(
            "查询趋势数据",
            "/v1/datapoints/trend",
            HttpMethod.POST,
            ParamPosition.BODY,
            ParamPosition.NONE,
            TrendEntity.class
    );

    private final String desc;
    private final String path;
    private final HttpMethod method;
    private final ParamPosition primaryParamPosition;
    private final ParamPosition secondaryParamPosition;
    private final Class<?> entityType;

    DataApiEnum(String desc, String path, HttpMethod method, ParamPosition primaryParamPosition, ParamPosition secondaryParamPosition, Class<?> entityType) {
        this.desc = desc;
        this.path = path;
        this.method = method;
        this.primaryParamPosition = primaryParamPosition;
        this.secondaryParamPosition = secondaryParamPosition;
        this.entityType = entityType;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Method: %s, Path: %s, Desc: %s, PrimaryParam: %s, SecondaryParam: %s, EntityType: %s",
                name(), method, path, desc, primaryParamPosition, secondaryParamPosition, entityType != null ? entityType.getSimpleName() : "null");
    }
}