package io.github.forget_the_bright.ge.entity.request.alarm;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 报警通知信息实体 (Alarm Notification Info Entity)
 * <p>
 * 该实体类用于表示报警通知的相关信息。
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class AlarmNotificationInfoEntity {

    /**
     * 报警信息列表 (Alarms Info List)
     * <p>
     * 包含一组具体的报警信息。
     * <p>
     * 类型: List<AlarmInfoEntity><br>
     * 示例: 见 AlarmInfoEntity 定义<br>
     * 释义: 每个元素是一个完整的报警信息对象。
     */
    @JSONField(name = "AlarmsInfo")
    private List<AlarmInfoEntity> alarmsInfo;

    /**
     * 数据源名称 (Data Source Name)
     * <p>
     * 报警数据来源的名称。
     * <p>
     * 类型: String<br>
     * 示例: "MainServer"<br>
     * 释义: 表示报警数据的实际来源。
     */
    @JSONField(name = "DataSource")
    private String dataSource;
}