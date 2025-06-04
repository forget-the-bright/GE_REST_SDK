package io.github.forget_the_bright.ge.entity.response.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.forget_the_bright.ge.constant.common.Quality;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/6/4 上午10:19
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Trend {
    /**
     * 时间戳，表示数据记录的时间。
     */
    @JSONField(name = "TimeStamp", format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
    private Boolean quality;

    /**
     * 数据属性列表，包含与数据相关的属性集合。
     */
    @JSONField(name = "DataAttributes")
    private List<Object> dataAttributes;
}
