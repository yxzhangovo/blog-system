package pers.blog.exception;

import pers.blog.enums.AppHttpCodeEnum;

/**
 * @author: zyx
 * @create: 2023/8/30
 * @description: 自定义异常处理
 */

public class SystemException extends RuntimeException {
    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
}
