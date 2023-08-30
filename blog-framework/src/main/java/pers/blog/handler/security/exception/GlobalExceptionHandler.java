package pers.blog.handler.security.exception;

import lombok.extern.slf4j.Slf4j;
import pers.blog.enums.AppHttpCodeEnum;
import pers.blog.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pers.blog.domain.ResponseResult;

/**
 * @author: zyx
 * @create: 2023/8/30
 * @description: 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e) {
        log.error("出现异常{}", e);
        return ResponseResult.errorResult(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e) {
        log.error("出现异常{}", e);
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }
}
