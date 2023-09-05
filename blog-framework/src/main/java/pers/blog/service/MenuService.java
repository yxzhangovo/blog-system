package pers.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.blog.domain.entity.Menu;
import pers.blog.domain.vo.MenuVo;

import java.util.List;


/**
 * @author: zyx
 * @create: 2023/9/4
 */
public interface MenuService extends IService<Menu> {
    // 根据id查询权限信息
    List<String> selectPermsByUserId(Long id);
    // 动态路由
    List<MenuVo> selectRouterMenuTreeByUserId(Long userId);
}
