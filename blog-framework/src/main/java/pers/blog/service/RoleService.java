package pers.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.blog.domain.entity.Role;

import java.util.List;


/**
 * @author: zyx
 * @create: 2023/9/4
 */
public interface RoleService extends IService<Role> {
    // 根据id查询角色信息
    List<String> selectRoleKeyByUserId(Long id);
}
