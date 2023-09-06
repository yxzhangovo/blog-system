package pers.blog.domain.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zyx
 * @create: 2023/9/6
 * @description: 文章标签关联表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("zyx_article_tag")
public class ArticleTag {
    private Long articleId;
    private Long tagId;
}

