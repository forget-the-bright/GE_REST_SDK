package io.github.forget_the_bright.ge.entity.response.base;

import com.alibaba.fastjson.annotation.JSONField;
import io.github.forget_the_bright.ge.constant.common.Quality;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 表示一个样本数据的响应实体类。
 * 包含时间戳、值、质量指标和数据属性列表等信息。
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @date 2025/3/5 下午2:28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Sample {

    /**
     * 时间戳，表示数据记录的时间。
     */
    @JSONField(name = "TimeStamp")
    private Date timeStamp;

    /**
     * 值，根据实际情况可以考虑改为 int, long, 或 double 类型。
     */
    @JSONField(name = "Value")
    private String value; // Consider changing to int, long, or double if appropriate

    /**
     * 质量，表示数据的质量指标。
     * 3 表示合格，其他值表示不合格（暂时考虑）。
     */
    @JSONField(name = "Quality")
    private Quality quality;

    /**
     * 数据属性列表，包含与数据相关的属性集合。
     */
    @JSONField(name = "DataAttributes")
    private List<Object> dataAttributes;

}
