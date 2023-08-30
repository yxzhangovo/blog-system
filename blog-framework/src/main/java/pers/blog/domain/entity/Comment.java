package pers.blog.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zyx
 * @create: 2023/8/28
 * @description: 评论表
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
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;

    private Integer delFlag;    // 是否删除: 0未删除, 1已删除
}

