package pers.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zyx
 * @create: 2023/9/6
 * @description: 后台文章列表DTo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListDto {
    private String title;
    private String summary;
}
