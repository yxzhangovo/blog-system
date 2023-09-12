package pers.blog.domain.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author: Zyx
 * @date: 9/12/2023
 * @description: 新增角色Dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRoleDto {
    private String roleName;

    private String roleKey; // 角色权限字符串

    private Integer roleSort;

    private String status;  // 角色状态: 0正常, 1停用

    private List<Long> menuIds;

    private String remark;

}
