package pers.blog.domain.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: Zyx
 * @date: 9/11/2023
 * @description: 添加评论DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentDto {
    private String type;    // 评论类型: 0文章评论, 1友链评论
    private Long articleId;
    private Long rootId;    // 根评论id
    private String content;
    private Long toCommentUserId;   // 所回复的目标评论的userid
    private Long toCommentId;   // 回复目标评论id

}
