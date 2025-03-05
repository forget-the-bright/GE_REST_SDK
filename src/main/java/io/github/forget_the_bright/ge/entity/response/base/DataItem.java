package io.github.forget_the_bright.ge.entity.response.base;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 表示一个数据项的响应实体类。
 * 包含标签名称、错误代码、数据类型、工程单位、高工程单位、低工程单位和样本列表等信息。
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @date 2025/3/5 下午2:36
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class DataItem {

    /**
     * 标签名称，表示数据项的标签名称。
     */
    @JSONField(name = "TagName")
    private String tagName;

    /**
     * 错误代码，表示数据项的错误代码。
     */
    @JSONField(name = "ErrorCode")
    private int errorCode;

    /**
     * 数据类型，表示数据项的数据类型。
     */
    @JSONField(name = "DataType")
    private String dataType;

    /**
     * 工程单位，表示数据项的工程单位。
     * 使用 Object 类型以处理可能的 null 值。
     */
    @JSONField(name = "EngineeringUnits")
    private Object engineeringUnits; // Using Object to handle null values

    /**
     * 高工程单位，表示数据项的高工程单位。
     */
    @JSONField(name = "HiEngineeringUnits")
    private int hiEngineeringUnits;

    /**
     * 低工程单位，表示数据项的低工程单位。
     */
    @JSONField(name = "LoEngineeringUnits")
    private int loEngineeringUnits;

    /**
     * 样本列表，包含与数据项相关的样本集合。
     */
    @JSONField(name = "Samples")
    private List<Sample> samples;

    /**
     * 存储趋势数据的列表
     * 每个列表项本身是一个Sample对象的列表，代表一个特定条件下的样本趋势
     * 使用JSONField注解将该字段映射到JSON对象中的"Trend"字段
     */
    @JSONField(name = "Trend")
    private List<List<Sample>> trend;
}
