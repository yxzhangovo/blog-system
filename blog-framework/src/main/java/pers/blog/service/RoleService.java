package pers.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.ChangeStatusDto;
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
}
