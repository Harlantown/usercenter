package com.songshu.usercenter.common.utils;

import com.songshu.usercenter.common.constant.BaseResponse;
import com.songshu.usercenter.common.constant.ErrorCode;
import com.songshu.usercenter.common.exception.BusinessException;
import com.sun.org.apache.regexp.internal.RE;

/**
 * @author Charmot
 * @Description
 * @create 2022-05-10 上午 10:52
 */
public class ResultUtils {

    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse(0, data,"ok", null);
    }

    public static <T> BaseResponse<T> failed(ErrorCode error){
        return new BaseResponse<>(error.getCode(), null, error.getMessage(), null);

    }

    public static  BaseResponse failed(ErrorCode error,String description){
        return new BaseResponse(error.getCode(), null, error.getMessage(), description);

    }

    public static  BaseResponse failed(int errorCode, String message, String description) {
        return new BaseResponse<>(errorCode, null, message, description);
    }
}
