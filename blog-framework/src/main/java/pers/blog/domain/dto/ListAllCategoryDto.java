package pers.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zyx
 * @create: 2023/9/5
 * @description: 查询所有分类DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListAllCategoryDto {
    private Long id;
    private String name;
    private String description;
}
