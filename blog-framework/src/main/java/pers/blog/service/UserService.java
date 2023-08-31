package pers.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.User;


/**
 * @author: zyx
 * @create: 2023/8/30
 */
public interface UserService extends IService<User> {
    // 获取用户信息
    ResponseResult userInfo();

}