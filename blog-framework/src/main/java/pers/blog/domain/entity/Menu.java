package pers.blog.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zyx
 * @create: 2023/9/4
 * @description: 菜单权限
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_menu")
public class Menu {

    private Long id;

    private String menuName;

    private Long parentId;

    private Integer orderNum;

    private String path;

    private String component;

    private Integer isFrame;    // 是否为外链: 0是, 1否

    private String menuType;    // 菜单类型: M目录, C菜单, F按钮

    private String visible; // 菜单状态: 0显示, 1隐藏

    private String status;  // 菜单状态: 0正常, 1停用

    private String perms;   // 权限标识

    private String icon;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String remark;
    
    private String delFlag; // 是否删除: 0未删除, 1已删除
}

