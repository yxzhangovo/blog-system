package pers.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.blog.domain.ResponseResult;
import pers.blog.service.CommentService;

/**
 * @author: zyx
 * @create: 2023/8/30
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 查询评论列表
     * @param articleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        return commentService.commentList(articleId, pageNum, pageSize);
    }
}
