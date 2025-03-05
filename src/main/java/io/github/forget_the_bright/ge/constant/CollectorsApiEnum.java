package io.github.forget_the_bright.ge.constant;

import io.github.forget_the_bright.ge.constant.attach.ParamPosition;
import io.github.forget_the_bright.ge.entity.request.collectors.CollectorEntity;
import lombok.Getter;
import cn.hutool.http.Method;
import lombok.ToString;

/**
 * Collectors 模块接口枚举 (Collectors API Enum)
 * <p>
 * 该枚举类定义了 Collectors 模块的所有接口及其相关信息。
 */
@ToString
@Getter
public enum CollectorsApiEnum {

    /**
     * 设置 Azure 调试信息日志级别
     * <p>
     * 可以设置 0 到 4 之间的值。
     */
    SET_AZURE_LOG_LEVEL(
            "设置 Azure 调试信息日志级别",
            "/v1/collector/azureloglevel",
            Method.PUT,
            ParamPosition.BODY,
            ParamPosition.NONE,
            CollectorEntity.class
    ,null),

    /**
     * 删除或移动缓冲区文件
     * <p>
     * 建议将缓冲区文件移动到同一驱动器内的新文件夹。
     */
    BUFFER_CONTROL(
            "删除或移动缓冲区文件",
            "/v1/collector/buffercontrol",
            Method.PUT,
            ParamPosition.BODY,
            ParamPosition.NONE,
            CollectorEntity.class
    ,null),

    /**
     * 创建收集器实例
     * <p>
     * 用于创建新的收集器实例。
     */
    CREATE_NEW_INSTANCE(
            "创建收集器实例",
            "/v1/collector/createnewinstance",
            Method.POST,
            ParamPosition.BODY,
            ParamPosition.NONE,
            String.class
    ,null),

    /**
     * 删除收集器实例
     * <p>
     * 用于删除指定的收集器实例。
     */
    DELETE_COLLECTOR_INSTANCE(
            "删除收集器实例",
            "/v1/collector/deleteinstance",
            Method.PUT,
            ParamPosition.BODY,
            ParamPosition.NONE,
            CollectorEntity.class
    ,null),

    /**
     * 删除离线收集器实例
     * <p>
     * 用于删除离线的收集器实例。
     */
    DELETE_OFFLINE_INSTANCE(
            "删除离线收集器实例",
            "/v1/collector/deleteofflineinstance",
            Method.DELETE,
            ParamPosition.BODY,
            ParamPosition.NONE,
            CollectorEntity.class
    ,null),

    /**
     * 查看收集器详细信息
     * <p>
     * 用于查看收集器的详细信息。
     */
    COLLECTOR_DETAILS(
            "查看收集器详细信息",
            "/v1/collector/details",
            Method.GET,
            ParamPosition.NONE,
            ParamPosition.NONE,
            null
    ,null),

    /**
     * 更新收集器实例
     * <p>
     * 用于修改收集器实例的云参数，更改后收集器实例将重新启动。
     */
    EDIT_INSTANCE(
            "更新收集器实例",
            "/v1/collector/editinstance",
            Method.PUT,
            ParamPosition.BODY,
            ParamPosition.NONE,
            String.class
    ,null),

    /**
     * 更新收集器的服务器节点
     * <p>
     * 用于更改收集器的服务器节点。
     */
    HISTORIAN_NODE_CHANGE(
            "更新收集器的服务器节点",
            "/v1/collector/historiannodechange",
            Method.PUT,
            ParamPosition.BODY,
            ParamPosition.NONE,
            CollectorEntity.class
    ,null),

    /**
     * 获取收集器实例详细信息
     * <p>
     * 用于查看指定收集器实例的详细信息。
     */
    INSTANCE_DETAILS(
            "获取收集器实例详细信息",
            "/v1/collector/instancedetails/{interfaceName}",
            Method.GET,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
    ,null),

    /**
     * 启用或禁用消息压缩
     * <p>
     * 用于启用或禁用消息压缩功能。
     */
    MESSAGE_COMPRESSION(
            "启用或禁用消息压缩",
            "/v1/collector/messagecompression",
            Method.PUT,
            ParamPosition.BODY,
            ParamPosition.NONE,
            CollectorEntity.class
    ,null),

    /**
     * 查看收集器运行模式
     * <p>
     * 用于查看收集器的运行模式。
     */
    COLLECTOR_RUNNING_MODE(
            "查看收集器运行模式",
            "/v1/collector/mode/{interfaceName}",
            Method.GET,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
    ,null),

    /**
     * 暂停收集器数据收集
     * <p>
     * 用于暂停收集器的数据收集功能。
     */
    PAUSE_COLLECTION(
            "暂停收集器数据收集",
            "/v1/collector/pausecollection/{interfaceName}",
            Method.PUT,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
    ,null),

    /**
     * 重建收集器列表
     * <p>
     * 用于重建收集器列表。
     */
    REBUILD_COLLECTORS_LIST(
            "重建收集器列表",
            "/v1/collector/rebuild/{interfaceName}",
            Method.PUT,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
    ,null),

