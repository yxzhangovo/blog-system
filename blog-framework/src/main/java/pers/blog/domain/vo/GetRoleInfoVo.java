package pers.blog.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: Zyx
 * @date: 9/12/2023
 * @description: 根据id获取角色信息Vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRoleInfoVo {
    private Long id;

    private String roleName;

    private String roleKey; // 角色权限字符串

    private Integer roleSort;

    private String status;  // 角色状态: 0正常, 1停用

    private String remark;
}
