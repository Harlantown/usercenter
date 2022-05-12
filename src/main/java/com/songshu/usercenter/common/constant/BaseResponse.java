package com.songshu.usercenter.common.constant;

import com.songshu.usercenter.common.exception.BusinessException;

import java.io.Serializable;

/**
 * @author Charmot
 * @Description
 * @create 2022-05-10 上午 10:42
 */
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 8155276002991780889L;

    private int code;

    private T data;

    private String message;

    private String description;

    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, T data) {
        this(code,data,"","");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage(), errorCode.getDescription());

    }

    public BaseResponse(BusinessException exception) {
        this(exception.getErrorCode(), null, exception.getMessage(), exception.getDescription());

    }
    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
