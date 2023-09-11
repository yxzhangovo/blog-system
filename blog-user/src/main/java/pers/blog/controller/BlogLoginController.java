package pers.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.User;
import pers.blog.enums.AppHttpCodeEnum;
import pers.blog.exception.SystemException;
import pers.blog.service.BlogLoginService;

/**
 * @author: zyx
 * @create: 2023/8/29
 */
@Api(tags = "前台登录", description = "前台登录相关接口")
@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }

    /**
     * 退出登录
     * @return
     */
    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public ResponseResult logout() {
        return blogLoginService.logout();
    }

}
