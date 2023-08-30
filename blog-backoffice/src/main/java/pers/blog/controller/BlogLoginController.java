package pers.blog.controller;

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
@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    /**
     * 用户登录
     * @param user
     * @return
     */
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
    @PostMapping("/logout")
    public ResponseResult logout() {
        return blogLoginService.logout();
    }

}
