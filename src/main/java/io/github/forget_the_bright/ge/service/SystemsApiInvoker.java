package io.github.forget_the_bright.ge.service;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.forget_the_bright.ge.constant.SystemsApiEnum;
import io.github.forget_the_bright.ge.constant.attach.ApiModule;
import io.github.forget_the_bright.ge.core.ApiClient;
import io.github.forget_the_bright.ge.core.ApiUtil;
import io.github.forget_the_bright.ge.entity.request.systems.HistorianServerEntity;
import io.github.forget_the_bright.ge.entity.request.systems.HorizontalScalabilityEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SystemsApiInvoker {

    /**
     * 更新数据存储
     *
     * @param dataStoreName 数据存储名称
     * @param body          包含更新信息的实体对象
     * @return 返回更新结果的JSON对象
     */
    public static JSONObject updateDataStore(String dataStoreName, String body) {
        Map<String, Object> params = new HashMap<>();
        params.put("dataStoreName", dataStoreName);

        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.UPDATE_DATA_STORE,
                params,
                body
        );
    }

    /**
     * 删除数据存储
     *
     * @param body 包含删除信息的实体对象
     * @return 返回删除结果的JSON对象
     */
    public static JSONObject deleteDataStore(HorizontalScalabilityEntity body) {
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.DELETE_DATA_STORE,
                null,
                body
        );
    }

    /**
     * 获取数据存储列表
     *
     * @param dataStoreMask 查询参数
     * @return 返回数据存储列表的JSON对象
     */
    public static JSONObject getDataStores(String dataStoreMask) {
        Map<String, Object> params = new HashMap<>();
        params.put("dataStoreMask", dataStoreMask);
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.GET_DATA_STORES,
                params
        );
    }

    /**
     * 添加数据存储
     *
     * @param body 包含添加信息的实体对象
     * @return 返回添加结果的JSON对象
     */
    public static JSONObject addDataStore(HorizontalScalabilityEntity body) {
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.ADD_DATA_STORE,
                body
        );
    }

    /**
     * 获取DHS机器列表
     *
     * @param storageName 查询参数
     * @return 返回DHS机器列表的JSON对象
     */
    public static JSONObject getDhsMachines(String storageName) {
        Map<String, Object> params = new HashMap<>();
        params.put("storageName", storageName);
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.GET_DHS_MACHINES,
                params
        );
    }

    /**
     * 更新DHS服务信息
     *
     * @param dHSServiceName DHS服务名称
     * @param body           包含更新信息的实体对象
     * @return 返回更新结果的JSON对象
     */
    public static JSONObject updateDhsService(String dHSServiceName, String body) {
        Map<String, Object> params = new HashMap<>();
        params.put("dHSServiceName", dHSServiceName);

        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.UPDATE_DHS_SERVICE,
                params,
                body
        );
    }

    /**
     * 获取DHS服务列表
     *
     * @param dHSServiceMask DHS 服务名称的检索值
     * @param withReason 指示是否必须在 API 响应中检索原因。
     * @return 返回DHS服务列表的JSON对象
     */
    public static JSONObject getDhsServices(String dHSServiceMask,Boolean withReason) {
        Map<String, Object> params = new HashMap<>();
        params.put("dHSServiceMask", dHSServiceMask);
        params.put("withReason", withReason);
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.GET_DHS_SERVICES,
                params
        );
    }

    /**
     * 获取指定用户下的服务器信息
     *
     * @param loginuser 用户名
     * @return 返回服务器信息的JSON对象
     */
    public static JSONObject getServers(String loginuser) {
        Map<String, Object> params = new HashMap<>();
        params.put("loginuser", loginuser);

        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.GET_SERVERS,
                params
        );
    }

    /**
     * 添加服务器并归当前用户管理
     *
     * @param body 包含添加信息的实体对象
     * @return 返回添加结果的JSON对象
     */
    public static JSONObject addHistorianServer(HistorianServerEntity body) {
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.ADD_HISTORIAN_SERVER,
                body
        );
    }

    /**
     * 删除服务器
     *
     * @param body 包含删除信息的实体对象
     * @return 返回删除结果的JSON对象
     */
    public static JSONObject deleteHistorianServer(HistorianServerEntity body) {
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.DELETE_HISTORIAN_SERVER,
                body
        );
    }

    /**
     * 修复服务器
     *
     * @param body 包含修复信息的实体对象
     * @return 返回修复结果的JSON对象
     */
    public static JSONObject modifyHistorianServer(HistorianServerEntity body) {
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.MODIFY_HISTORIAN_SERVER,
                null,
                body
        );
    }

    /**
     * 获取服务器类型信息
     *
     * @return 返回服务器类型信息的JSON对象
     */
    public static JSONObject getServerType() {
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.GET_SERVER_TYPE
        );
    }

    /**
     * 添加机器
     *
     * @param body 包含添加信息的实体对象
     * @return 返回添加结果的JSON对象
     */
    public static JSONObject addMachine(HorizontalScalabilityEntity body) {
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.ADD_MACHINE,
                body
        );
    }

    /**
     * 删除机器
     *
     * @param body 包含删除信息的实体对象
     * @return 返回删除结果的JSON对象
     */
    public static JSONObject deleteMachine(HorizontalScalabilityEntity body) {
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.DELETE_MACHINE,
                body
        );
    }

    /**
     * 创建镜像组
     *
     * @param body 包含创建信息的实体对象
     * @return 返回创建结果的JSON对象
     */
    public static JSONObject createMirrorGroup(HorizontalScalabilityEntity body) {
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.CREATE_MIRROR_GROUP,
                body
        );
    }

    /**
     * 更新镜像组
     *
     * @param body 包含更新信息的实体对象
     * @return 返回更新结果的JSON对象
     */
    public static JSONObject renameMirrorGroup(HorizontalScalabilityEntity body) {
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.RENAME_MIRROR_GROUP,
                body
        );
    }

    /**
     * 删除镜像组
     *
     * @param body 包含删除信息的实体对象
     * @return 返回删除结果的JSON对象
     */
    public static JSONObject deleteMirrorGroup(HorizontalScalabilityEntity body) {
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.DELETE_MIRROR_GROUP,
                body
        );
    }

    /**
     * 添加镜像机器
     *
     * @param body 包含添加信息的实体对象
     * @return 返回添加结果的JSON对象
     */
    public static JSONObject addMirrorMachine(HorizontalScalabilityEntity body) {
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.ADD_MIRROR_MACHINE,
                body
        );
    }

    /**
     * 删除镜像机器
     *
     * @param body 包含删除信息的实体对象
     * @return 返回删除结果的JSON对象
     */
    public static JSONObject removeMirrorMachine(HorizontalScalabilityEntity body) {
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.REMOVE_MIRROR_MACHINE,
                body
        );
    }

    /**
     * 获取系统的读取速率和接收速率
     *
     * @param perfTagName  性能标签名称
     * @param perfTagType  性能标签类型
     * @param name         名称
     * @param start        开始时间
     * @param end          结束时间
     * @param intervalMs   时间间隔（毫秒）
     * @return 返回性能数据的JSON对象
     */
    public static JSONObject getPerfTagData(String perfTagName, String perfTagType, String name, Date start, Date end, Long intervalMs) {
        Map<String, Object> params = new HashMap<>();
        params.put("perfTagName", perfTagName);
        params.put("perfTagType", perfTagType);
        params.put("name", name);
        params.put("start",  ApiUtil.isNullExec(start, () -> DateUtil.format(start, DatePattern.UTC_FORMAT)));
        params.put("end",  ApiUtil.isNullExec(end, () -> DateUtil.format(end, DatePattern.UTC_FORMAT)));
        params.put("intervalMs", intervalMs);

        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.GET_PERF_TAG_DATA,
                params
        );
    }

    /**
     * 获取服务器属性列表
     *
     * @return 返回服务器属性列表的JSON对象
     */
    public static JSONObject getServerProperties() {
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.GET_SERVER_PROPERTIES
        );
    }

    /**
     * 获取数据存储列表
     *
     * @param storageName 存储名称
     * @return 返回数据存储列表的JSON对象
     */
    public static JSONObject getDataStoresByStorage(String storageName) {
        Map<String, Object> params = new HashMap<>();
        params.put("storageName", storageName);
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.GET_DATA_STORES_BY_STORAGE,
                params
        );
    }

    /**
     * 更新默认数据存储
     *
     * @param storageName 存储名称
     * @param body        包含更新信息的实体对象
     * @return 返回更新结果的JSON对象
     */
    public static JSONObject updateDefaultDataStore(String storageName, String body) {
        Map<String, Object> params = new HashMap<>();
        params.put("storageName", storageName);

        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.UPDATE_DEFAULT_DATA_STORE,
                params,
                body
        );
    }

    /**
     * 获取存储位置列表
     *
     * @param storageMask 位置掩码的值。
     * @return 返回存储位置列表的JSON对象
     */
    public static JSONObject getStorages(String storageMask) {
        Map<String, Object> params = new HashMap<>();
        params.put("storageMask", storageMask);
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.GET_STORAGES,
                params
        );
    }

    /**
     * 获取系统统计信息
     *
     * @return 返回系统统计信息的JSON对象
     */
    public static JSONObject getSystemStats() {
        return ApiClient.execute(
                ApiModule.SYSTEMS,
                SystemsApiEnum.GET_SYSTEM_STATS
        );
    }
}
