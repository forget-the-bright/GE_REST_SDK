package io.github.forget_the_bright.ge.constant.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 采样模式枚举类，表示不同的数据采样方式。
 * Sampling mode enumeration, representing different data sampling methods.
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/5 下午2:59
 */
public enum SamplingMode {

    /**
     * 未定义: 采样模式未定义。
     * Undefined: Sampling mode is not defined.
     */
    Undefined(0),

    /**
     * 当前值: 获取当前值。时间间隔条件被忽略。
     * CurrentValue: Retrieves the current value. The time-interval criteria are ignored.
     */
    CurrentValue(1),

    /**
     * 插值: 根据间隔或样本数量以及时间框架条件，获取等间距的插值。
     * Interpolated: Retrieves evenly-spaced, interpolated values based on interval or NumberOfSamples and the time-frame criteria.
     */
    Interpolated(2),

    /**
     * 趋势: 返回每个指定间隔的原始最小值和原始最大值。使用趋势采样模式在检索绘图数据点时最大化性能。
     * 对于趋势采样模式，如果采样间隔不能被间隔长度均匀分割，Historian会忽略任何剩余的间隔。
     * Trend: Returns the raw minimum and raw maximum value for each specified interval. Use the Trend sampling mode to maximize performance when retrieving data points for plotting.
     * For the Trend sampling mode, if the sampling interval does not evenly divide by the interval length, Historian ignores any left-over intervals.
     */
    Trend(3),

    /**
     * 原始按时间: 根据时间框架条件获取原始归档值。
     * RawByTime: Retrieves raw archive values based on time-frame criteria.
     */
    RawByTime(4),

    /**
     * 原始按数量: 根据开始时间、样本数量和方向条件获取原始归档值。
     * 对于这种采样模式，结束时间条件被忽略。
     * RawByNumber: Retrieves raw archive values based on the StartTime criteria, the NumberOfSamples, and Direction criteria.
     * The EndTime criteria is ignored for this sampling mode.
     */
    RawByNumber(5),

    /**
     * 计算值: 根据样本数量、间隔、时间框架条件和计算模式条件获取等间距的计算值。
     * Calculated: Retrieves evenly spaced calculated values based on NumberOfSamples, interval, the time frame criteria, and the CalculationMode criteria.
     */
    Calculated(6),

    /**
     * 实验室: 返回实际收集的值，没有插值。
     * Lab: Returns actual collected values without interpolation.
     */
    Lab(7),

    /**
     * 插值转原始: 当样本数量少于可用样本时，提供原始数据替代插值数据。
     * InterpolatedtoRaw: Provides raw data in place of interpolated data when the number of samples are fewer than the available samples.
     */
    InterpolatedtoRaw(8),

    /**
     * 趋势到原始: 与趋势类似，但当请求的样本数量多于原始数据点时，返回所有可用的原始数据点，不再进行进一步处理。
     * TrendtoRaw: Similar to Trend, but returns all available raw data points with no further processing when more samples are requested than there are raw data points.
     */
    TrendtoRaw(9),

    /**
     * 实验到原始: 当样本数量少于可用样本时，为选定的计算数据提供原始数据。
     * LabtoRaw: Provides raw data for the selected calculated data, when NumberOfSamples is less than the available samples.
     */
    LabtoRaw(10),

    /**
     * 过滤切换原始: 使用以下值返回过滤的时间范围：1（真），0（假）。
     * 这种采样模式与时间范围和过滤标签条件一起使用。
     * 响应字符串以开始时间戳开始，以结束时间戳结束。
     * RawByFilterToggle: Returns filtered time ranges using the following values: 1 (true), 0 (false).
     * This sampling mode is used with the time range and filter tag conditions.
     * The response string starts with a starting time stamp and ends with an ending timestamp.
     */
    RawByFilterToggle(11);

    /**
     * 枚举值
     */
    private final int value;

    /**
     * 枚举构造函数
     *
     * @param value 枚举值
     */
    SamplingMode(int value) {
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
