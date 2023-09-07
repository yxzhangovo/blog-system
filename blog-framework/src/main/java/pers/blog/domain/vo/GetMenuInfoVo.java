package pers.blog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zyx
 * @create: 2023/9/7
 * @description: 获取菜单信息Vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMenuInfoVo {
    //菜单ID
    private Long id;
    //菜单名称
    private String menuName;
    //父菜单ID
    private Long parentId;
    //显示顺序
    private Integer orderNum;
    //路由地址
    private String path;

    //菜单类型（M目录 C菜单 F按钮）
    private String menuType;
    //菜单状态（0显示 1隐藏）
    private String visible;

    private String remark;

    //权限标识
    private String perms;
    //菜单图标
    private String icon;

}
