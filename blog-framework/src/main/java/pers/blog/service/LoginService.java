package pers.blog.service;

import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.LoginUserDto;
import pers.blog.domain.entity.LoginUser;
import pers.blog.domain.entity.User;

/**
 * @author: zyx
 * @create: 2023/9/4
 */
public interface LoginService {
    // 后台用户登录
    ResponseResult login(LoginUserDto user);
    // 用户退出登录
    ResponseResult logout();
}
