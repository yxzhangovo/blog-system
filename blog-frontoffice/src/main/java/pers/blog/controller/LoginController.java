package pers.blog.controller;

import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.LoginUser;
import pers.blog.domain.entity.User;
import pers.blog.domain.vo.AdminUserInfoVo;
import pers.blog.domain.vo.MenuVo;
import pers.blog.domain.vo.RoutersVo;
import pers.blog.domain.vo.UserInfoVo;
import pers.blog.enums.AppHttpCodeEnum;
import pers.blog.exception.SystemException;
import pers.blog.service.LoginService;
import pers.blog.service.MenuService;
import pers.blog.service.RoleService;
import pers.blog.utils.BeanCopyUtils;
import pers.blog.utils.SecurityUtils;

import java.util.List;

/**
 * @author: zyx
 * @create: 2023/9/4
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    /**
     * 后台用户登录
     * @param user
     * @return
     */
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        if (!Strings.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }

    /**
     * 获取用户角色和权限信息
     * @return
     */
    @GetMapping("getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo() {
        // 获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();

        // 根据用户id查询权限信息
        List<String> perms =  menuService.selectPermsByUserId(loginUser.getUser().getId());

        // 根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        // 封装数据返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        AdminUserInfoVo adminUserInfo = new AdminUserInfoVo(perms, roleKeyList, userInfoVo);
        return ResponseResult.okResult(adminUserInfo);
    }

    /**
     * 动态路由
     * @return
     */
    @GetMapping("getRouters")
    public ResponseResult<RoutersVo> getRouters() {
        Long userId = SecurityUtils.getUserId();
        // 查询Menu, 结果是tree的形式
        List<MenuVo> menus = menuService.selectRouterMenuTreeByUserId(userId);

        // 封装数据返回
        return ResponseResult.okResult(new RoutersVo(menus));

    }
}
