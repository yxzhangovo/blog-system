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
 * @create: 2023/8/28
 * @description: 分类表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("zyx_category")
public class Category {
    private Long id;

    private String name;

    private Long pid;   // 父分类id: 若不存在则为-1

    private String description;

    private String status;  // 状态: 0禁用, 1正常

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Integer delFlag;    // 是否删除: 0未删除, 1已删除
}

