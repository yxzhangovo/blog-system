package pers.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.LoginUser;
import pers.blog.domain.entity.User;
import pers.blog.domain.vo.BlogUserLoginVo;
import pers.blog.domain.vo.UserInfoVo;
import pers.blog.service.BlogLoginService;
import pers.blog.utils.BeanCopyUtils;
import pers.blog.utils.JwtUtils;
import pers.blog.utils.RedisCache;

import java.util.Objects;

/**
 * @author: zyx
 * @create: 2023/8/29
 */
@Service
public class BlogLoginServiceImpl implements BlogLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;    // 用于验证用户名和密码

    @Autowired
    private RedisCache redisCache;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);// 用于认证的方法
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 获取userId, 生成Token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtils.createJWT(userId);

        // 将用户信息存入Redis
        redisCache.setCacheObject("bloglogin:" + userId, loginUser);

        // 将Token和userInfo封装返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        return ResponseResult.okResult(new BlogUserLoginVo(jwt, userInfoVo));
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public ResponseResult logout() {
        // 获取Token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 获取userId
        Long userId = loginUser.getUser().getId();
        // 删除Redis中的用户信息
        redisCache.deleteObject("bloglogin:" + userId);
        return ResponseResult.okResult();
    }
}
