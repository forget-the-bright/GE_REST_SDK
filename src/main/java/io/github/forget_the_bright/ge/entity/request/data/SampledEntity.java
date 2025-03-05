package io.github.forget_the_bright.ge.entity.request.data;

import com.alibaba.fastjson.annotation.JSONField;
import io.github.forget_the_bright.ge.constant.common.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 采样实体 (Sampled Entity)
 * <p>
 * 该实体类用于表示采样数据的相关参数。
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SampledEntity {

    /**
     * 计算模式 (Calculation Mode)
     * <p>
     * 指定计算模式。
     * <p>
     * 类型: Integer<br>
     * 示例: 0<br>
     * 释义: 具体含义需参考计算模式的枚举定义。
     */
    @JSONField(name = "calculationMode")
    private CalculationMode calculationMode;

    /**
     * 每个计算间隔内的存档值计数 (Count)
     * <p>
     * 每个计算间隔内的存档值数量。
     * <p>
     * 类型: Integer<br>
     * 示例: 10<br>
     * 释义: 数据条数用于计算时间间隔。
     */
    @JSONField(name = "count")
    private Integer count;

    /**
     * 采样方向 (Direction)
     * <p>
     * 指定从存档中采样数据的方向（向前或向后）。
     * <p>
     * 类型: Integer<br>
     * 示例: 0<br>
     * 释义: 默认值为向前 (0)。
     */
    @JSONField(name = "direction")
    private Direction direction;

    /**
     * 查询结束时间 (End Time)
     * <p>
     * 查询的结束时间。
     * <p>
     * 类型: String<br>
     * 格式: ISO 8601<br>
     * 示例: "2023-10-01T12:00:00Z"<br>
     * 释义: 表示查询的时间范围结束的时间点。
     */
    @JSONField(name = "end", format = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date endTime;

    /**
     * 过滤表达式 (Filter Expression)
     * <p>
     * 数据过滤的表达式。
     * <p>
     * 类型: String<br>
     * 示例: "value > 50"<br>
     * 释义: 表示用于筛选数据的条件。
     */
    @JSONField(name = "filterExpression")
    private String filterExpression;

    /**
     * 过滤模式 (Filter Mode)
     * <p>
     * 指定过滤模式。
     * <p>
     * 类型: Integer<br>
     * 示例: 1<br>
     * 释义: 具体含义需参考过滤模式的枚举定义。
     */
    @JSONField(name = "filterMode")
    private FilterMode filterMode;

    /**
     * 时间间隔 (Interval in Milliseconds)
     * <p>
     * 时间间隔（以毫秒为单位）。
     * <p>
     * 类型: Long<br>
     * 示例: 60000<br>
     * 释义: 表示每次采样的时间间隔。
     */
    @JSONField(name = "intervalMs")
    private Long intervalMs;

    /**
     * 查询修饰符 (Query Modifier)
     * <p>
     * 查询修饰符。
     * <p>
     * 类型: Long<br>
     * 示例: 100<br>
     * 释义: 用于修改查询行为的参数。
     */
    @JSONField(name = "queryModifier")
    private Long queryModifier;

    /**
     * 返回数据字段 (Return Data Fields)
     * <p>
     * 指定返回的数据字段。
     * <p>
     * 类型: Integer<br>
     * 示例: 3<br>
     * 释义: 数值类型，具体含义需参考返回字段的枚举定义。
     */
    @JSONField(name = "returnDataFields")
    private ReturnDataFields returnDataFields;

    /**
     * 采样模式类型 (Sampling Mode)
     * <p>
     * 指定采样模式类型。
     * <p>
     * 类型: Integer<br>
     * 示例: 2<br>
     * 释义: 具体含义需参考采样模式的枚举定义。
     */
    @JSONField(name = "samplingMode")
    private SamplingMode samplingMode;

    /**
     * 查询开始时间 (Start Time)
     * <p>
     * 查询的开始时间。
     * <p>
     * 类型: String<br>
     * 格式: ISO 8601<br>
     * 示例: "2023-10-01T11:00:00Z"<br>
     * 释义: 表示查询的时间范围开始的时间点。
     */
    @JSONField(name = "start", format = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date startTime;

    /**
     * 查询标签名称 (Tag Names)
     * <p>
     * 查询指定的标签名称。
     * <p>
     * 类型: String<br>
     * 示例: "SensorA,SensorB"<br>
     * 释义: 表示需要查询的标签列表。
     */
    @JSONField(name = "tagNames")
    private String tagNames;
}