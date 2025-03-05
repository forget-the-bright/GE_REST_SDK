package io.github.forget_the_bright.ge.entity.request.data;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 标签数据创建实体 (Tag Data Creation Entity)
 * <p>
 * 该实体类用于表示标签数据创建时所需的信息。
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TagDataCreationEntity {

    /**
     * 标签名 (Tag Name)
     * <p>
     * 创建的标签名称。
     * <p>
     * 类型: String<br>
     * 示例: "NewSensor"<br>
     * 释义: 表示新创建的标签名称。
     */
    @JSONField(name = "TagName")
    private String tagName;

    /**
     * 样本数据 (Samples)
     * <p>
     * 标签对应的样本数据列表。
     * <p>
     * 类型: List<DataSampleEntity><br>
     * 示例: 见 DataSampleEntity 定义<br>
     * 释义: 包含一组具体的样本数据。
     */
    @JSONField(name = "samples")
    private List<DataSampleEntity> samples;
}