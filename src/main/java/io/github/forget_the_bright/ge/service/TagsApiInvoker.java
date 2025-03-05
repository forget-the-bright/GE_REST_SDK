package io.github.forget_the_bright.ge.service;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.forget_the_bright.ge.constant.TagsApiEnum;
import io.github.forget_the_bright.ge.constant.attach.ApiModule;
import io.github.forget_the_bright.ge.core.ApiClient;
import io.github.forget_the_bright.ge.core.ApiUtil;
import io.github.forget_the_bright.ge.entity.request.tags.TagCommentEntity;
import io.github.forget_the_bright.ge.entity.response.TagsResult;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 标签服务调用工具类，提供对标签相关API的封装方法。
 *
 * <p>该类包含以下功能：
 * <ul>
 *     <li>查询标签列表。</li>
 *     <li>添加单个或批量标签。</li>
 *     <li>获取标签属性和注释信息。</li>
 *     <li>更新、重命名或删除标签。</li>
 *     <li>执行高级标签查询。</li>
 * </ul>
 *
 * <p>所有方法均通过 {@link ApiClient} 调用对应的API接口，并返回JSON格式的响应结果。
 */
public class TagsApiInvoker {


    /**
     * 根据指定参数查询标签列表。
     *
     * @param maxNumber 最大返回标签数量。值为0时使用默认限制。
     * @param nameMask  标签名称模糊匹配字符串，支持通配符（如星号 *）。
     * @return 包含查询结果的JSON对象，结构如下：
     * <pre>
     * {
     *   "ErrorCode": 11,
     *   "ErrorMessage": "Refine Search Results.",
     *   "Tags": [
     *     "XX.XXXX.XX-XXX_XX"
     *   ]
     * }
     * </pre>
     */
    public static TagsResult queryTagsByParams(int maxNumber, String nameMask) {
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
     * 根据指定参数查询标签列表。
     *
     * @param maxNumber 最大返回标签数量。值为0时使用默认限制。
     * @param nameMask  标签名称模糊匹配字符串，支持通配符（如星号 *）。
     * @return 包含查询结果的JSON对象，结构如下：
     * <pre>
     * {
     *   "ErrorCode": 11,
     *   "ErrorMessage": "Refine Search Results.",
     *   "Tags": [
     *     "XX.XXXX.XX-XXX_XX"
     *   ]
     * }
     * </pre>
     */
    public static TagsResult queryTagsByPath(int maxNumber, String nameMask) {
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

    /**
     * 添加标签评论。
     *
     * @param tagCommentEntity 标签评论对象。
     * @return 包含操作结果的JSON对象。
     */
    public static JSONObject addTagComment(TagCommentEntity tagCommentEntity) {
        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.ADD_TAG_COMMENT,
                tagCommentEntity
        );
    }

    /**
     * 添加单个标签。
     *
     * @param tagName 标签名称。
     * @return 包含操作结果的JSON对象。
     */
    public static JSONObject addSingleTag(String tagName) {
        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.ADD_SINGLE_TAG,
                tagName
        );
    }

    /**
     * 批量添加标签。
     *
     * @param tagNames 标签名称列表，以逗号分隔。
     * @return 包含操作结果的JSON对象。
     */
    public static JSONObject addBatchTags(String tagNames) {
        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.ADD_BATCH_TAGS,
                tagNames
        );
    }

    /**
     * 获取标签注释信息。
     *
     * @param begin    查询起始时间。
     * @param end      查询结束时间。
     * @param tagNames 标签名称列表，以逗号分隔。
     * @return 包含查询结果的JSON对象。
     */
    public static JSONObject getCommentsByQuery(Date begin, Date end, String tagNames) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("begin", ApiUtil.isNullExec(begin, () -> DateUtil.format(begin, DatePattern.UTC_FORMAT)));
        params.put("end", ApiUtil.isNullExec(end, () -> DateUtil.format(end, DatePattern.UTC_FORMAT)));
        params.put("tagNames", tagNames);
        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.GET_COMMENTS_BY_QUERY,
                params
        );
    }

    /**
     * 获取标签注释信息。
     *
     * @param begin    查询起始时间。
     * @param end      查询结束时间。
     * @param tagNames 标签名称列表，以逗号分隔。
     * @return 包含查询结果的JSON对象。
     */
    public static JSONObject getCommentsByPath(Date begin, Date end, String tagNames) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("begin", ApiUtil.isNullExec(begin, () -> DateUtil.format(begin, DatePattern.UTC_FORMAT)));
        params.put("end", ApiUtil.isNullExec(end, () -> DateUtil.format(end, DatePattern.UTC_FORMAT)));
        params.put("tagNames", tagNames);
        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.GET_COMMENTS_BY_PATH,
                params
        );
    }

    /**
     * 获取标签属性。
     *
     * @param tagName 标签名。
     * @return 包含操作结果的JSON对象。
     */
    public static JSONObject getTagPropertiesPath(String tagName) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("tagName", tagName);
        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.GET_TAG_PROPERTIES_PATH,
                params
        );
    }

    /**
     * 更新标签属性。
     *
     * @param tagName 标签名。
     * @param body    更新内容的JSON字符串。
     * @return 包含操作结果的JSON对象。
     */
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

    /**
     * 更新标签属性。
     *
     * @param tagName 标签名。
     * @param body    更新内容的JSON字符串。
     * @return 包含操作结果的JSON对象。
     */
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
     * @return JSONObject 包含API调用结果的响应对象，包含操作状态码和响应数据
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
     * 执行高级标签查询。
     *
     * <p>支持多种查询条件，包括但不限于计算类型、收集器信息、数据存储名称等。
     *
     * @param calctype             计算类型精确匹配（0,1,2），可选参数。
     * @param collectiondisabled   是否禁用收集器，必须为true/false，可选参数。
     * @param collectioninterval   收集间隔，值为0时匹配所有间隔，可选参数。
     * @param collectorcompression 收集器压缩方式，必须为true/false，可选参数。
     * @param collectorname        收集器名称模糊匹配，默认*表示匹配所有，可选参数。
     * @param collectortype        收集器类型精确匹配，可选参数。
     * @param comment              注释模糊匹配，默认*表示匹配所有，可选参数。
     * @param datastorename        数据存储名称模糊匹配，默认*表示匹配所有，可选参数。
     * @param datatype             数据类型精确匹配，可选参数。
     * @param description          描述模糊匹配，默认*表示匹配所有，可选参数。
     * @param egudescription       EGU描述模糊匹配，默认*表示匹配所有，可选参数。
     * @param enumeratedset        枚举集模糊匹配，默认*表示匹配所有，可选参数。
     * @param hasalias             是否具有别名，必须为true/false，可选参数。
     * @param isstale              是否标记为过期，必须为true/false，可选参数。
     * @param lastmodified         最后修改时间，应用 &gt;= 操作符返回符合条件的标签，可选参数。
     * @param lastmodifieduser     最后修改用户模糊匹配，默认*表示匹配所有，可选参数。
     * @param numberofelements     元素数量精确匹配，值为0时忽略此参数，可选参数。
     * @param pageno               分页页码，无效时返回空数据，可选参数。
     * @param pagesize             分页大小，有效范围为2-512，值为0时表示不分页返回所有，可选参数。
     * @param sourceaddress        源地址模糊匹配，默认*表示匹配所有，可选参数。
     * @param tagname              标签名称模糊匹配，默认*表示匹配所有，可选参数。
     * @param userdefinedtypename  用户定义类型名称模糊匹配，默认*表示匹配所有，可选参数。
     * @return 包含查询结果的JSON对象。
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
