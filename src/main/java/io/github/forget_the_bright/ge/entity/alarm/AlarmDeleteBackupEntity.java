package io.github.forget_the_bright.ge.entity.alarm;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 报警删除备份实体 (Alarm Delete Backup Entity)
 * <p>
 * 该实体类用于定义创建报警数据副本时所需的参数。
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class AlarmDeleteBackupEntity {

    /**
     * 备份文件路径 (Backup File Path)
     * <p>
     * 指定存储报警数据副本的文件路径。
     * <p>
     * 类型: String<br>
     * 示例: "/backup/alarm_data_20231001.zip"<br>
     * 释义: 表示备份文件在系统中的存储位置。
     */
    @JSONField(name = "BackupFileName")
    private String backupFileName;

    /**
     * 结束时间 (End Time)
     * <p>
     * 报警或事件的时间范围的结束时间。
     * <p>
     * 类型: String<br>
     * 格式: ISO 8601 或 Unix 时间戳<br>
     * 示例: "2023-10-01T12:00:00Z" 或 "1696152000"<br>
     * 释义: 定义报警或事件的时间范围的结束时间点。
     */
    @JSONField(name = "EndTime", format = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date endTime;

    /**
     * 是否压缩报警 (Should Zip Alarms)
     * <p>
     * 指示是否对报警数据进行压缩。
     * <p>
     * 类型: Boolean<br>
     * 示例: true<br>
     * 释义: 如果设置为 true，则备份文件将以压缩格式存储；如果为 false，则以普通格式存储。
     */
    @JSONField(name = "ShouldZipAlarms")
    private Boolean shouldZipAlarms;

    /**
     * 开始时间 (Start Time)
     * <p>
     * 报警或事件的时间范围的开始时间。
     * <p>
     * 类型: String<br>
     * 格式: ISO 8601 或 Unix 时间戳<br>
     * 示例: "2023-10-01T00:00:00Z" 或 "1696065600"<br>
     * 释义: 定义报警或事件的时间范围的起始时间点。
     */
    @JSONField(name = "StartTime", format = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date startTime;
}