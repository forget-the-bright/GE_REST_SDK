package io.github.forget_the_bright.ge.constant.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 历史记录服务状态码枚举类
 * Enumeration class for Historian service status codes.
 */
public enum ErrorCode {

    /**
     * 操作成功。
     * Operation successful.
     */
    SUCCESS(0),

    /**
     * 操作失败。
     * Operation failed.
     */
    FAILED(-1),

    /**
     * 操作因超时失败。
     * Operation failed due to timeout.
     */
    TIMEOUT(-2),

    /**
     * 未连接到历史记录服务器。
     * Not connected to Historian server.
     */
    NOT_CONNECTED(-3),

    /**
     * 服务器上不存在指定的收集器。
     * The given collector does not exist on the server.
     */
    COLLECTOR_NOT_FOUND(-4),

    /**
     * 操作不被支持。
     * Operation not supported.
     */
    NOT_SUPPORTED(-5),

    /**
     * 尝试覆盖已存在的数据样本。
     * Attempt to overwrite an existing data sample.
     */
    DUPLICATE_DATA(-6),

    /**
     * 用户名或密码错误。
     * Bad user name or password.
     */
    INVALID_USERNAME(-7),

    /**
     * 权限不足以执行操作。
     * Insufficient permissions for operation.
     */
    ACCESS_DENIED(-8),

    /**
     * 尝试写入未来时间过远的数据。
     * Attempted data write too far in the future.
     */
    WRITE_IN_FUTURE(-9),

    /**
     * 尝试写入离线存档。
     * Attempted data write to an offline archive.
     */
    WRITE_ARCHIVE_OFFLINE(-10),

    /**
     * 尝试写入只读存档。
     * Attempted data write to a read-only archive.
     */
    WRITE_ARCHIVE_READONLY(-11),

    /**
     * 尝试写入超出配置的活动范围。
     * Attempted data write beyond the configured active range.
     */
    WRITE_OUTSIDE_ACTIVE_RANGE(-12),

    /**
     * 尝试写入但没有可用存档。
     * Attempted data write with no available archives.
     */
    WRITE_NO_ARCHIVE_AVAILABLE(-13),

    /**
     * 请求的标签未找到。
     * The requested tag was not found.
     */
    INVALID_TAGNAME(-14),

    /**
     * 许可的标签数量超出限制。
     * Number of licensed tags exceeded.
     */
    LICENSED_TAG_COUNT_EXCEEDED(-15),

    /**
     * 许可的服务器连接数超出限制。
     * Number of licensed server connections exceeded.
     */
    LICENSED_CONNECTION_COUNT_EXCEEDED(-16),

    /**
     * 内部许可错误。
     * Internal license error.
     */
    INTERNAL_LICENSE_ERROR(-17),

    /**
     * 没有可用的标签数据。
     * No available tag data.
     */
    NO_VALUE(-18),

    /**
     * 服务器上已存在该收集器名称。
     * The given collector name already exists on the server.
     */
    DUPLICATE_COLLECTOR(-19),

    /**
     * 服务器或功能未授权。
     * Server or feature is not licensed.
     */
    NOT_LICENSED(-20),

    /**
     * 在计算中检测到循环引用。
     * Circular reference detected in calculation.
     */
    CIRCULAR_REFERENCE(-21),

    /**
     * 磁盘空间不足，无法执行备份。
     * Insufficient disk space to perform backup.
     */
    BACKUP_INSUFFICIENT_SPACE(-22),

    /**
     * 操作因服务器版本不受支持。
     * Operation unsupported due to server version.
     */
    INVALID_SERVER_VERSION(-23),

    /**
     * 查询结果大小超过上限。
     * Upper limit on query results exceeded.
     */
    QUERY_RESULT_SIZE_EXCEEDED(-24),

    /**
     * 尝试删除超出允许修改区间的数据。
     * Attempted data delete outside allowed modification interval.
     */
    DELETE_OUTSIDE_ACTIVE_RANGE(-25),

    /**
     * 无法访问报警和事件子系统。
     * Alarms and Events subsystem unreachable.
     */
    ALARM_ARCHIVER_UNAVAILABLE(-26),

    /**
     * 提供的参数无效。
     * A supplied argument is invalid.
     */
    ARGUMENT_EXCEPTION(-27),

    /**
     * 提供的参数为 NULL。
     * A supplied argument is NULL.
     */
    ARGUMENT_NULL_EXCEPTION(-28),

    /**
     * 提供的参数超出有效范围。
     * A supplied argument is outside the valid range.
     */
    ARGUMENT_OUT_OF_RANGE_EXCEPTION(-29),

    /**
     * 请求的枚举集未找到。
     * The requested Enumerated Set was not found.
     */
    INVALID_ENUMERATED_SET(-30),

