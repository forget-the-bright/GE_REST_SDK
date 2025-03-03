package io.github.forget_the_bright.ge.constant;

import cn.hutool.http.Method;
import io.github.forget_the_bright.ge.constant.attach.ParamPosition;
import io.github.forget_the_bright.ge.entity.alarm.AlarmDeleteBackupEntity;
import io.github.forget_the_bright.ge.entity.alarm.AlarmNotificationInfoEntity;
import io.github.forget_the_bright.ge.entity.alarm.AlarmQueryInfoEntity;
import lombok.Getter;
import lombok.ToString;

/**
 * 告警模块接口枚举 (Alarm API Enum)
 * <p>
 * 该枚举类定义了告警模块的所有接口及其相关信息。
 */
@ToString
@Getter
public enum AlarmApiEnum {

    /**
     * 创建告警数据副本接口
     * <p>
     * 此接口允许在离线文件中创建告警数据的副本，以便稍后恢复。
     */
    CREATE_BACKUP(
            "创建告警备份",
            "/v1/alarms/backup",
            Method.POST,
            ParamPosition.BODY,
            ParamPosition.NONE,
            AlarmDeleteBackupEntity.class
    ),

    /**
     * 添加告警接口
     * <p>
     * 此接口允许在 Historian Server 中创建新的告警。
     */
    CREATE_ALARM(
            "创建告警",
            "/v1/alarms/create",
            Method.POST,
            ParamPosition.BODY,
            ParamPosition.NONE,
            AlarmNotificationInfoEntity.class
    ),

    /**
     * 清除时间段内的告警接口
     * <p>
     * 此接口允许清除指定时间段内的告警。
     */
    DELETE_ALARMS(
            "清除时间段内的告警",
            "/v1/alarms/delete",
            Method.POST,
            ParamPosition.BODY,
            ParamPosition.NONE,
            AlarmDeleteBackupEntity.class
    ),

    /**
     * 查询告警和事件数据接口
     * <p>
     * 此接口允许查询告警和事件数据。
     */
    QUERY_ALARMS(
            "查询告警和事件数据",
            "/v1/alarms/query",
            Method.POST,
            ParamPosition.BODY,
            ParamPosition.NONE,
            AlarmQueryInfoEntity.class
    ),

    /**
     * 恢复告警备份接口
     * <p>
     * 此接口允许将之前备份的告警恢复到正在运行的系统中。
     */
    RESTORE_BACKUP(
            "恢复告警备份",
            "/v1/alarms/restore",
            Method.POST,
            ParamPosition.BODY,
            ParamPosition.NONE,
            String.class // 恢复备份时传递的是字符串形式的备份文件路径
    );

    // 成员变量
    private final String desc; // 接口描述
    private final String path; // 接口路径
    private final Method method; // 请求方法
    private final ParamPosition primaryParamPosition; // 主要参数位置
    private final ParamPosition secondaryParamPosition; // 次要参数位置
    private final Class<?> entityType; // 返回值实体类

    // 构造函数
    AlarmApiEnum(String desc, String path, Method method,
                 ParamPosition primaryParamPosition, ParamPosition secondaryParamPosition,
                 Class<?> entityType) {
        this.desc = desc;
        this.path = path;
        this.method = method;
        this.primaryParamPosition = primaryParamPosition;
        this.secondaryParamPosition = secondaryParamPosition;
        this.entityType = entityType;
    }

}
