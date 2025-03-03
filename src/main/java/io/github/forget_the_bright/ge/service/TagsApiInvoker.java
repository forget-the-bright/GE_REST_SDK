package io.github.forget_the_bright.ge.service;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.forget_the_bright.ge.constant.AlarmApiEnum;
import io.github.forget_the_bright.ge.constant.TagsApiEnum;
import io.github.forget_the_bright.ge.constant.child.ApiModule;
import io.github.forget_the_bright.ge.core.ApiClient;
import io.github.forget_the_bright.ge.entity.alarm.AlarmDeleteBackupEntity;
import io.github.forget_the_bright.ge.entity.tags.TagCommentEntity;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author wanghao(helloworlwh @ 163.com)
 * @Date 2025/3/3 下午3:15
 */
public class TagsApiInvoker {


    /**
     * 根据指定参数查询标签列表
     *
     * @param maxNumber 最大返回标签数量，0表示使用默认限制
     * @param nameMask  标签名称模糊匹配字符串,可以包含通配符，例如星号 (*)
     * @return 包含查询结果的JSON对象，结构为：
     * {
     * "ErrorCode": 11,
     * "ErrorMessage": " Refine Search Results.",
     * "Tags": [
     * "XX.XXXX.XX-XXX_XX"
     * ]
     * }
     */
    public static JSONObject queryTagsByParams(int maxNumber, String nameMask) {
        // 构造API请求参数集
        Map<String, Object> params = new HashMap<>();
        params.put("maxNumber", maxNumber);
        params.put("nameMask", nameMask);

        // 调用API客户端执行标签查询请求
        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.QUERY_TAGS_BY_PARAMS,
                params
        );
    }

    /**
     * 根据指定参数查询标签列表
     *
     * @param maxNumber 最大返回标签数量，0表示使用默认限制
     * @param nameMask  标签名称模糊匹配字符串,可以包含通配符，例如星号 (*)
     * @return 包含查询结果的JSON对象，结构为：
     * {
     * "ErrorCode": 11,
     * "ErrorMessage": " Refine Search Results.",
     * "Tags": [
     * "XX.XXXX.XX-XXX_XX"
     * ]
     * }
     */
    public static JSONObject queryTagsByPath(int maxNumber, String nameMask) {
        // 构造API请求参数集
        Map<String, Object> params = new HashMap<>();
        params.put("maxNumber", maxNumber);
        params.put("nameMask", nameMask);

        // 调用API客户端执行标签查询请求
        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.QUERY_TAGS_BY_PATH,
                params
        );
    }


    public static JSONObject addTagComment(TagCommentEntity tagCommentEntity) {
        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.ADD_TAG_COMMENT,
                tagCommentEntity
        );
    }


    public static JSONObject addSingleTag(String tagName) {
        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.ADD_SINGLE_TAG,
                tagName
        );
    }

    public static JSONObject addBatchTags(String tagNames) {
        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.ADD_BATCH_TAGS,
                tagNames
        );
    }


    public static JSONObject getCommentsByQuery(Date begin, Date end, String tagNames) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("begin", DateUtil.format(begin, DatePattern.UTC_FORMAT));
        params.put("end", DateUtil.format(end, DatePattern.UTC_FORMAT));
        params.put("tagNames", tagNames);
        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.GET_COMMENTS_BY_QUERY,
                params
        );
    }


    public static JSONObject getCommentsByPath(Date begin, Date end, String tagNames) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("begin", DateUtil.format(begin, DatePattern.UTC_FORMAT));
        params.put("end", DateUtil.format(end, DatePattern.UTC_FORMAT));
        params.put("tagNames", tagNames);
        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.GET_COMMENTS_BY_PATH,
                params
        );
    }


    public static JSONObject getTagPropertiesPath(String tagName) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("tagName", tagName);
        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.GET_TAG_PROPERTIES_PATH,
                params
        );
    }

    public static JSONObject getTagPropertiesPathBody(String tagName, String body) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("tagName", tagName);
        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.GET_TAG_PROPERTIES_PATH_BODY,
                params,
                body
        );
    }

    public static JSONObject updateTagProperties(String tagName, String body) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("tagName", tagName);
        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.UPDATE_TAG_PROPERTIES,
                params,
                body
        );
    }


    /**
     * 重命名标签（支持模拟操作）
     *
     * @param truerename 是否实际执行重命名操作
     *                   true: 执行实际重命名操作
     *                   false: 则创建别名
     * @param oldTagName 需要被替换的原始标签名称（非空）
     * @param newTagName 要替换为的新标签名称（需符合标签命名规范）
     */
    public static JSONObject renameTag(Boolean truerename, String oldTagName, String newTagName) {
        // 构建API请求参数集
        HashMap<String, Object> params = new HashMap<>();
        params.put("oldTagName", oldTagName);
        params.put("newTagName", newTagName);
        params.put("truerename", truerename);
        // 调用标签服务API执行重命名操作
        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.RENAME_TAG,
                params
        );
    }


    /**
     * 删除指定标签
     *
     * @param permanentDelete 是否永久删除标签，true表示执行物理删除，false表示逻辑删除(假删除)
     * @param tagName         需要删除的目标标签名称
     * @return JSONObject 包含API调用结果的响应对象，包含操作状态码和响应数据
     */
    public static JSONObject deleteTag(Boolean permanentDelete, String tagName) {
        // 构建API请求参数集
        HashMap<String, Object> params = new HashMap<>();
        params.put("tagName", tagName);
        params.put("permanentDelete", permanentDelete);

        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.DELETE_TAG,
                params
        );
    }


    /**
     * 执行标签列表高级查询
     *
     * @param calctype             计算类型精确匹配（0,1,2），可选参数
     * @param collectiondisabled   必须为true/false，否则报错，可选参数
     * @param collectioninterval   如果为0则匹配所有间隔，否则精确匹配，可选参数
     * @param collectorcompression 收集器压缩方式精确匹配（true/false），可选参数
     * @param collectorname        收集器名称模糊匹配，默认*表示匹配所有，可选参数
     * @param collectortype        收集器类型精确匹配，可选参数
     * @param comment              注释模糊匹配，默认*表示匹配所有，可选参数
     * @param datastorename        数据存储名称模糊匹配，默认*表示匹配所有，可选参数
     * @param datatype             数据类型精确匹配，可选参数
     * @param description          描述模糊匹配，默认*表示匹配所有，可选参数
     * @param egudescription       EGU描述模糊匹配，默认*表示匹配所有，可选参数
     * @param enumeratedset        枚举集模糊匹配，默认*表示匹配所有，可选参数
     * @param hasalias             必须为true/false，否则报错，可选参数
     * @param isstale              必须为true/false，否则报错，可选参数
     * @param lastmodified         应用>=操作符返回最后修改时间符合条件的标签，可选参数
     * @param lastmodifieduser     最后修改用户模糊匹配，默认*表示匹配所有，可选参数
     * @param numberofelements     元素数量精确匹配（0则忽略此参数），可选参数
     * @param pageno               分页页码（无效时返回空数据），可选参数
     * @param pagesize             分页大小（有效范围2-512，0表示不分页返回所有），可选参数
     * @param sourceaddress        源地址模糊匹配，默认*表示匹配所有，可选参数
     * @param tagname              标签名称模糊匹配，默认*表示匹配所有，可选参数
     * @param userdefinedtypename  用户定义类型名称模糊匹配，默认*表示匹配所有，可选参数
     * @return 包含API调用结果的响应对象
     * @http.response.details <table summary="响应详情">
     * <tr><td> 状态码 </td><td> 描述 </td><td> 响应头 </td></tr>
     * <tr><td> 200 </td><td> 请求成功 </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> 未授权 </td><td>  -  </td></tr>
     * <tr><td> 403 </td><td> 禁止访问 </td><td>  -  </td></tr>
     * <tr><td> 404 </td><td> 资源未找到 </td><td>  -  </td></tr>
     * </table>
     */
    public static JSONObject advancedSearchTags(String calctype,
                                                String collectiondisabled,
                                                String collectioninterval,
                                                String collectorcompression,
                                                String collectorname,
                                                String collectortype,
                                                String comment,
                                                String datastorename,
                                                String datatype,
                                                String description,
                                                String egudescription,
                                                String enumeratedset,
                                                String hasalias,
                                                String isstale,
                                                String lastmodified,
                                                String lastmodifieduser,
                                                String numberofelements,
                                                String pageno,
                                                String pagesize,
                                                String sourceaddress,
                                                String tagname,
                                                String userdefinedtypename) {
        // 构建API请求参数集
        HashMap<String, Object> params = new HashMap<>();
        if (calctype != null) params.put("calctype", calctype);
        if (collectiondisabled != null) params.put("collectiondisabled", collectiondisabled);
        if (collectioninterval != null) params.put("collectioninterval", collectioninterval);
        if (collectorcompression != null) params.put("collectorcompression", collectorcompression);
        if (collectorname != null) params.put("collectorname", collectorname);
        if (collectortype != null) params.put("collectortype", collectortype);
        if (comment != null) params.put("comment", comment);
        if (datastorename != null) params.put("datastorename", datastorename);
        if (datatype != null) params.put("datatype", datatype);
        if (description != null) params.put("description", description);
        if (egudescription != null) params.put("egudescription", egudescription);
        if (enumeratedset != null) params.put("enumeratedset", enumeratedset);
        if (hasalias != null) params.put("hasalias", hasalias);
        if (isstale != null) params.put("isstale", isstale);
        if (lastmodified != null) params.put("lastmodified", lastmodified);
        if (lastmodifieduser != null) params.put("lastmodifieduser", lastmodifieduser);
        if (numberofelements != null) params.put("numberofelements", numberofelements);
        if (pageno != null) params.put("pageno", pageno);
        if (pagesize != null) params.put("pagesize", pagesize);
        if (sourceaddress != null) params.put("sourceaddress", sourceaddress);
        if (tagname != null) params.put("tagname", tagname);
        if (userdefinedtypename != null) params.put("userdefinedtypename", userdefinedtypename);

        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.ADVANCED_TAG_QUERY,
                params
        );
    }

}
