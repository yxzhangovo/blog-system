package pers.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.ChangeStatusDto;
import pers.blog.service.RoleService;

/**
 * @author: zyx
 * @create: 2023/9/7
 */
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
    @GetMapping("/list")
    public ResponseResult listRole(Integer pageNum, Integer pageSize, String roleName, String status) {
        return roleService.listRole(pageNum, pageSize, roleName, status);
    }

    /**
     * 修改角色状态
     * @param statusInfo
     * @return
     */
    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody ChangeStatusDto statusInfo) {
        return roleService.changeStatus(statusInfo);
    }
}