package pers.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.blog.constans.SystemConstants;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.Menu;
import pers.blog.domain.vo.GetAllMenusVo;
import pers.blog.domain.vo.GetMenuInfoVo;
import pers.blog.domain.vo.MenuVo;
import pers.blog.enums.AppHttpCodeEnum;
import pers.blog.exception.SystemException;
import pers.blog.mapper.MenuMapper;
import pers.blog.service.MenuService;
import pers.blog.service.RoleService;
import pers.blog.utils.BeanCopyUtils;
import pers.blog.utils.SecurityUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zyx
 * @create: 2023/9/4
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private RoleService roleService;

    /**
     * 根据用户id查询对应权限关键字
     * @param id
     * @return
     */
    @Override
    public List<String> selectPermsByUserId(Long id) {
        // 如果是管理员(id为1), 返回所有权限
        if (SecurityUtils.isAdmin()) {
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(Menu::getMenuType, SystemConstants.MENU, SystemConstants.BUTTON);
            queryWrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> menuList = this.list(queryWrapper);

            List<String> perms = menuList.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }

        // 否则返回对应权限
        return getBaseMapper().selectPermsByUserId(id);
    }

    /**
     * 动态路由
     * @param userId
     * @return
     */
    @Override
    public List<MenuVo> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<MenuVo> menus = null;

        // 判断是否为管理员, 是则返回所有符合要求的Menu
        if (SecurityUtils.isAdmin()) {
            menus = menuMapper.selectAllRouterMenu();
        } else {
            // 查询当前用户具有的Menu
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }

        // 构建MenuTree格式

        List<MenuVo> menuTree = builderMenuTree(menus, 0L);
        return menuTree;
    }

    /**
     * 子菜单查询
     * @param menus
     * @return
     */
    private List<MenuVo> builderMenuTree(List<MenuVo> menus, Long parentId) {
        List<MenuVo> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    /**
     * 获取传入参数的子Menu
     * @param menu
     * @param menus
     * @return
     */
    private List<MenuVo> getChildren(MenuVo menu, List<MenuVo> menus) {
        List<MenuVo> childrenList = menus.stream()
                .filter(menuVo -> menuVo.getParentId().equals(menu.getId()))
                .collect(Collectors.toList());
        return childrenList;
    }

    /**
     * 查询菜单列表
     * @param status
     * @param menuName
     * @return
     */
    @Override
    public ResponseResult getAllMenus(String status, String menuName) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(menuName), Menu::getMenuName, menuName);
        queryWrapper.eq(StringUtils.hasText(status), Menu::getStatus, status);
        queryWrapper.orderByAsc(Menu::getParentId).orderByAsc(Menu::getOrderNum);
        List<Menu> menuList = this.list(queryWrapper);
        List<GetAllMenusVo> menusVos = BeanCopyUtils.copyList(menuList, GetAllMenusVo.class);

        return ResponseResult.okResult(menusVos);
    }

    /**
     * 获取菜单信息
     * @param id
     * @return
     */
    @Override
    public ResponseResult getMenuInfo(Long id) {
        Menu menu = this.getById(id);
        GetMenuInfoVo menuVo = BeanCopyUtils.copyBean(menu, GetMenuInfoVo.class);
        return ResponseResult.okResult(menuVo);
    }

    /**
     * 更新菜单信息
     * @param menu
     * @return
     */
    @Override
    public ResponseResult updateMenu(Menu menu) {
        Long parentId = menu.getParentId();
        Long id = menu.getId();
        if (menu.getId().compareTo(menu.getParentId()) == 0) {
            throw new SystemException(AppHttpCodeEnum.PARENT_ERROR);
        } else {
            this.updateById(menu);
        }
        return ResponseResult.okResult();
    }

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    @Override
    public ResponseResult deleteMenu(Long menuId) {
        // 查询所有parentId, 查看是否有当前id的?
        List<Long> parentIds = this.list().stream()
                .map(Menu::getParentId)
                .collect(Collectors.toList());

        for (Long parentId : parentIds) {
            if (parentId.compareTo(menuId) == 0) {
                throw new SystemException(AppHttpCodeEnum.HAVE_CHILDREN);
            }
        }

        this.removeById(menuId);
        return ResponseResult.okResult();
    }
}
