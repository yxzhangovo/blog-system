package pers.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: zyx
 * @create: 2023/9/6
 * @description: 添加博文Dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddArticleDto {
    private Long id;

    private String title;

    private String content;

    private String summary;

    private Long categoryId;

    private String thumbnail;

    private String isTop;   // 是否置顶: 0不置顶, 1置顶

    private String status;  // 是否发布: 0草稿, 1已发布

    private String isComment;   //是否允许评论: 0否, 1是

    private List<Long> tags;
}
