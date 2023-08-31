package pers.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.blog.domain.ResponseResult;
import pers.blog.mapper.UserMapper;
import pers.blog.service.UserService;

/**
 * @author: zyx
 * @create: 2023/8/31
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/userInfo")
    public ResponseResult userInfo() {
        return userService.userInfo();
    }


}
