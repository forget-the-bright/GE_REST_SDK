package io.github.forget_the_bright.ge.constant.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 定义过滤模式的枚举类，用于指定过滤条件的模式
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/5 下午2:59
 */
public enum FilterMode {
    /**
     * Retrieves data for the exact times that the filter condition is True.
     * 在过滤条件为真的确切时间检索数据。
     */
    ExactTime(1),

    /**
     * Retrieves data from the timestamp of the last False filter condition to the timestamp of the next True condition.
     * 从最后一个过滤条件为假的时间戳到下一个为真的时间戳之间检索数据。
     */
    BeforeTime(2),

    /**
     * Retrieves data from the timestamp of the True filter condition to the timestamp of the next False condition.
     * 从过滤条件为真的时间戳到下一个为假的时间戳之间检索数据。
     */
    AfterTime(3),

    /**
     * Retrieves data from the timestamp of the last False filter condition to the timestamp of the next False condition.
     * 从最后一个过滤条件为假的时间戳到下一个为假的时间戳之间检索数据。
     */
    BeforeAndAfterTime(4);

    private final int value;

    FilterMode(int value) {
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

