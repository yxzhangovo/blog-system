package pers.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Zyx
 * @date: 9/11/2023
 * @description: 更新用户状态DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeUserStatusDto {
    private Long userId;
    private String status;
}
