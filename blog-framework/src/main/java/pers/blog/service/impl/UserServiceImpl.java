package pers.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.AddUserDto;
import pers.blog.domain.dto.ChangeUserStatusDto;
import pers.blog.domain.dto.UpdateUserDto;
import pers.blog.domain.entity.Role;
import pers.blog.domain.entity.User;
import pers.blog.domain.entity.UserRole;
import pers.blog.domain.vo.GetUserInfoVo;
import pers.blog.domain.vo.PageVo;
import pers.blog.domain.vo.UserInfoVo;
import pers.blog.enums.AppHttpCodeEnum;
import pers.blog.exception.SystemException;
import pers.blog.mapper.UserMapper;
import pers.blog.service.RoleService;
import pers.blog.service.UserRoleService;
import pers.blog.service.UserService;
import pers.blog.utils.BeanCopyUtils;
import pers.blog.utils.ImplUtils;
import pers.blog.utils.SecurityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zyx
 * @create: 2023/8/30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

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

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public ResponseResult register(User user) {
        // 非空判断(后期改为Validation框架)
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }

        // 是否重名判断
        if (userNameExist(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        save(user);

        return ResponseResult.okResult();
    }

    /**
     * 获取用户列表
     * @param pageNum
     * @param pageSize
     * @param userName
     * @param phonenumber
     * @param status
     * @return
     */
    @Override
    public ResponseResult getUserList(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(userName), User::getUserName, userName);
        queryWrapper.like(StringUtils.hasText(phonenumber), User::getPhonenumber, phonenumber);
        queryWrapper.eq(StringUtils.hasText(status), User::getStatus, status);
        Page<User> userPage = new Page<>(pageNum, pageSize);

        this.page(userPage, queryWrapper);

        PageVo pageVo = new PageVo(userPage.getRecords(), userPage.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    /**
     * 添加用户
     * @param userDto
     * @return
     */
    @Override
    public ResponseResult addUser(AddUserDto userDto) {
        // 对密码进行加密
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String password = encoder.encode(userDto.getPassword());
        String[] split = password.split("}");
        // 添加用户
        User user = BeanCopyUtils.copyBean(userDto, User.class);
        user.setPassword(split[1]);
        this.save(user);

        // 添加用户和角色关联信息
        List<Long> roleIds = userDto.getRoleIds();
        for (Long roleId : roleIds) {
            userRoleService.save(new UserRole(user.getId(), roleId));
        }

        return ResponseResult.okResult();
    }

    /**
     * 删除用户
     * @param ids
     * @return
     */
    @Override
    public ResponseResult deleteUsers(String ids) {
        Long[] list = ImplUtils.removeByIds(ids);
        for (Long id : list) {
            this.removeById(id);
        }

        return ResponseResult.okResult();
    }

    /**
     * 查询用户信息
     * @param id
     * @return
     */
    @Override
    public ResponseResult getUserInfo(Long id) {
        // 根据userId查询UserRole
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, id);
        List<UserRole> userRoles = userRoleService.list(queryWrapper);

        // 获取roleIds
        List<Long> roleIds = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            roleIds.add(userRole.getRoleId());
        }

        // 查询roles
        List<Role> roles = new ArrayList<>();
        for (Long roleId : roleIds) {
            roles.add(roleService.getById(roleId));
        }

        User user = this.getById(id);

        GetUserInfoVo userInfoVo = new GetUserInfoVo(roleIds, roles, user);
        return ResponseResult.okResult(userInfoVo);
    }

    /**
     * 后台更新用户信息
     * @param userDto
     * @return
     */
    @Override
    @Transactional
    public ResponseResult updateUser(UpdateUserDto userDto) {
        // 更新用户信息
        User user = BeanCopyUtils.copyBean(userDto, User.class);
        this.updateById(user);

        // 先删除原本的id
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, userDto.getId());
        userRoleService.remove(queryWrapper);

        // 添加id
        List<Long> roleIds = userDto.getRoleIds();
        List<UserRole> userRoles = new ArrayList<>();
        for (Long roleId : roleIds) {
            userRoles.add(new UserRole(userDto.getId(), roleId));
        }
        userRoleService.saveBatch(userRoles);


        return ResponseResult.okResult();
    }

    /**
     * 更新角色状态
     * @param userStatusDto
     * @return
     */
    @Override
    public ResponseResult changeUserStatus(ChangeUserStatusDto userStatusDto) {
        User user = getById(userStatusDto.getUserId());
        user.setStatus(userStatusDto.getStatus());
        updateById(user);
        return ResponseResult.okResult();
    }

    /**
     * 验证用户名是否存在
     * @param userName
     * @return
     */
    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName);
        return this.count(queryWrapper) > 0;
    }
}
