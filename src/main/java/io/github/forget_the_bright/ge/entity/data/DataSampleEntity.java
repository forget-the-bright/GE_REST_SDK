package io.github.forget_the_bright.ge.entity.data;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 数据样本实体 (Data Sample Entity)
 * <p>
 * 该实体类用于表示数据样本的信息。
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class DataSampleEntity {

    /**
     * 数据质量 (Quality)
     * <p>
     * 数据的质量指标。
     * <p>
     * 类型: Integer<br>
     * 示例: 0<br>
     * 释义: 数值类型，具体含义需参考数据质量的枚举定义。
     */
    @JSONField(name = "Quality")
    private Integer quality;

    /**
     * 时间戳 (Timestamp)
     * <p>
     * 数据样本的时间戳。
     * <p>
     * 类型: String<br>
     * 格式: ISO 8601<br>
     * 示例: "2023-10-01T12:00:00Z"<br>
     * 释义: 表示数据样本生成的时间点。
     */
    @JSONField(name = "TimeStamp", format = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date timeStamp;

    /**
     * 数据值 (Value)
     * <p>
     * 数据样本的具体值。
     * <p>
     * 类型: String<br>
     * 示例: "75.5"<br>
     * 释义: 表示数据样本的数值或状态。
     */
    @JSONField(name = "Value")
    private String value;
}