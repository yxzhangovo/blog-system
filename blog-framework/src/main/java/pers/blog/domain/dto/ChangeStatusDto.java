package pers.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zyx
 * @create: 2023/9/8
 * @description: 修改角色状态DTo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeStatusDto {
    private Long roleId;
    private String status;
}
