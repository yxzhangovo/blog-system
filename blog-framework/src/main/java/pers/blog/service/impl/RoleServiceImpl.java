package pers.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pers.blog.constans.SystemConstants;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.AddRoleDto;
import pers.blog.domain.dto.ChangeStatusDto;
import pers.blog.domain.dto.UpdateRoleDto;
import pers.blog.domain.entity.Menu;
import pers.blog.domain.entity.Role;
import pers.blog.domain.entity.RoleMenu;
import pers.blog.domain.vo.GetRoleInfoVo;
import pers.blog.domain.vo.PageVo;
import pers.blog.mapper.RoleMapper;
import pers.blog.service.MenuService;
import pers.blog.service.RoleMenuService;
import pers.blog.service.RoleService;
import pers.blog.utils.BeanCopyUtils;
import pers.blog.utils.ImplUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zyx
 * @create: 2023/9/4
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMenuService roleMenuService;

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

    /**
     * 删除角色
     * @param ids
     * @return
     */
    @Override
    public ResponseResult deleteRoles(String ids) {
        Long[] list = ImplUtils.removeByIds(ids);
        for (Long id : list) {
            this.removeById(id);
        }
        return ResponseResult.okResult();
    }

    /**
     * 查询所有状态为正常的角色
     * @return
     */
    @Override
    public ResponseResult listAllRole() {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getStatus, 0);
        List<Role> list = this.list(queryWrapper);
        return ResponseResult.okResult(list);
    }

    /**
     * 添加角色
     * @param roleDto
     * @return
     */
    @Override
    @Transactional
    public ResponseResult addRole(AddRoleDto roleDto) {
        Role role = BeanCopyUtils.copyBean(roleDto, Role.class);
        this.save(role);

        List<Long> menuIds = roleDto.getMenuIds();
        List<RoleMenu> roleMenus = new ArrayList<>();
        for (Long menuId : menuIds) {
            roleMenus.add(new RoleMenu(role.getId(), menuId));
        }
        roleMenuService.saveBatch(roleMenus);

        return ResponseResult.okResult();
    }

    /**
     * 根据id查询角色信息
     * @param id
     * @return
     */
    @Override
    public ResponseResult getRoleInfo(Long id) {
        Role role = getById(id);
        GetRoleInfoVo roleInfoVo = BeanCopyUtils.copyBean(role, GetRoleInfoVo.class);
        return ResponseResult.okResult(roleInfoVo);

    }

    /**
     * 更新角色信息
     * @param roleDto
     * @return
     */
    @Override
    @Transactional
    public ResponseResult updateRole(UpdateRoleDto roleDto) {
        Role role = BeanCopyUtils.copyBean(roleDto, Role.class);
        this.updateById(role);

        // 删除对应的menuId
        LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenu::getRoleId, role.getId());
        roleMenuService.remove(queryWrapper);

        // 添加对应的menuId
        List<Long> menuIds = roleDto.getMenuIds();
        List<RoleMenu> roleMenus = new ArrayList<>();
        for (Long menuId : menuIds) {
            roleMenus.add(new RoleMenu(role.getId(), menuId));
        }
        roleMenuService.saveBatch(roleMenus);

        return ResponseResult.okResult();
    }
}
