package pers.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.blog.domain.entity.User;
import pers.blog.mapper.UserMapper;
import pers.blog.service.UserService;

/**
 * @author: zyx
 * @create: 2023/8/30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
