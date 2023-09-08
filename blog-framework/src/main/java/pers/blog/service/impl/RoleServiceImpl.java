package pers.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.ChangeStatusDto;
import pers.blog.domain.entity.Role;
import pers.blog.domain.vo.PageVo;
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

    /**
     * 查询角色列表
     * @param pageNum
     * @param pageSize
     * @param roleName
     * @param status
     * @return
     */
    @Override
    public ResponseResult listRole(Integer pageNum, Integer pageSize, String roleName, String status) {
        Page<Role> rolePage = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(roleName), Role::getRoleName, roleName);
        queryWrapper.eq(StringUtils.hasText(status), Role::getStatus, status);
        queryWrapper.orderByAsc(Role::getRoleSort);
        this.page(rolePage, queryWrapper);


        PageVo pageVo = new PageVo(rolePage.getRecords(), rolePage.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    /**
     * 修改角色状态
     * @param statusInfo
     * @return
     */
    @Override
    public ResponseResult changeStatus(ChangeStatusDto statusInfo) {
        Role role = this.getById(statusInfo.getRoleId());
        role.setStatus(statusInfo.getStatus());
        this.updateById(role);
        return ResponseResult.okResult();
    }
}
