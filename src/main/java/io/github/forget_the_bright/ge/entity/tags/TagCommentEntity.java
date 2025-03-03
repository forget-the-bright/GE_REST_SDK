package io.github.forget_the_bright.ge.entity.tags;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 标签评论实体 (Tag Comment Entity)
 * <p>
 * 该实体类用于表示标签的评论信息。
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TagCommentEntity {

    /**
     * 评论内容 (Comment)
     * <p>
     * 标签的评论内容。
     * <p>
     * 类型: String<br>
     * 示例: "This is a test comment."<br>
     * 释义: 表示对标签的具体评论。
     */
    @JSONField(name = "comment")
    private String comment;

    /**
     * 标签名 (Tag Name)
     * <p>
     * 关联的标签名称。
     * <p>
     * 类型: String<br>
     * 示例: "SensorA"<br>
     * 释义: 表示评论所属的标签。
     */
    @JSONField(name = "tagName")
    private String tagName;

    /**
     * 时间戳 (Timestamp)
     * <p>
     * 评论的时间戳。
     * <p>
     * 类型: String<br>
     * 格式: ISO 8601<br>
     * 示例: "2023-10-01T12:00:00Z"<br>
     * 释义: 表示评论创建的时间点。
     */
    @JSONField(name = "timeStamp", format = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date timeStamp;
}