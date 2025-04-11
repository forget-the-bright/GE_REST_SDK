package io.github.forget_the_bright.ge.constant.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * This class represents the direction constants.
 * 该类表示方向常量。
 *
 * @author wanghao(helloworlwh@163.com)
 * @since 2025/03/05 14:59
 */
public enum Direction {
    /**
     * Represents the forward direction with a value of 1.
     * 表示值为1的向前方向。
     */
    Forward(1),

    /**
     * Represents the backward direction with a value of 0.
     * 表示值为0的向后方向。
     */
    Backward(0);

    private final int value;

    /**
     * Constructor to initialize the direction value.
     * 构造函数用于初始化方向值。
     *
     * @param value The integer value representing the direction.
     *              表示方向的整数值。
     */
    Direction(int value) {
        this.value = value;
    }

    /**
     * Get the integer value of the direction.
     * 获取方向的整数值。
     *
     * @return The integer value of the direction.
     *         方向的整数值。
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
