package pers.blog.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: zyx
 * @create: 2023/9/6
 * @description: 后台文章列表Vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListBackendVo {
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

    private Date createTime;
}
