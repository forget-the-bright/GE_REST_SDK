package io.github.forget_the_bright.ge.entity.request.systems;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 水平扩展实体 (Horizontal Scalability Entity)
 * <p>
 * 该实体类用于表示水平扩展相关的配置信息。
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class HorizontalScalabilityEntity {

    /**
     * 数据存储名称 (Data Store Name)
     * <p>
     * 数据存储的名称。
     * <p>
     * 类型: String<br>
     * 示例: "DataStoreA"<br>
     * 释义: 表示数据存储的唯一标识名称。
     */
    @JSONField(name = "dataStoreName")
    private String dataStoreName;

    /**
     * 数据存储字符串 (Data Store String)
     * <p>
     * 数据存储的连接字符串。
     * <p>
     * 类型: String<br>
     * 示例: "jdbc:mysql://localhost:3306/datastore"<br>
     * 释义: 表示数据存储的连接信息。
     */
    @JSONField(name = "dataStoreString")
    private String dataStoreString;

    /**
     * 默认 (Default)
     * <p>
     * 指定是否为默认的数据存储。
     * <p>
     * 类型: Boolean<br>
     * 示例: true<br>
     * 释义: 如果为 true，则表示该数据存储是默认存储。
     */
    @JSONField(name = "default")
    private Boolean defaultFlag;

    /**
     * 描述 (Description)
     * <p>
     * 数据存储的描述信息。
     * <p>
     * 类型: String<br>
     * 示例: "Primary Data Store"<br>
     * 释义: 提供数据存储的简要说明。
     */
    @JSONField(name = "description")
    private String description;

    /**
     * 是否默认 (Is Default)
     * <p>
     * 指定是否为默认的存储。
     * <p>
     * 类型: Boolean<br>
     * 示例: true<br>
     * 释义: 如果为 true，则表示该存储是默认存储。
     */
    @JSONField(name = "isDefault")
    private Boolean isDefault;

    /**
     * 是否特殊镜像 (Is Special Mirror)
     * <p>
     * 指定是否为特殊的镜像存储。
     * <p>
     * 类型: Boolean<br>
     * 示例: false<br>
     * 释义: 如果为 true，则表示该存储是一个特殊的镜像存储。
     */
    @JSONField(name = "isSpecialMirror")
    private Boolean isSpecialMirror;

    /**
     * 镜像机器名称 (Mirror Machine Name)
     * <p>
     * 镜像机器的名称。
     * <p>
     * 类型: String<br>
     * 示例: "MirrorMachineA"<br>
     * 释义: 表示镜像机器的唯一标识名称。
     */
    @JSONField(name = "mirrorMachineName")
    private String mirrorMachineName;

    /**
     * 镜像存储名称 (Mirror Storage Name)
     * <p>
     * 镜像存储的名称。
     * <p>
     * 类型: String<br>
     * 示例: "MirrorStorageA"<br>
     * 释义: 表示镜像存储的唯一标识名称。
     */
    @JSONField(name = "mirrorStorageName")
    private String mirrorStorageName;

    /**
     * 新镜像存储名称 (Mirror Storage New Name)
     * <p>
     * 新的镜像存储名称。
     * <p>
     * 类型: String<br>
     * 示例: "MirrorStorageB"<br>
     * 释义: 表示新的镜像存储名称。
     */
    @JSONField(name = "mirrorStorageNewName")
    private String mirrorStorageNewName;

    /**
     * 节点名称 (Node Name)
     * <p>
     * 节点的名称。
     * <p>
     * 类型: String<br>
     * 示例: "NodeA"<br>
     * 释义: 表示节点的唯一标识名称。
     */
    @JSONField(name = "nodeName")
    private String nodeName;

    /**
     * 节点列表 (Nodes)
     * <p>
     * 节点的列表信息。
     * <p>
     * 类型: String<br>
     * 示例: "NodeA,NodeB"<br>
     * 释义: 表示一组节点的名称。
     */
    @JSONField(name = "nodes")
    private String nodes;

    /**
     * 存储名称 (Storage Name)
     * <p>
     * 存储的名称。
     * <p>
     * 类型: String<br>
     * 示例: "StorageA"<br>
     * 释义: 表示存储的唯一标识名称。
     */
    @JSONField(name = "storageName")
    private String storageName;

    /**
     * 存储字符串 (Storage String)
     * <p>
     * 存储的连接字符串。
     * <p>
     * 类型: String<br>
     * 示例: "jdbc:mysql://localhost:3306/storage"<br>
     * 释义: 表示存储的连接信息。
     */
    @JSONField(name = "storageString")
    private String storageString;

    /**
     * URI 基础地址 (URI Base Address)
     * <p>
     * URI 的基础地址。
     * <p>
     * 类型: String<br>
     * 示例: "http://example.com/api"<br>
     * 释义: 表示 API 或服务的基础地址。
     */
    @JSONField(name = "uriBaseAddress")
    private String uriBaseAddress;
}
