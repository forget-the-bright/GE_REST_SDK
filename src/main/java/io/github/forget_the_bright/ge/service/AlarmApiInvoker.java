package io.github.forget_the_bright.ge.service;

import com.alibaba.fastjson.JSONObject;
import io.github.forget_the_bright.ge.constant.AlarmApiEnum;
import io.github.forget_the_bright.ge.constant.child.ApiModule;
import io.github.forget_the_bright.ge.core.ApiClient;
import io.github.forget_the_bright.ge.entity.alarm.AlarmDeleteBackupEntity;
import io.github.forget_the_bright.ge.entity.alarm.AlarmQueryInfoEntity;
import org.springframework.stereotype.Component;

// AlarmApiInvoker.java
@Component
public class AlarmApiInvoker {
    private final ApiClient apiClient;

    public AlarmApiInvoker(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public JSONObject createBackup(AlarmDeleteBackupEntity entity) {
        return apiClient.execute(
                ApiModule.ALARMS,
                AlarmApiEnum.CREATE_BACKUP,
                entity
        );
    }

    public JSONObject queryAlarmsByParam(AlarmQueryInfoEntity query) {
        return apiClient.execute(
                ApiModule.ALARMS,
                AlarmApiEnum.QUERY_ALARMS,
                query
        );
    }
}
