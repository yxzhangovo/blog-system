package pers.blog.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: zyx
 * @create: 2023/8/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVo {
    private Long id;

    private String title;

    private String content;

    private String categoryName;

    private Long categoryId;

    private Long viewCount;

    private String isComment;   //是否允许评论: 0否, 1是

    private Date createTime;


}
