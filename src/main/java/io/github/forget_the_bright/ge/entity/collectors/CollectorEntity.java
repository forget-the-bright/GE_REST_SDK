package io.github.forget_the_bright.ge.entity.collectors;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 数据采集器实体 (Collector Entity)
 * <p>
 * 该实体类用于表示数据采集器的相关配置信息。
 */
@Data
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class CollectorEntity {

    /**
     * Azure 日志级别 (Azure Log Level)
     * <p>
     * 指定 Azure 日志记录的详细程度。
     * <p>
     * 类型: Integer<br>
     * 示例: 3<br>
     * 释义: 数值越大，日志越详细。
     */
    @JSONField(name = "azureLogLevel")
    private Integer azureLogLevel;

    /**
     * 缓冲模式 (Buffer Mode)
     * <p>
     * 指定缓冲区的工作模式。
     * <p>
     * 类型: Integer<br>
     * 示例: 1<br>
     * 释义: 具体含义需参考缓冲模式的枚举定义。
     */
    @JSONField(name = "bufferMode")
    private Integer bufferMode;

    /**
     * 缓冲路径 (Buffer Path)
     * <p>
     * 缓冲文件存储的路径。
     * <p>
     * 类型: String<br>
     * 示例: "/var/log/collector"<br>
     * 释义: 表示缓冲文件的实际存储位置。
     */
    @JSONField(name = "bufferPath")
    private String bufferPath;

    /**
     * 调试模式 (Debug Mode)
     * <p>
     * 指定是否启用调试模式。
     * <p>
     * 类型: Integer<br>
     * 示例: 1<br>
     * 释义: 如果为 1，则启用调试模式。
     */
    @JSONField(name = "debugMode")
    private Integer debugMode;

    /**
     * 删除标签 (Delete Tags)
     * <p>
     * 指定是否删除标签。
     * <p>
     * 类型: Boolean<br>
     * 示例: true<br>
     * 释义: 如果为 true，则删除指定的标签。
     */
    @JSONField(name = "deleteTags")
    private Boolean deleteTags;

    /**
     * 历史服务器节点 (Historian Node)
     * <p>
     * 指定历史服务器的节点名称。
     * <p>
     * 类型: String<br>
     * 示例: "NodeA"<br>
     * 释义: 表示历史服务器的具体节点。
     */
    @JSONField(name = "historianNode")
    private String historianNode;

    /**
     * 历史服务器用户名 (Historian User Name)
     * <p>
     * 历史服务器的用户名。
     * <p>
     * 类型: String<br>
     * 示例: "admin"<br>
     * 释义: 表示登录历史服务器的用户名。
     */
    @JSONField(name = "historianUserName")
    private String historianUserName;

    /**
     * 历史服务器密码 (Historian Password)
     * <p>
     * 历史服务器的密码。
     * <p>
     * 类型: String<br>
     * 示例: "password123"<br>
     * 释义: 表示登录历史服务器的密码。
     */
    @JSONField(name = "historianpassword")
    private String historianPassword;

    /**
     * 接口名称 (Interface Name)
     * <p>
     * 数据采集器的接口名称。
     * <p>
     * 类型: String<br>
     * 示例: "Interface1"<br>
     * 释义: 表示数据采集器使用的接口名称。
     */
    @JSONField(name = "interfaceName")
    private String interfaceName;

    /**
     * 消息压缩 (Message Compression)
     * <p>
     * 指定是否启用消息压缩。
     * <p>
     * 类型: Boolean<br>
     * 示例: true<br>
     * 释义: 如果为 true，则启用消息压缩功能。
     */
    @JSONField(name = "messageCompression")
    private Boolean messageCompression;

    /**
     * 模式 (Mode)
     * <p>
     * 数据采集器的工作模式。
     * <p>
     * 类型: Integer<br>
     * 示例: 2<br>
     * 释义: 具体含义需参考工作模式的枚举定义。
     */
    @JSONField(name = "mode")
    private Integer mode;

    /**
     * Windows 用户名 (Windows User Name)
     * <p>
     * Windows 系统的用户名。
     * <p>
     * 类型: String<br>
     * 示例: "winAdmin"<br>
     * 释义: 表示登录 Windows 系统的用户名。
     */
    @JSONField(name = "winUserName")
    private String winUserName;

    /**
     * Windows 密码 (Windows Password)
     * <p>
     * Windows 系统的密码。
     * <p>
     * 类型: String<br>
     * 示例: "winPassword123"<br>
     * 释义: 表示登录 Windows 系统的密码。
     */
    @JSONField(name = "winPassword")
    private String winPassword;
}