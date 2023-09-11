package pers.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.User;
import pers.blog.service.UserService;

/**
 * @author: zyx
 * @create: 2023/8/31
 */
@Api(tags = "用户", description = "用户相关接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     * @return
     */
    @ApiOperation(value = "查询个人信息")
    @GetMapping("/userInfo")
    public ResponseResult userInfo() {
        return userService.userInfo();
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @ApiOperation(value = "更新用户信息")
    @PutMapping("/userInfo")
    public ResponseResult updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user) {
        return userService.register(user);
    }
}
