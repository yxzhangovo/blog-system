package pers.blog.service;

import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.User;

/**
 * @author: zyx
 * @create: 2023/8/29
 */
public interface BlogLoginService {
    // 用户登录
    ResponseResult login(User user);
    // 退出登录
    ResponseResult logout();

}
