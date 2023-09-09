package pers.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.Menu;
import pers.blog.service.MenuService;

/**
 * @author: zyx
 * @create: 2023/9/6
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 查询菜单列表
     * @param status
     * @param menuName
     * @return
     */
    @GetMapping("/list")
    public ResponseResult getAllMenus(String status, String menuName) {
        return menuService.getAllMenus(status, menuName);
    }

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    @PostMapping
    public ResponseResult addMenu(@RequestBody Menu menu) {
        menuService.save(menu);
        return ResponseResult.okResult();
    }

    /**
     * 根据id查询菜单信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getMenuInfo(@PathVariable Long id) {
        return menuService.getMenuInfo(id);
    }

    /**
     * 更新菜单信息
     * @param menu
     * @return
     */
    @PutMapping
    public ResponseResult updateMenu(@RequestBody Menu menu) {
        return menuService.updateMenu(menu);
    }

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    @DeleteMapping("/{menuId}")
    public ResponseResult deleteMenu(@PathVariable Long menuId) {
        return menuService.deleteMenu(menuId);
    }

    /**
     * 获取菜单树
     * @return
     */
    @GetMapping("/treeselect")
    public ResponseResult getTreeSelect() {
        return menuService.getTreeSelect();
    }
}