    /**
     * 请求的数据存储未找到。
     * The requested data store was not found.
     */
    INVALID_DATA_STORE(-31),

    /**
     * 操作不允许。
     * Operation not permitted.
     */
    NOT_PERMITTED(-32),

    /**
     * 自定义数据类型不受支持。
     * The Custom data type is not supported.
     */
    INVALID_CUSTOM_DATA_TYPE(-33),


    /**
     * 用户定义的引用已存在。
     * N/A
     */
    IH_STATUS_EXISTING_USERDEF_REFERENCES(-34),

    /**
     * 标签名称无效或已被删除。
     * N/A
     */
    IH_STATUS_INVALID_TAGNAME_DELETEDTAG(-35),

    /**
     * DHS 节点名称无效。
     * N/A
     */
    IH_STATUS_INVALID_DHS_NODENAME(-36),

    /**
     * DHS 服务正在使用中。
     * N/A
     */
    IH_STATUS_DHS_SERVICE_IN_USE(-37),

    /**
     * DHS 存储正在使用中。
     * N/A
     */
    IH_STATUS_DHS_STORAGE_IN_USE(-38),

    /**
     * 镜像中的 DHS 节点过多。
     * N/A
     */
    IH_STATUS_DHS_TOO_MANY_NODES_IN_MIRROR(-39),

    /**
     * 存档已同步。
     * N/A
     */
    IH_STATUS_ARCHIVE_IN_SYNC(-40),

    /**
     * 存档名称无效。
     * N/A
     */
    INVALID_ARCHIVE_NAME(-41),

    /**
     * 会话 ID 无效。
     * Session id is invalid.
     */
    INVALID_SESSION(1),

    /**
     * 会话已过期。
     * Session has expired.
     */
    SESSION_EXPIRED(2),

    /**
     * 未知错误，请检查服务器日志。
     * Unknown error, please check server log.
     */
    UNKNOWN_ERROR(3),

    /**
     * 没有有效的客户端缓冲区管理器。
     * No valid client buffer manager.
     */
    NO_VALID_CLIENT_BUFFER_MANAGER(4),

    /**
     * 返回的数据集中没有值。
     * No value in returned data set.
     */
    NO_VALUE_IN_DATA_SET(5),

    /**
     * 标签不存在。
     * Tag doesn't exist.
     */
    TAG_NOT_EXISTING(6),

    /**
     * 中央缓冲服务器调用失败。
     * Service call to central buffer server fail.
     */
    CLIENT_BUFFER_MANAGER_COMMUNICATION_ERROR(7),

    /**
     * 不支持的标签类型。
     * Tag type is not supported.
     */
    TAG_TYPE_NOT_SUPPORTED(8),

    /**
     * 值类型与标签数据类型不匹配。
     * Value type doesn't match tag data type.
     */
    VALUE_TYPE_NOT_MATCH_TAG_DATA_TYPE(9),

    /**
     * 查询参数无效。
     * Invalid query parameter.
     */
    INVALID_PARAMETER(10),

    /**
     * 标签搜索结果超过 5000 条。
     * Tag Search Criteria result was more than 5000.
     */
    TAG_SEARCH_RESULT_IS_HUGE(11),

    /**
     * 无效的历史记录服务器。
     * No valid server or historian server name isn't in the server list.
     */
    INVALID_HISTORIAN_SERVER(12),

    /**
     * 收集器类型无效。
     * The collector type is not valid. For a list of collector types, refer to Collector Type and Subtype.
     */
    IH_STATUS_INVALID_INTERFACETYPE(-49),

    /**
     * 启动收集器失败。
     * Starting the collector has failed.
     */
    IH_STATUS_INTERFACE_START_FAIL(-50),

    /**
     * 停止收集器失败。
     * Stopping the collector has failed.
     */
    IH_STATUS_INTERFACE_STOP_FAIL(-51);

    private final int value;

    /**
     * 构造函数
     * Constructor
     *
     * @param value 状态码 / Status code
     */
    ErrorCode(int value) {
        this.value = value;
    }

    /**
     * 获取状态码
     * Get status code
     *
     * @return 状态码 / Status code
     */
    public int getValue() {
        return value;
    }

    @JsonValue
    @Override
    public String toString() {
        return String.valueOf(value);
    }

    /**
     * 根据值获取枚举实例
     *
     * @param value 枚举值
     * @return 枚举实例
     * @throws IllegalArgumentException 如果值不对应任何枚举实例
     */
    public static ErrorCode fromValue(int value) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.getValue() == value) {
                return errorCode;
            }
        }
        throw new IllegalArgumentException("未知的质量值: " + value);
    }
}