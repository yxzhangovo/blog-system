package pers.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.blog.domain.entity.Role;
import pers.blog.mapper.RoleMapper;
import pers.blog.service.RoleService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zyx
 * @create: 2023/9/4
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    /**
     * 查询用户角色信息
     * @param id
     * @return
     */
    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        // 如果是管理员, 返回admin
        if (id == 1L) {
            ArrayList<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return  roleKeys;
        }

        // 查询用户所具有的权限信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }
}
