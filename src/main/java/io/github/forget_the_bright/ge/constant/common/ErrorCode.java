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

    // 其他状态码省略...

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
    UNKNOWN_ERROR(3);

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