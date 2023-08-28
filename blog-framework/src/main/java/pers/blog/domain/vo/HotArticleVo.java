package pers.blog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zyx
 * @create: 2023/8/28
 * @description: 响应Vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotArticleVo {
    private Long id;
    private String title;
    private Long viewCount;

}
