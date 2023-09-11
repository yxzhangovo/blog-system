package pers.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.ChangeStatusDto;
import pers.blog.service.RoleService;

/**
 * @author: zyx
 * @create: 2023/9/7
 */
@Api(tags = "角色", description = "角色相关接口")
@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查询角色列表
     * @param pageNum
     * @param pageSize
     * @param roleName
     * @param status
     * @return
     */
    @ApiOperation(value = "分页查询角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页数"),
            @ApiImplicitParam(name = "pageSize",value = "一页显示的内容"),
            @ApiImplicitParam(name = "roleName",value = "可选, 模糊查询角色名称"),
            @ApiImplicitParam(name = "status",value = "可选, 根据状态查询")
    })
    @GetMapping("/list")
    public ResponseResult listRole(Integer pageNum, Integer pageSize, String roleName, String status) {
        return roleService.listRole(pageNum, pageSize, roleName, status);
    }

    /**
     * 修改角色状态
     * @param statusInfo
     * @return
     */
    @ApiOperation(value = "修改角色状态")
    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody ChangeStatusDto statusInfo) {
        return roleService.changeStatus(statusInfo);
    }

    /**
     * 删除角色
     * @param ids
     * @return
     */
    @ApiOperation(value = "根据id删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "要删除的id, 可能为多个, 类型为字符串格式")
    })
    @DeleteMapping("/{id}")
    public ResponseResult deleteRole(@PathVariable("id") String ids) {
        return roleService.deleteRoles(ids);
    }

    /**
     * 查询所有状态正常的角色
     * @return
     */
    @ApiOperation(value = "查询所有状态为正常的角色")
    @GetMapping("/listAllRole")
    public ResponseResult listAllRole() {
        return roleService.listAllRole();
    }
}
