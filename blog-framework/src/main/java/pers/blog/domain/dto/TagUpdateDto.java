package pers.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zyx
 * @create: 2023/9/5
 * @description: 更新标签DTO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagUpdateDto {
    private Long id;
    private String name;
    private String remark;
}