    /**
     * 重新启动收集器
     * <p>
     * 用于重新启动收集器。
     */
    RESTART_COLLECTOR(
            "重新启动收集器",
            "/v1/collector/restart",
            Method.PUT,
            ParamPosition.BODY,
            ParamPosition.NONE,
            CollectorEntity.class
    ,null),

    /**
     * 恢复收集器数据收集
     * <p>
     * 用于恢复收集器的数据收集功能。
     */
    RESUME_COLLECTION(
            "恢复收集器数据收集",
            "/v1/collector/resumecollection/{interfaceName}",
            Method.PUT,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
    ,null),

    /**
     * 设置收集器调试模式
     * <p>
     * 用于设置收集器的调试模式。
     */
    SET_DEBUG_MODE(
            "设置收集器调试模式",
            "/v1/collector/setdebugmode",
            Method.PUT,
            ParamPosition.BODY,
            ParamPosition.NONE,
            CollectorEntity.class
    ,null),

    /**
     * 启动收集器
     * <p>
     * 用于启动收集器。
     */
    START_COLLECTOR(
            "启动收集器",
            "/v1/collector/start",
            Method.PUT,
            ParamPosition.BODY,
            ParamPosition.NONE,
            CollectorEntity.class
    ,null),

    /**
     * 获取收集器状态
     * <p>
     * 用于查看收集器的状态。
     */
    COLLECTOR_STATUS(
            "获取收集器状态",
            "/v1/collector/status/{interfaceName}",
            Method.GET,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
    ,null),

    /**
     * 停止收集器
     * <p>
     * 用于停止收集器。
     */
    STOP_COLLECTOR(
            "停止收集器",
            "/v1/collector/stop",
            Method.PUT,
            ParamPosition.BODY,
            ParamPosition.NONE,
            CollectorEntity.class
    ,null),

    /**
     * 获取收集器的版本号
     * <p>
     * 用于查看收集器的版本号。
     */
    COLLECTOR_VERSION(
            "获取收集器的版本号",
            "/v1/collector/version",
            Method.GET,
            ParamPosition.QUERY,
            ParamPosition.NONE,
            null
    ,null),

    /**
     * 获取收集器的版本号（路径参数）
     * <p>
     * 用于查看指定收集器的版本号。
     */
    COLLECTOR_VERSION_BY_PATH(
            "获取收集器的版本号（路径参数）",
            "/v1/collector/version/{interfaceName}",
            Method.GET,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
    ,null),

    /**
     * 获取收集器关联的代理机器列表
     * <p>
     * 用于查看与 Historian 服务器关联的收集器代理机器列表。
     */
    COLLECTOR_MANAGER_LIST(
            "获取收集器关联的代理机器列表",
            "/v1/collectormanagerlist",
            Method.GET,
            ParamPosition.NONE,
            ParamPosition.NONE,
            null
    ,null),

    /**
     * 获取收集器所在计算机安装组件的详细信息
     * <p>
     * 用于查看收集器计算机上安装组件的详细信息。
     */
    INSTALL_COMPONENT_DETAILS(
            "获取收集器所在计算机安装组件的详细信息",
            "/v1/installcomponentdetails/{collectorType}/{collectorSubType}/{machine}",
            Method.GET,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
    ,null),

    /**
     * 获取指定机器上安装的 OPC 警报和事件服务器列表
     * <p>
     * 用于查看指定机器上安装的 OPC 警报和事件服务器列表。
     */
    LOCAL_OPC_AE_SERVERS(
            "获取指定机器上安装的 OPC 警报和事件服务器列表",
            "/v1/localopcaeservers/{machine}",
            Method.GET,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
    ,null),

    /**
     * 查看指定机器上安装的 OPC HDA 服务器列表
     * <p>
     * 用于查看指定机器上安装的 OPC HDA 服务器列表。
     */
    LOCAL_OPC_HDA_SERVERS(
            "查看指定机器上安装的 OPC HDA 服务器列表",
            "/v1/localopchdaservers/{machine}",
            Method.GET,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
    ,null),

    /**
     * 查看指定机器上安装的 OPC 服务器列表
     * <p>
     * 用于查看指定机器上安装的 OPC 服务器列表。
     */
    LOCAL_OPC_SERVERS(
            "查看指定机器上安装的 OPC 服务器列表",
            "/v1/localopcservers/{machine}",
            Method.GET,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
    ,null),

    /**
     * 获取离线收集器列表
     * <p>
     * 用于查看离线收集器列表。
     */
    OFFLINE_COLLECTORS(
            "获取离线收集器列表",
            "/v1/offlinecollectors",
            Method.GET,
            ParamPosition.NONE,
            ParamPosition.NONE,
            null,null
    );

    private final String desc;
    private final String path;
    private final Method method;
    private final ParamPosition primaryParamPosition;
    private final ParamPosition secondaryParamPosition;
    private final Class<?> entityType;
    private final Class<?> resultType; // 返回值实体类

    CollectorsApiEnum(String desc, String path, Method method,
                      ParamPosition primaryParamPosition, ParamPosition secondaryParamPosition,
                      Class<?> entityType,
                      Class<?> resultType) {
        this.desc = desc;
        this.path = path;
        this.method = method;
        this.primaryParamPosition = primaryParamPosition;
        this.secondaryParamPosition = secondaryParamPosition;
        this.entityType = entityType;
        this.resultType = resultType;
    }
}