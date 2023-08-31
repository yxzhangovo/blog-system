package pers.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.User;
import pers.blog.domain.vo.UserInfoVo;
import pers.blog.mapper.UserMapper;
import pers.blog.service.UserService;
import pers.blog.utils.BeanCopyUtils;
import pers.blog.utils.SecurityUtils;

/**
 * @author: zyx
 * @create: 2023/8/30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 获取用户信息
     * @return
     */
    @Override
    public ResponseResult userInfo() {
        // 获取当前id
        Long userId = SecurityUtils.getUserId();

        // 根据id获取用户信息
        User userInfo = this.getById(userId);

        // 封装
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(userInfo, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @Override
    public ResponseResult updateUserInfo(User user) {
        this.updateById(user);
        return ResponseResult.okResult();
    }
}
