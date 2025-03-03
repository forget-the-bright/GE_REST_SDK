package io.github.forget_the_bright.ge.service;

import com.alibaba.fastjson.JSONObject;
import io.github.forget_the_bright.ge.constant.AlarmApiEnum;
import io.github.forget_the_bright.ge.constant.attach.ApiModule;
import io.github.forget_the_bright.ge.core.ApiClient;
import io.github.forget_the_bright.ge.entity.alarm.AlarmDeleteBackupEntity;
import io.github.forget_the_bright.ge.entity.alarm.AlarmNotificationInfoEntity;
import io.github.forget_the_bright.ge.entity.alarm.AlarmQueryInfoEntity;

/**
 * 报警服务API调用器
 *
 * <p>提供报警服务的相关操作接口，包括报警的创建、删除、查询及备份管理等功能。
 * 通过统一封装的ApiClient执行具体API调用操作。</p>
 */
public class AlarmApiInvoker {

    /**
     * 创建报警备份
     *
     * @param entity 包含备份路径等信息的实体对象
     * @return 包含API响应结果的JSON对象
     */
    public static JSONObject createBackUp(AlarmDeleteBackupEntity entity) {
        return ApiClient.execute(
                ApiModule.ALARMS,
                AlarmApiEnum.CREATE_BACKUP,
                entity
        );
    }

    /**
     * 创建新报警
     *
     * @param entity 包含报警通知详细信息的实体对象
     * @return 包含API响应结果的JSON对象
     */
    public static JSONObject createAlarms(AlarmNotificationInfoEntity entity) {
        return ApiClient.execute(
                ApiModule.ALARMS,
                AlarmApiEnum.CREATE_ALARM,
                entity
        );
    }

    /**
     * 删除指定报警
     *
     * @param entity 包含待删除报警信息的实体对象
     * @return 包含API响应结果的JSON对象
     */
    public static JSONObject deleteAlarms(AlarmDeleteBackupEntity entity) {
        return ApiClient.execute(
                ApiModule.ALARMS,
                AlarmApiEnum.DELETE_ALARMS,
                entity
        );
    }

    /**
     * 根据条件查询报警
     *
     * @param query 包含查询条件的参数实体
     * @return 包含查询结果集的JSON对象
     */
    public static JSONObject queryAlarmsByParam(AlarmQueryInfoEntity query) {
        return ApiClient.execute(
                ApiModule.ALARMS,
                AlarmApiEnum.QUERY_ALARMS,
                query
        );
    }

    /**
     * 从备份恢复报警
     *
     * @param path 备份文件路径
     * @return 包含恢复操作结果的JSON对象
     */
    public static JSONObject restoreAlarmsBackUp(String path) {
        return ApiClient.execute(
                ApiModule.ALARMS,
                AlarmApiEnum.RESTORE_BACKUP,
                path
        );
    }
}
