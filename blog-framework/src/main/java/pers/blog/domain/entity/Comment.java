package pers.blog.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zyx
 * @create: 2023/8/28
 * @description: 评论
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("zyx_comment")
public class Comment {
    
    private Long id;

    private String type;    // 评论类型: 0文章评论, 1友链评论

    private Long articleId;

    private Long rootId;    // 根评论id

    private String content;

    private Long toCommentUserId;   // 所回复的目标评论的userid

    private Long toCommentId;   // 回复目标评论id

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Integer delFlag;    // 是否删除: 0未删除, 1已删除
}

