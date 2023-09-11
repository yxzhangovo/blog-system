package pers.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.LoginUserDto;
import pers.blog.domain.entity.LoginUser;
import pers.blog.domain.entity.User;
import pers.blog.enums.AppHttpCodeEnum;
import pers.blog.exception.SystemException;
import pers.blog.service.LoginService;
import pers.blog.utils.JwtUtils;
import pers.blog.utils.RedisCache;
import pers.blog.utils.SecurityUtils;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author: zyx
 * @create: 2023/9/4
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
     * 后台用户登录
     * @param user
     * @return
     */
    @Override
    public ResponseResult login(LoginUserDto user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if (Objects.isNull(authenticate)) {
            throw new SystemException(AppHttpCodeEnum.LOGIN_ERROR);
        }

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtils.createJWT(userId);

        redisCache.setCacheObject("login:" + userId, loginUser);

        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return ResponseResult.okResult(map);
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public ResponseResult logout() {
        Long userId = SecurityUtils.getUserId();
        redisCache.deleteObject("login" + userId);
        return ResponseResult.okResult();
    }
}
