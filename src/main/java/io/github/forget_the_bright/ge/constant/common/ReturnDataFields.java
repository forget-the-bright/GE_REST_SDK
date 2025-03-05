package io.github.forget_the_bright.ge.constant.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @Description TODO
 * @Author wanghao(helloworlwh @ 163.com)
 * @Date 2025/3/5 下午4:01
 */
public enum ReturnDataFields {

    /**
     * All Fields: Specifies that all data fields are returned in the query.
     * 所有字段：指定在查询中返回所有数据字段。
     */
    AllFields(1),

    /**
     * TimeStamp: The time stamp of the collected sample or an interval time stamp.
     * When specified in the query, returns the TimeStamp property.
     * 时间戳：收集样本的时间戳或间隔时间戳。在查询中指定时，返回时间戳属性。
     */
    TimeStamp(2),

    /**
     * Value: The collected value or sampled value; the data type of the value will be the same data type as the tag's raw data.
     * 值：收集的值或采样值；值的数据类型将与标签原始数据的数据类型相同。
     */
    Value(3),

    /**
     * Quality: When specified in the query, returns the Quality property.
     * Each sample in Current Value and Raw query retrieval has a quality of:
     * Good (3)
     * Not Available (2)
     * Uncertain (1)
     * Bad (0)
     * Interpolated and Lab Retrieval express quality as "percent good".
     * 质量：在查询中指定时，返回质量属性。当前值和原始查询检索中的每个样本都有以下质量：
     * 优质 (3)
     * 不可用 (2)
     * 不确定 (1)
     * 劣质 (0)
     * 插值和实验室检索将质量表达为“良好百分比”。
     */
    Quality(4);

    private final int value;

    ReturnDataFields(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    @JsonValue
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
