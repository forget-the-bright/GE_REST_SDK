package io.github.forget_the_bright.ge.entity.systems;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 历史服务器实体 (Historian Server Entity)
 * <p>
 * 该实体类用于表示历史服务器的相关信息。
 */
@Data
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class HistorianServerEntity {

    /**
     * 描述 (Description)
     * <p>
     * 历史服务器的描述信息。
     * <p>
     * 类型: String<br>
     * 示例: "Main Historian Server"<br>
     * 释义: 提供历史服务器的简要说明。
     */
    @JSONField(name = "description")
    private String description;

    /**
     * 历史服务器类型 (DHS Type)
     * <p>
     * 历史服务器的类型标识。
     * <p>
     * 类型: String<br>
     * 示例: "Enterprise"<br>
     * 释义: 表示历史服务器的类型。
     */
    @JSONField(name = "dhstype")
    private String dhsType;

    /**
     * 是否默认 (Is Default)
     * <p>
     * 指定是否为默认的历史服务器。
     * <p>
     * 类型: String<br>
     * 示例: "true"<br>
     * 释义: 如果为 "true"，则表示该服务器是默认服务器。
     */
    @JSONField(name = "isdefault")
    private String isDefault;

    /**
     * 登录用户 (Login User)
     * <p>
     * 登录历史服务器的用户名。
     * <p>
     * 类型: String<br>
     * 示例: "admin"<br>
     * 释义: 表示登录历史服务器的用户名。
     */
    @JSONField(name = "loginuser")
    private String loginUser;

    /**
     * 服务器名称 (Server Name)
     * <p>
     * 历史服务器的名称。
     * <p>
     * 类型: String<br>
     * 示例: "ServerA"<br>
     * 释义: 表示历史服务器的唯一标识名称。
     */
    @JSONField(name = "servername")
    private String serverName;

    /**
     * 系统名称 (System Name)
     * <p>
     * 历史服务器所属的系统名称。
     * <p>
     * 类型: String<br>
     * 示例: "SystemX"<br>
     * 释义: 表示历史服务器所属的系统。
     */
    @JSONField(name = "systemname")
    private String systemName;
}