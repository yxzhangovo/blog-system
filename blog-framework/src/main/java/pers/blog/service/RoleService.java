package pers.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.AddRoleDto;
import pers.blog.domain.dto.ChangeStatusDto;
import pers.blog.domain.dto.UpdateRoleDto;
import pers.blog.domain.entity.Role;

import java.util.List;


/**
 * @author: zyx
 * @create: 2023/9/4
 */
public interface RoleService extends IService<Role> {
    // 根据id查询角色信息
    List<String> selectRoleKeyByUserId(Long id);
    // 查询角色列表
    ResponseResult listRole(Integer pageNum, Integer pageSize, String roleName, String status);
    // 修改角色状态
    ResponseResult changeStatus(ChangeStatusDto statusInfo);
    // 删除角色
    ResponseResult deleteRoles(String ids);
    // 查询所有状态为正常的角色
    ResponseResult listAllRole();
    // 添加角色
    ResponseResult addRole(AddRoleDto roleDto);
    // 根据id查询角色信息
    ResponseResult getRoleInfo(Long id);
    // 更新角色信息
    ResponseResult updateRole(UpdateRoleDto roleDto);
}
