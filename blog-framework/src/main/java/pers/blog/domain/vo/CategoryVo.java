package pers.blog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zyx
 * @create: 2023/8/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo {
    private Long id;
    private String name;
}
