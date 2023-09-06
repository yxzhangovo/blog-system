package pers.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.blog.domain.ResponseResult;
import pers.blog.service.MenuService;

/**
 * @author: zyx
 * @create: 2023/9/6
 */
@RestController
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private MenuService menuService;

    /**
     * 查询菜单列表
     * @param status
     * @param menuName
     * @return
     */
    @GetMapping("/menu/list")
    public ResponseResult getAllMenus(String status, String menuName) {
        return menuService.getAllMenus(status, menuName);
    }
}
