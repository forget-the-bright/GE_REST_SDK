package io.github.forget_the_bright.ge.entity.response.base;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * TODO
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/6/4 上午10:15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TrendItem {
    /**
     * 标签名称，表示数据项的标签名称。
     */
    @JSONField(name = "TagName")
    private String tagName;

    /**
     * 标签名称，表示数据项的标签名称。
     */
    @JSONField(name = "TagSource")
    private String tagSource;
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
     * 数据类型，表示数据项的数据类型。
     */
    @JSONField(name = "DataType")
    private String dataType;


    /**
     * 存储趋势数据的列表
     * 每个列表项本身是一个Sample对象的列表，代表一个特定条件下的样本趋势
     * 使用JSONField注解将该字段映射到JSON对象中的"Trend"字段
     */
    @JSONField(name = "Trend")
    private List<List<Trend>> trend;

}
