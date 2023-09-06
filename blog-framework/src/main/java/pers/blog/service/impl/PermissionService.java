package pers.blog.service.impl;

import org.springframework.stereotype.Service;
import pers.blog.utils.SecurityUtils;

import java.util.List;

/**
 * @author: zyx
 * @create: 2023/9/6
 * @description: 权限验证
 */
@Service("ps")
public class PermissionService {
    /**
     * 判断当前用户是否具有指定权限
     * @param permission
     * @return
     */
    public Boolean hasPermission(String permission) {
        // 如果是超级管理员, 直接返回true
        if (SecurityUtils.isAdmin()) {
            return true;
        }

        // 获取当前用户权限列表, 判断是否存在permission
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        return permissions.contains(permission);
    }
}
