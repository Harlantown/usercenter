package com.songshu.usercenter.common.exception;

import com.songshu.usercenter.common.constant.BaseResponse;
import com.songshu.usercenter.common.constant.ErrorCode;
import com.songshu.usercenter.common.utils.ResultUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Charmot
 * @Description
 * @create 2022-05-10 上午 11:50
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessExceptionHandler(BusinessException e){
        return ResultUtils.failed(e.getErrorCode(), e.getMessage(), e.getDescription());

    }

    @ExceptionHandler({RuntimeException.class})
    public BaseResponse runtimeExceptionHandler(RuntimeException e){
        return ResultUtils.failed(ErrorCode.SYSTEM_ERROR, e.getMessage());

    }

}
