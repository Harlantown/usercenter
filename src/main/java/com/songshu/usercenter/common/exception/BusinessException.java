package com.songshu.usercenter.common.exception;

import com.songshu.usercenter.common.constant.ErrorCode;

/**
 * @author Charmot
 * @Description
 * @create 2022-05-10 上午 11:27
 */
public class BusinessException extends RuntimeException{

    private final int errorCode;

    private final String description;

    /**
     * 无描述信息的
     * @param errorCode
     */
    public BusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    /**
     * 有描述信息的异常
     * @param errorCode
     * @param description
     */
    public BusinessException(ErrorCode errorCode, String description){
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        this.description = description;
    }

    /**
     * 默认构造器
     * @param code
     * @param description
     * @param message
     */
    public BusinessException(int code, String description, String message) {
        super(message);
        this.errorCode = code;
        this.description = description;
    }



    public int getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }
}
