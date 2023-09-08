package pers.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.AddUserDto;
import pers.blog.domain.dto.UpdateUserDto;
import pers.blog.domain.entity.User;


/**
 * @author: zyx
 * @create: 2023/8/30
 */
public interface UserService extends IService<User> {
    // 获取用户信息
    ResponseResult userInfo();
    // 更新用户id
    ResponseResult updateUserInfo(User user);
    // 用户注册
    ResponseResult register(User user);
    // 获取用户列表
    ResponseResult getUserList(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status);
    // 添加用户
    ResponseResult addUser(AddUserDto userDto);
    // 删除用户
    ResponseResult deleteUsers(String ids);
    // 查询对应用户信息
    ResponseResult getUserInfo(Long id);
    // 后台更新用户信息
    ResponseResult updateUser(UpdateUserDto userDto);
}
