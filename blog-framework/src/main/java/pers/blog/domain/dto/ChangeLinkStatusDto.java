package pers.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Zyx
 * @date: 9/11/2023
 * @description: 更新友链状态
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeLinkStatusDto {
    private Long id;
    private String status;
}
