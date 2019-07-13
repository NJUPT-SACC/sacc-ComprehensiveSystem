package com.sacc.ComprehensiveSystem.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 统一封装返回数据
 * @param <T>
 */
public class RestResult<T> {
    static Logger logger = LoggerFactory.getLogger(RestResult.class);

    public static final int STATUS_SUCCESS = 2000;
    // 数据重复
    public static final int STATUS_DUPLICATION = 2001;
    public static final int STATUS_WRONG_FORMAT = 4000;
    public static final int STATUS_VALID_FAILED = 4001;
    public static final int STATUS_OUT_OF_SERVICE = 4002;
    public static final int STATUS_ENHANCE_FAILED = 5001;
    public static final int STATUS_OTHERS = 5002;
    public static final int STATUS_FALLBACK = 6001;

    private int status = 0;
    private String message;
    private T data;

    public RestResult(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public RestResult(){}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSONUtils.toJson(this);
    }
}
