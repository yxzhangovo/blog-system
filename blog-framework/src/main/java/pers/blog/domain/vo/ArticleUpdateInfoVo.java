package pers.blog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author: zyx
 * @create: 2023/9/6
 * @description: 后台更新文章, 获取文章详情Vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleUpdateInfoVo {
    private Long id;

    private String title;

    private String content;

    private String summary;

    private Long categoryId;

    private String thumbnail;

    private String isTop;   // 是否置顶: 0不置顶, 1置顶

    private String status;  // 是否发布: 0草稿, 1已发布

    private Long viewCount;

    private String isComment;   //是否允许评论: 0否, 1是

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Integer delFlag;    // 是否删除: 0未删除, 1已删除

    private List<Long> tags;
}