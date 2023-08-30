package pers.blog.filter;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.LoginUser;
import pers.blog.enums.AppHttpCodeEnum;
import pers.blog.utils.JwtUtils;
import pers.blog.utils.RedisCache;
import pers.blog.utils.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author: zyx
 * @create: 2023/8/29
 * @description: Jwt登录校验过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中的Token
        String token = request.getHeader("token");

        if (!StringUtils.hasText(token)) {
            // 说明该接口不需要登录, 直接放行
            filterChain.doFilter(request, response);
            return;
        }

        // 解析Token获取userId
        Claims claims = null;
        try {
            claims = JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            // Token超时或Token不合法
            // 全局异常处理器处理Controller层, 这里需要自己处理
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        String userId = claims.getSubject();

        // 从Redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject("bloglogin" + userId);

        if (Objects.isNull(loginUser)) {
            // 说明登录过期
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }

        // 存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
