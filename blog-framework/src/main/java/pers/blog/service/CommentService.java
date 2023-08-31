package pers.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.Comment;


/**
 * @author: zyx
 * @create: 2023/8/30
 */
public interface CommentService extends IService<Comment> {
    // 查询评论列表
    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);
    // 发表评论
    ResponseResult addComment(Comment comment);
}
