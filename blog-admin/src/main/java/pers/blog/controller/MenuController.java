package pers.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.Menu;
import pers.blog.service.MenuService;

/**
 * @author: zyx
 * @create: 2023/9/6
 */
@Api(tags = "菜单", description = "后台菜单相关接口")
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
    @ApiOperation(value = "查询菜单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status",value = "可选, 模糊查询菜单"),
            @ApiImplicitParam(name = "menuName",value = "可选, 根据状态查询")
    })
    @GetMapping("/list")
    public ResponseResult getAllMenus(String status, String menuName) {
        return menuService.getAllMenus(status, menuName);
    }

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    @ApiOperation(value = "新增菜单")
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
    @ApiOperation(value = "根据id获取菜单信息")
    @GetMapping("/{id}")
    public ResponseResult getMenuInfo(@PathVariable Long id) {
        return menuService.getMenuInfo(id);
    }

    /**
     * 更新菜单信息
     * @param menu
     * @return
     */
    @ApiOperation(value = "更新菜单")
    @PutMapping
    public ResponseResult updateMenu(@RequestBody Menu menu) {
        return menuService.updateMenu(menu);
    }

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    @ApiOperation(value = "根据id删除菜单")
    @DeleteMapping("/{menuId}")
    public ResponseResult deleteMenu(@PathVariable Long menuId) {
        return menuService.deleteMenu(menuId);
    }

    /**
     * 获取菜单树
     * @return
     */
    @ApiOperation(value = "获取菜单树")
    @GetMapping("/treeselect")
    public ResponseResult getTreeSelect() {
        return menuService.getTreeSelect();
    }

    /**
     * 根据角色id获取对应的菜单
     * @param id
     * @return
     */
    @ApiOperation(value = "根据角色id获取对应的菜单")
    @GetMapping("/roleMenuTreeSelect/{id}")
    public ResponseResult getRoleMenuTreeSelect(@PathVariable Long id) {
        return menuService.getRoleMenuTree(id);
    }
}
