package pers.blog.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author: zyx
 * @create: 2023/8/28
 * @description: 文章
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("zyx_article")
public class Article {
    private Long id;

    private String title;

    private String content;

    private String summary;

    @TableField(exist = false)
    private String categoryName;

    private Long categoryId;

    private String thumbnail;

    private String isTop;   // 是否置顶: 0不置顶, 1置顶

    private String status;  // 是否发布: 0已发布, 1草稿

    private Long viewCount;

    private String isComment;   //是否允许评论: 0否, 1是

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Integer delFlag;    // 是否删除: 0未删除, 1已删除

    public Article(Long id, Long viewCount) {
        this.id = id;
        this.viewCount = viewCount;
    }
}

