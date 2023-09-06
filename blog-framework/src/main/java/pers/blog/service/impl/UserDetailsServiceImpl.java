package pers.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pers.blog.constans.SystemConstants;
import pers.blog.domain.entity.LoginUser;
import pers.blog.domain.entity.User;
import pers.blog.mapper.MenuMapper;
import pers.blog.mapper.UserMapper;

import java.util.List;
import java.util.Objects;

/**
 * @author: zyx
 * @create: 2023/8/29
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(queryWrapper);

        // 未查到抛出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }

        // 携带权限返回
        if (user.getType().equals(SystemConstants.ADMIN)) {
            List<String> list = menuMapper.selectPermsByUserId(user.getId());
            return new LoginUser(user, list);
        }

        // 返回
        return new LoginUser(user, null);
    }
}
