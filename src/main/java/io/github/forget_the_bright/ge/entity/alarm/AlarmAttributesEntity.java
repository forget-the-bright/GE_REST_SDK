package io.github.forget_the_bright.ge.entity.alarm;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 报警属性实体 (Alarm Attributes Entity)
 * <p>
 * 该实体类用于表示报警的属性信息。
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class AlarmAttributesEntity {

    /**
     * 数据类型 (Data Type)
     * <p>
     * 报警属性的数据类型。
     * <p>
     * 类型: Integer<br>
     * 示例: 1 (整数), 2 (浮点数)<br>
     * 默认值: 1
     */
    @JSONField(name = "DataType")
    private Integer dataType;

    /**
     * 名称 (Name)
     * <p>
     * 报警属性的名称。
     * <p>
     * 类型: String<br>
     * 示例: "Temperature"
     */
    @JSONField(name = "Name")
    private String name;

    /**
     * 值 (Value)
     * <p>
     * 报警属性的具体值。
     * <p>
     * 类型: String<br>
     * 示例: "High"
     */
    @JSONField(name = "Value")
    private String value;
}