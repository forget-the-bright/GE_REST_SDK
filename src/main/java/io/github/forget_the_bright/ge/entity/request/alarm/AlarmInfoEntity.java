package io.github.forget_the_bright.ge.entity.request.alarm;

import com.alibaba.fastjson.annotation.JSONField;
import io.github.forget_the_bright.ge.config.UTCDateSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 报警信息实体 (Alarm Info Entity)
 * <p>
 * 该实体类用于表示报警的详细信息。
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class AlarmInfoEntity {

    /**
     * 确认时间 (Acknowledgment Time)
     * <p>
     * 报警被确认的时间。
     * <p>
     * 类型: String<br>
     * 格式: ISO 8601<br>
     * 示例: "2023-10-01T12:00:00Z"<br>
     * 释义: 表示报警被用户或系统确认的时间点。
     */
    @JSONField(name = "AckTime", serializeUsing = UTCDateSerializer.class)
    private Date ackTime;

    /**
     * 操作员 (Actor)
     * <p>
     * 执行操作的用户或系统名称。
     * <p>
     * 类型: String<br>
     * 示例: "AdminUser"<br>
     * 释义: 表示触发或确认报警的操作员身份。
     */
    @JSONField(name = "Actor")
    private String actor;

    /**
     * 报警类型 (Alarm Type)
     * <p>
     * 报警的类型标识。
     * <p>
     * 类型: Integer<br>
     * 示例: 1<br>
     * 释义: 数值类型，具体含义需参考报警类型的枚举定义。
     */
    @JSONField(name = "AlarmType")
    private Integer alarmType;

    /**
     * 条件名称 (Condition Name)
     * <p>
     * 报警关联的条件名称。
     * <p>
     * 类型: String<br>
     * 示例: "TemperatureExceeded"<br>
     * 释义: 表示触发报警的具体条件。
     */
    @JSONField(name = "ConditionName")
    private String conditionName;

    /**
     * 数据源名称 (Data Source Name)
     * <p>
     * 报警数据来源的名称。
     * <p>
     * 类型: String<br>
     * 示例: "SensorA"<br>
     * 释义: 表示报警数据的来源设备或系统。
     */
    @JSONField(name = "DataSourceName")
    private String dataSourceName;

    /**
     * 结束时间 (End Time)
     * <p>
     * 报警结束的时间。
     * <p>
     * 类型: String<br>
     * 格式: ISO 8601<br>
     * 示例: "2023-10-01T12:05:00Z"<br>
     * 释义: 表示报警状态结束的时间点。
     */
    @JSONField(name = "EndTime", serializeUsing = UTCDateSerializer.class)
    private Date endTime;

    /**
     * 事件类别 (Event Category)
     * <p>
     * 报警所属的事件类别。
     * <p>
     * 类型: String<br>
     * 示例: "Safety"<br>
     * 释义: 表示报警所属的事件分类。
     */
    @JSONField(name = "EventCategory")
    private String eventCategory;

    /**
     * 项目 ID (Item ID)
     * <p>
     * 报警关联的项目唯一标识。
     * <p>
     * 类型: String<br>
     * 示例: "ITEM-001"<br>
     * 释义: 表示报警与特定项目的关联关系。
     */
    @JSONField(name = "ItemId")
    private String itemId;

    /**
     * 报警消息 (Message)
     * <p>
     * 报警的具体描述信息。
     * <p>
     * 类型: String<br>
     * 示例: "Temperature exceeded the threshold value."<br>
     * 释义: 提供报警的详细说明。
     */
    @JSONField(name = "Message")
    private String message;

    /**
     * 数据质量 (Quality)
     * <p>
     * 报警数据的质量指标。
     * <p>
     * 类型: Integer<br>
     * 示例: 0<br>
     * 释义: 数值类型，具体含义需参考数据质量的枚举定义。
     */
    @JSONField(name = "Quality")
    private Integer quality;

    /**
     * 严重性 (Severity)
     * <p>
     * 报警的严重程度。
     * <p>
     * 类型: Integer<br>
     * 示例: 3<br>
     * 释义: 数值类型，具体含义需参考严重性的枚举定义。
     */
    @JSONField(name = "Severity")
    private Integer severity;

    /**
     * 数据源 (Source)
     * <p>
     * 报警数据的具体来源。
     * <p>
     * 类型: String<br>
     * 示例: "MainServer"<br>
     * 释义: 表示报警数据的实际来源。
     */
    @JSONField(name = "Source")
    private String source;

    /**
     * 开始时间 (Start Time)
     * <p>
     * 报警开始的时间。
     * <p>
     * 类型: String<br>
     * 格式: ISO 8601<br>
     * 示例: "2023-10-01T12:00:00Z"<br>
     * 释义: 表示报警状态开始的时间点。
     */
    @JSONField(name = "StartTime", serializeUsing = UTCDateSerializer.class)
    private Date startTime;

    /**
     * 状态转换时间 (State Transition Time)
     * <p>
     * 报警状态发生变化的时间。
     * <p>
     * 类型: String<br>
     * 格式: ISO 8601<br>
     * 示例: "2023-10-01T12:02:00Z"<br>
     * 释义: 表示报警状态从一个状态转换到另一个状态的时间点。
     */
    @JSONField(name = "StateTransitionTime", serializeUsing = UTCDateSerializer.class)
    private Date stateTransitionTime;

    /**
     * 子条件名称 (Sub Condition Name)
     * <p>
     * 报警关联的子条件名称。
     * <p>
     * 类型: String<br>
     * 示例: "HighPressure"<br>
     * 释义: 表示触发报警的具体子条件。
     */
    @JSONField(name = "SubConditionName")
    private String subConditionName;

    /**
     * 标签名 (Tag Name)
     * <p>
     * 报警关联的标签名称。
     * <p>
     * 类型: String<br>
     * 示例: "PressureSensor1"<br>
     * 释义: 表示报警与特定标签的关联关系。
     */
    @JSONField(name = "Tagname")
    private String tagName;

    /**
     * 时间戳 (Timestamp)
     * <p>
     * 报警的时间戳。
     * <p>
     * 类型: String<br>
     * 格式: ISO 8601<br>
     * 示例: "2023-10-01T12:00:00Z"<br>
     * 释义: 表示报警发生的确切时间点。
     */
    @JSONField(name = "TimeStamp", serializeUsing = UTCDateSerializer.class)
    private Date timeStamp;

    /**
     * 厂商属性列表 (Vendor Attributes)
     * <p>
     * 报警的厂商自定义属性列表。
     * <p>
     * 类型: List<AlarmAttributesEntity><br>
     * 示例: 见 AlarmAttributesEntity 定义<br>
     * 释义: 包含厂商定义的附加属性信息。
     */
    @JSONField(name = "VendorAttributes")
    private List<AlarmAttributesEntity> vendorAttributes;
}