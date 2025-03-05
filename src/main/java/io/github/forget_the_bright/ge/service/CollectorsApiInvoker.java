package io.github.forget_the_bright.ge.service;

import com.alibaba.fastjson.JSONObject;
import io.github.forget_the_bright.ge.constant.CollectorsApiEnum;
import io.github.forget_the_bright.ge.constant.attach.ApiModule;
import io.github.forget_the_bright.ge.core.ApiClient;
import io.github.forget_the_bright.ge.entity.request.collectors.CollectorEntity;

import java.util.HashMap;
import java.util.Map;

public class CollectorsApiInvoker {

    /**
     * 设置 Azure 调试信息日志级别
     *
     * @param body 包含日志级别信息的实体对象
     * @return 返回设置结果的JSON对象
     */
    public static JSONObject setAzureLogLevel(CollectorEntity body) {
        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.SET_AZURE_LOG_LEVEL,
                body
        );
    }

    /**
     * 删除或移动缓冲区文件
     *
     * @param body 包含缓冲区文件操作信息的实体对象
     * @return 返回操作结果的JSON对象
     */
    public static JSONObject bufferControl(CollectorEntity body) {
        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.BUFFER_CONTROL,
                body
        );
    }

    /**
     * 创建收集器实例
     *
     * @param body 包含收集器实例信息的实体对象
     * @return 返回创建结果的JSON对象
     */
    public static JSONObject createNewInstance(String body) {
        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.CREATE_NEW_INSTANCE,
                body
        );
    }

    /**
     * 删除收集器实例
     *
     * @param body 包含收集器实例信息的实体对象
     * @return 返回删除结果的JSON对象
     */
    public static JSONObject deleteCollectorInstance(CollectorEntity body) {
        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.DELETE_COLLECTOR_INSTANCE,
                body
        );
    }

    /**
     * 删除离线收集器实例
     *
     * @param body 包含收集器实例信息的实体对象
     * @return 返回删除结果的JSON对象
     */
    public static JSONObject deleteOfflineInstance(CollectorEntity body) {
        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.DELETE_OFFLINE_INSTANCE,
                null,
                body
        );
    }

    /**
     * 查看收集器详细信息
     *
     * @return 返回收集器详细信息的JSON对象
     */
    public static JSONObject collectorDetails() {
        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.COLLECTOR_DETAILS
        );
    }

    /**
     * 更新收集器实例
     *
     * @param body 包含收集器实例信息的实体对象
     * @return 返回更新结果的JSON对象
     */
    public static JSONObject editInstance(String body) {
        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.EDIT_INSTANCE,
                body
        );
    }

    /**
     * 更新收集器的服务器节点
     *
     * @param body 包含服务器节点信息的实体对象
     * @return 返回更新结果的JSON对象
     */
    public static JSONObject historianNodeChange(CollectorEntity body) {
        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.HISTORIAN_NODE_CHANGE,
                body
        );
    }

    /**
     * 获取收集器实例详细信息
     *
     * @param interfaceName 接口名称
     * @return 返回收集器实例详细信息的JSON对象
     */
    public static JSONObject instanceDetails(String interfaceName) {
        Map<String, Object> params = new HashMap<>();
        params.put("interfaceName", interfaceName);

        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.INSTANCE_DETAILS,
                params
        );
    }

    /**
     * 启用或禁用消息压缩
     *
     * @param body 包含消息压缩信息的实体对象
     * @return 返回操作结果的JSON对象
     */
    public static JSONObject messageCompression(CollectorEntity body) {
        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.MESSAGE_COMPRESSION,
                body
        );
    }

    /**
     * 查看收集器运行模式
     *
     * @param interfaceName 接口名称
     * @return 返回收集器运行模式的JSON对象
     */
    public static JSONObject collectorRunningMode(String interfaceName) {
        Map<String, Object> params = new HashMap<>();
        params.put("interfaceName", interfaceName);

        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.COLLECTOR_RUNNING_MODE,
                params
        );
    }

    /**
     * 暂停收集器数据收集
     *
     * @param interfaceName 接口名称
     * @return 返回操作结果的JSON对象
     */
    public static JSONObject pauseCollection(String interfaceName) {
        Map<String, Object> params = new HashMap<>();
        params.put("interfaceName", interfaceName);

        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.PAUSE_COLLECTION,
                params
        );
    }

    /**
     * 重建收集器列表
     *
     * @param interfaceName 接口名称
     * @return 返回操作结果的JSON对象
     */
    public static JSONObject rebuildCollectorsList(String interfaceName) {
        Map<String, Object> params = new HashMap<>();
        params.put("interfaceName", interfaceName);

        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.REBUILD_COLLECTORS_LIST,
                params
        );
    }

    /**
     * 重新启动收集器
     *
     * @param body 包含收集器信息的实体对象
     * @return 返回操作结果的JSON对象
     */
    public static JSONObject restartCollector(CollectorEntity body) {
        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.RESTART_COLLECTOR,
                body
        );
    }

    /**
     * 恢复收集器数据收集
     *
     * @param interfaceName 接口名称
     * @return 返回操作结果的JSON对象
     */
    public static JSONObject resumeCollection(String interfaceName) {
        Map<String, Object> params = new HashMap<>();
        params.put("interfaceName", interfaceName);

        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.RESUME_COLLECTION,
                params
        );
    }

    /**
     * 设置收集器调试模式
     *
     * @param body 包含调试模式信息的实体对象
     * @return 返回操作结果的JSON对象
     */
    public static JSONObject setDebugMode(CollectorEntity body) {
        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.SET_DEBUG_MODE,
                body
        );
    }

    /**
     * 启动收集器
     *
     * @param body 包含收集器信息的实体对象
     * @return 返回操作结果的JSON对象
     */
    public static JSONObject startCollector(CollectorEntity body) {
        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.START_COLLECTOR,
                body
        );
    }

    /**
     * 获取收集器状态
     *
     * @param interfaceName 接口名称
     * @return 返回收集器状态的JSON对象
     */
    public static JSONObject collectorStatus(String interfaceName) {
        Map<String, Object> params = new HashMap<>();
        params.put("interfaceName", interfaceName);

        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.COLLECTOR_STATUS,
                params
        );
    }

    /**
     * 停止收集器
     *
     * @param body 包含收集器信息的实体对象
     * @return 返回操作结果的JSON对象
     */
    public static JSONObject stopCollector(CollectorEntity body) {
        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.STOP_COLLECTOR,
                body
        );
    }

    /**
     * 获取收集器的版本号
     *
     * @param interfaceName 接口名称
     * @return 返回收集器版本号的JSON对象
     */
    public static JSONObject collectorVersion(String interfaceName) {
        Map<String, Object> params = new HashMap<>();
        params.put("interfaceName", interfaceName);
        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.COLLECTOR_VERSION,
                params
        );
    }

    /**
     * 获取收集器的版本号（路径参数）
     *
     * @param interfaceName 接口名称
     * @return 返回收集器版本号的JSON对象
     */
    public static JSONObject collectorVersionByPath(String interfaceName) {
        Map<String, Object> params = new HashMap<>();
        params.put("interfaceName", interfaceName);

        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.COLLECTOR_VERSION_BY_PATH,
                params
        );
    }

    /**
     * 获取收集器关联的代理机器列表
     *
     * @return 返回代理机器列表的JSON对象
     */
    public static JSONObject collectorManagerList() {
        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.COLLECTOR_MANAGER_LIST
        );
    }

    /**
     * 获取收集器所在计算机安装组件的详细信息
     *
     * @param collectorType    收集器类型
     * @param collectorSubType 收集器子类型
     * @param machine          机器名称
     * @return 返回安装组件详细信息的JSON对象
     */
    public static JSONObject installComponentDetails(String collectorType, String collectorSubType, String machine) {
        Map<String, Object> params = new HashMap<>();
        params.put("collectorType", collectorType);
        params.put("collectorSubType", collectorSubType);
        params.put("machine", machine);

        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.INSTALL_COMPONENT_DETAILS,
                params
        );
    }

    /**
     * 获取指定机器上安装的 OPC 警报和事件服务器列表
     *
     * @param machine 机器名称
     * @return 返回OPC警报和事件服务器列表的JSON对象
     */
    public static JSONObject localOpcAeServers(String machine) {
        Map<String, Object> params = new HashMap<>();
        params.put("machine", machine);

        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.LOCAL_OPC_AE_SERVERS,
                params
        );
    }

    /**
     * 查看指定机器上安装的 OPC HDA 服务器列表
     *
     * @param machine 机器名称
     * @return 返回OPC HDA服务器列表的JSON对象
     */
    public static JSONObject localOpcHdaServers(String machine) {
        Map<String, Object> params = new HashMap<>();
        params.put("machine", machine);

        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.LOCAL_OPC_HDA_SERVERS,
                params
        );
    }

    /**
     * 查看指定机器上安装的 OPC 服务器列表
     *
     * @param machine 机器名称
     * @return 返回OPC服务器列表的JSON对象
     */
    public static JSONObject localOpcServers(String machine) {
        Map<String, Object> params = new HashMap<>();
        params.put("machine", machine);

        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.LOCAL_OPC_SERVERS,
                params
        );
    }

    /**
     * 获取离线收集器列表
     *
     * @return 返回离线收集器列表的JSON对象
     */
    public static JSONObject offlineCollectors() {
        return ApiClient.execute(
                ApiModule.COLLECTORS,
                CollectorsApiEnum.OFFLINE_COLLECTORS
        );
    }
}
