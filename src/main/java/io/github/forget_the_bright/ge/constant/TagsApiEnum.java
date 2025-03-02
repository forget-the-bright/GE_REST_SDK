package io.github.forget_the_bright.ge.constant;
import io.github.forget_the_bright.ge.constant.child.ParamPosition;
import io.github.forget_the_bright.ge.entity.tags.TagCommentEntity;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpMethod;

/**
 * Tags 模块接口枚举 (Tags API Enum)
 * <p>
 * 该枚举类定义了 Tags 模块的所有接口及其相关信息
 */
@ToString
@Getter
public enum TagsApiEnum {

    /**
     * 查询标签列表
     * <p>通过给定的 nameMask 检索合格的标签名称列表</p>
     */
    QUERY_TAGS_BY_PARAMS(
            "查询标签列表",
            "/v1/tags",
            HttpMethod.GET,
            ParamPosition.QUERY,
            ParamPosition.NONE,
            null
    ),

    /**
     * 添加标签评论
     * <p>通过请求体添加标签评论</p>
     */
    ADD_TAG_COMMENT(
            "添加标签评论",
            "/v1/tags/addcomment",
            HttpMethod.POST,
            ParamPosition.BODY,
            ParamPosition.NONE,
            TagCommentEntity.class
    ),

    /**
     * 添加单个标签
     * <p>需要提供标签名称和数据类型</p>
     */
    ADD_SINGLE_TAG(
            "添加单个标签",
            "/v1/tags/addtag",
            HttpMethod.POST,
            ParamPosition.BODY,
            ParamPosition.NONE,
            String.class
    ),

    /**
     * 批量添加标签
     * <p>使用数组批量添加新标签</p>
     */
    ADD_BATCH_TAGS(
            "批量添加标签",
            "/v1/tags/addtags",
            HttpMethod.POST,
            ParamPosition.BODY,
            ParamPosition.NONE,
            String.class
    ),

    /**
     * 查看标签评论（参数在查询字符串）
     */
    GET_COMMENTS_BY_QUERY(
            "查询标签评论",
            "/v1/tags/comments",
            HttpMethod.GET,
            ParamPosition.QUERY,
            ParamPosition.NONE,
            null
    ),

    /**
     * 查看标签评论（参数在路径）
     */
    GET_COMMENTS_BY_PATH(
            "路径参数查询评论",
            "/v1/tags/comments/{tagNames}/{start}/{end}",
            HttpMethod.GET,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
    ),

    /**
     * 检索标签属性（GET方式）
     */
    GET_TAG_PROPERTIES_GET(
            "GET方式检索属性",
            "/v1/tags/properties/{tagName}",
            HttpMethod.GET,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
    ),

    /**
     * 检索标签属性（POST方式）
     */
    GET_TAG_PROPERTIES_POST(
            "POST方式检索属性",
            "/v1/tags/properties/{tagName}",
            HttpMethod.POST,
            ParamPosition.PATH,
            ParamPosition.BODY,
            String.class
    ),

    /**
     * 更新标签属性
     */
    UPDATE_TAG_PROPERTIES(
            "更新标签属性",
            "/v1/tags/properties/{tagName}",
            HttpMethod.PUT,
            ParamPosition.PATH,
            ParamPosition.BODY,
            String.class
    ),

    /**
     * 标签重命名
     * <p>包含路径参数和查询参数</p>
     */
    RENAME_TAG(
            "标签重命名",
            "/v1/tags/tagrename/{oldTagName}/{newTagName}",
            HttpMethod.PUT,
            ParamPosition.PATH,
            ParamPosition.QUERY,
            null
    ),

    /**
     * 路径参数查询标签列表
     */
    QUERY_TAGS_BY_PATH(
            "路径参数查询标签",
            "/v1/tags/{nameMask}/{maxNumber}",
            HttpMethod.GET,
            ParamPosition.PATH,
            ParamPosition.NONE,
            null
    ),

    /**
     * 删除标签
     * <p>包含路径参数和可选查询参数</p>
     */
    DELETE_TAG(
            "删除标签",
            "/v1/tags/{tagName}",
            HttpMethod.DELETE,
            ParamPosition.PATH,
            ParamPosition.QUERY,
            null
    ),

    /**
     * 高级标签列表查询
     * <p>包含多个查询参数</p>
     */
    ADVANCED_TAG_QUERY(
            "高级标签查询",
            "/v1/tagslist",
            HttpMethod.GET,
            ParamPosition.QUERY,
            ParamPosition.NONE,
            null
    );

    private final String desc;
    private final String path;
    private final HttpMethod method;
    private final ParamPosition primaryParamPosition;
    private final ParamPosition secondaryParamPosition;
    private final Class<?> entityType;

    TagsApiEnum(String desc, String path, HttpMethod method,
                ParamPosition primaryParamPosition, ParamPosition secondaryParamPosition,
                Class<?> entityType) {
        this.desc = desc;
        this.path = path;
        this.method = method;
        this.primaryParamPosition = primaryParamPosition;
        this.secondaryParamPosition = secondaryParamPosition;
        this.entityType = entityType;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Method: %s, Path: %s, Desc: %s, PrimaryParam: %s, SecondaryParam: %s, EntityType: %s",
                name(), method, path, desc, primaryParamPosition, secondaryParamPosition,
                entityType != null ? entityType.getSimpleName() : "null");
    }
}
