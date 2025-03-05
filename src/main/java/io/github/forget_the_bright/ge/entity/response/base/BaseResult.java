package io.github.forget_the_bright.ge.entity.response.base;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * This class represents the base result for API responses.
 * 该类表示API响应的基础结果，包含通用的错误码和错误信息。
 *
 * @author wanghao(helloworlwh@163.com)
 * @date 2025/03/05 17:01
 */
public class BaseResult {

    /**
     * ErrorCode: The error code returned by the server.
     * 错误码：服务器返回的错误码。
     */
    @JSONField(name = "ErrorCode")
    private int errorCode;

    /**
     * ErrorMessage: The error message returned by the server if an error occurs.
     * 错误信息：如果发生错误，服务器返回的错误信息。
     */
    @JSONField(name = "ErrorMessage")
    private String errorMessage;

    // Getters and Setters (optional, depending on your project's coding standards)

    /**
     * Get the error code returned by the server.
     * 获取服务器返回的错误码。
     *
     * @return The error code.
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Set the error code returned by the server.
     * 设置服务器返回的错误码。
     *
     * @param errorCode The error code to set.
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Get the error message returned by the server if an error occurs.
     * 获取服务器返回的错误信息（如果发生错误）。
     *
     * @return The error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Set the error message returned by the server if an error occurs.
     * 设置服务器返回的错误信息（如果发生错误）。
     *
     * @param errorMessage The error message to set.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
