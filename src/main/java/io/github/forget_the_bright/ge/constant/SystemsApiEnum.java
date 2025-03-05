package io.github.forget_the_bright.ge.constant;

import io.github.forget_the_bright.ge.constant.attach.ParamPosition;

import io.github.forget_the_bright.ge.entity.request.systems.HistorianServerEntity;
import io.github.forget_the_bright.ge.entity.request.systems.HorizontalScalabilityEntity;
import lombok.Getter;
import cn.hutool.http.Method;
import lombok.ToString;

@ToString
@Getter
public enum SystemsApiEnum {

    UPDATE_DATA_STORE("更新数据存储",
            "/v1/dataStore/{dataStoreName}",
            Method.PUT, ParamPosition.PATH, ParamPosition.BODY,
            String.class,null),

    DELETE_DATA_STORE("删除数据存储",
            "/v1/datastore",
            Method.DELETE, ParamPosition.BODY, ParamPosition.NONE,
            HorizontalScalabilityEntity.class,null),

    GET_DATA_STORES("获取数据存储列表",
            "/v1/datastores",
            Method.GET, ParamPosition.QUERY, ParamPosition.NONE,
            null,null),

    ADD_DATA_STORE("添加数据存储",
            "/v1/datastoretostorage",
            Method.POST, ParamPosition.BODY, ParamPosition.NONE,
            HorizontalScalabilityEntity.class,null),

    GET_DHS_MACHINES("获取DHS机器列表",
            "/v1/dhsmachines",
            Method.GET, ParamPosition.QUERY, ParamPosition.NONE,
            null,null),

    UPDATE_DHS_SERVICE("更新DHS服务信息",
            "/v1/dhsservice/{dHSServiceName}",
            Method.PUT, ParamPosition.PATH, ParamPosition.BODY,
            String.class,null),

    GET_DHS_SERVICES("获取DHS服务列表",
            "/v1/dhsservices",
            Method.GET, ParamPosition.QUERY, ParamPosition.NONE,
            null,null),

    GET_SERVERS("获取指定用户下的服务器信息",
            "/v1/getservers/{loginuser}",
            Method.GET, ParamPosition.PATH, ParamPosition.NONE,
            null,null),

    ADD_HISTORIAN_SERVER("添加服务器并归当前用户管理",
            "/v1/historianserver/addServer",
            Method.POST, ParamPosition.BODY, ParamPosition.NONE,
            HistorianServerEntity.class,null),

    DELETE_HISTORIAN_SERVER("删除服务器",
            "/v1/historianserver/deleteServer",
            Method.DELETE, ParamPosition.BODY, ParamPosition.NONE,
            HistorianServerEntity.class,null),

    MODIFY_HISTORIAN_SERVER("修复服务器",
            "/v1/historianserver/modifyServer",
            Method.PUT, ParamPosition.BODY, ParamPosition.NONE,
            HistorianServerEntity.class,null),

    GET_SERVER_TYPE("获取服务器类型信息",
            "/v1/historianserver/type",
            Method.GET, ParamPosition.NONE, ParamPosition.NONE,
            null,null),

    ADD_MACHINE("添加机器",
            "/v1/machine",
            Method.POST, ParamPosition.BODY, ParamPosition.NONE,
            HorizontalScalabilityEntity.class,null),

    DELETE_MACHINE("删除机器",
            "/v1/machine",
            Method.DELETE, ParamPosition.BODY, ParamPosition.NONE,
            HorizontalScalabilityEntity.class,null),

    CREATE_MIRROR_GROUP("创建镜像组",
            "/v1/mirrorgroup",
            Method.POST, ParamPosition.BODY, ParamPosition.NONE,
            HorizontalScalabilityEntity.class,null),

    RENAME_MIRROR_GROUP("更新镜像组",
            "/v1/mirrorgroup",
            Method.PUT, ParamPosition.BODY, ParamPosition.NONE,
            HorizontalScalabilityEntity.class,null),

    DELETE_MIRROR_GROUP("删除镜像组",
            "/v1/mirrorgroup",
            Method.DELETE, ParamPosition.BODY, ParamPosition.NONE,
            HorizontalScalabilityEntity.class,null),

    ADD_MIRROR_MACHINE("添加镜像机器",
            "/v1/mirrormachine",
            Method.POST, ParamPosition.BODY, ParamPosition.NONE,
            HorizontalScalabilityEntity.class,null),

    REMOVE_MIRROR_MACHINE("删除镜像机器",
            "/v1/mirrormachine",
            Method.DELETE, ParamPosition.BODY, ParamPosition.NONE,
            HorizontalScalabilityEntity.class,null),

    GET_PERF_TAG_DATA("获取系统的读取速率和接收速率",
            "/v1/performancecounter/perftagdata/{perfTagName}/{perfTagType}/{name}/{start}/{end}/{intervalMs}",
            Method.GET, ParamPosition.PATH, ParamPosition.NONE,
            null,null),

    GET_SERVER_PROPERTIES("获取服务器属性列表",
            "/v1/serverproperties",
            Method.GET, ParamPosition.NONE, ParamPosition.NONE,
            null,null),

    GET_DATA_STORES_BY_STORAGE("获取数据存储列表",
            "/v1/storage/datastores",
            Method.GET, ParamPosition.QUERY, ParamPosition.NONE,
            null,null),

    UPDATE_DEFAULT_DATA_STORE("更新默认数据存储",
            "/v1/storage/{storageName}",
            Method.PUT, ParamPosition.PATH, ParamPosition.BODY,
            String.class,null),

    GET_STORAGES("获取存储位置列表",
            "/v1/storages",
            Method.GET, ParamPosition.QUERY, ParamPosition.NONE,
            null,null),

    GET_SYSTEM_STATS("获取系统统计信息",
            "/v1/systemstats",
            Method.GET, ParamPosition.NONE, ParamPosition.NONE,
            null,null);


    private final String desc;
    private final String path;
    private final Method method;
    private final ParamPosition primaryParamPosition;
    private final ParamPosition secondaryParamPosition;
    private final Class<?> entityType;
    private final Class<?> resultType; // 返回值实体类

    SystemsApiEnum(String desc, String path, Method method, ParamPosition primaryParamPosition, ParamPosition secondaryParamPosition, Class<?> entityType,
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