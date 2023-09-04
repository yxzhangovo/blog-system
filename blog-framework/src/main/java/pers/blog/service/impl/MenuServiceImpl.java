package pers.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.blog.constans.SystemConstants;
import pers.blog.domain.entity.Menu;
import pers.blog.mapper.MenuMapper;
import pers.blog.service.MenuService;
import pers.blog.service.RoleService;

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
        if (id == 1L) {
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
}
