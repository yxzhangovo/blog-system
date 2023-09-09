package pers.blog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageCategoryVo {
    private Long id;

    private String name;

    private String description;

    private String status;  // 状态: 0禁用, 1正常

}
