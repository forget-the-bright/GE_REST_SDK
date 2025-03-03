package io.github.forget_the_bright.ge.entity.tags;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 标签名称实体 (Tag Names Entity)
 * <p>
 * 该实体类用于表示标签名称的相关信息。
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TagNamesEntity {

    /**
     * 标签名 (Tag Names)
     * <p>
     * 标签名称列表。
     * <p>
     * 类型: String<br>
     * 示例: "SensorA,SensorB"<br>
     * 释义: 表示一组标签名称。
     */
    @JSONField(name = "tagNames")
    private String tagNames;
}