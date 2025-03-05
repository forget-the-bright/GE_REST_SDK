package io.github.forget_the_bright.ge.constant.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 枚举类CalculationMode用于定义计算模式
 * 这里可以包括多种计算模式，例如：简单模式、复杂模式、自定义模式等
 * 每种模式可能对应着不同的计算规则或算法
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @date 2025/3/5 下午2:59
 */
public enum CalculationMode {
    /**
     * Calculation mode is not defined.
     * 计算模式未定义。
     */
    Undefined(0),

    /**
     * Retrieves the time-weighted average for each calculation interval.
     * 获取每个计算间隔的时间加权平均值。
     */
    Average(1),

    /**
     * Retrieves the time-weighted standard deviation for each calculation interval.
     * 获取每个计算间隔的时间加权标准差。
     */
    StandardDeviation(2),

    /**
     * Retrieves the time-weighted rate total for each calculation interval.
     * 获取每个计算间隔的时间加权速率总和。
     */
    Total(3),

    /**
     * Retrieves the minimum value for each calculation interval.
     * 获取每个计算间隔的最小值。
     */
    Minimum(4),

    /**
     * Retrieves the maximum value for each calculation interval.
     * 获取每个计算间隔的最大值。
     */
    Maximum(5),

    /**
     * Counts the number of raw samples found with good quality in the interval.
     * 计算间隔内良好质量的原始样本数量。
     */
    Count(6),

    /**
     * Retrieves the arithmetic average of all good quality raw samples for each calculation interval.
     * 获取每个计算间隔内所有良好质量原始样本的算术平均值。
     */
    RawAverage(7),

    /**
     * Retrieves the standard deviation of all good quality raw samples for each calculation interval.
     * 获取每个计算间隔内所有良好质量原始样本的标准差。
     */
    RawStandardDeviation(8),

    /**
     * Retrieves the arithmetic total (sum) of sampled values for each interval.
     * 获取每个间隔内样本值的算术总和（求和）。
     */
    RawTotal(9),

    /**
     * Retrieves the timestamp of the minimum value found within each calculation interval.
     * 获取每个计算间隔内找到的最小值的时间戳。
     */
    MinimumTime(10),

    /**
     * Retrieves the timestamp of the maximum value found within each calculation interval.
     * 获取每个计算间隔内找到的最大值的时间戳。
     */
    MaximumTime(11),

    /**
     * Retrieves the amount of time (milliseconds) during the interval when the data is of good quality and the filter condition is met.
     * 获取间隔内数据质量良好且满足过滤条件的时间量（毫秒）。
     */
    TimeGood(12),

    /**
     * Retrieves the amount of time a tag uses to transition to another state from a previous state during a time interval.
     * 获取标签在时间间隔内从上一个状态转换到另一个状态所用的时间量。
     */
    StateCount(13),

    /**
     * Retrieves the duration that a tag stayed in a given state within an interval.
     * 获取标签在间隔内停留在给定状态的持续时间。
     */
    StateTime(14),

    /**
     * Retrieves the OPCQAND, bit-wise AND operation of all the 16-bit OPC qualities of the raw samples stored in the specified interval.
     * 获取指定间隔内存储的所有原始样本的16位OPC质量位的位-wise AND操作结果。
     */
    OPCQAnd(15),

    /**
     * Retrieves the OPCQOR, bit-wise OR operation of all the 16-bit OPC qualities of the raw samples stored in the specified interval.
     * 获取指定间隔内存储的所有原始样本的16位OPC质量位的位-wise OR操作结果。
     */
    OPCQOr(16),

    /**
     * Retrieves the first good raw sample value for a given interval.
     * 获取给定间隔内的第一个良好原始样本值。
     */
    FirstRawValue(17),

    /**
     * Retrieves the first good raw timestamp for a given interval.
     * 获取给定间隔内的第一个良好原始样本的时间戳。
     */
    FirstRawTime(18),

    /**
     * Retrieves the last good raw sample value for a given time interval.
     * 获取给定时间间隔内的最后一个良好原始样本值。
     */
    LastRawValue(19),

    /**
     * Retrieves the last good timestamp of the last value for a given time interval.
     * 获取给定时间间隔内的最后一个良好值的时间戳。
     */
    LastRawTime(20),

    /**
     * Retrieves the statistics for a tag from the archive stored in the specified interval.
     * 从指定间隔内存储的档案中检索标签的统计信息。
     */
    TagStats(21);

    private final int value;
    /**
     * 枚举构造函数
     *
     * @param value 枚举值
     */
    CalculationMode(int value) {
        this.value = value;
    }
    /**
     * 获取枚举值
     *
     * @return 枚举值
     */
    public int getValue() {
        return value;
    }
    @JsonValue
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

