package pers.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.blog.constans.SystemConstants;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.AddCommentDto;
import pers.blog.domain.entity.Comment;
import pers.blog.service.CommentService;

/**
 * @author: zyx
 * @create: 2023/8/30
 */
@Api(tags = "评论", description = "评论相关接口")
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
    @ApiOperation(value = "查看评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章id"),
            @ApiImplicitParam(name = "pageNum", value = "页数"),
            @ApiImplicitParam(name = "pageSize", value = "一次展示的页数")
    })
    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT, articleId, pageNum, pageSize);
    }

    /**
     * 查询友链评论列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "查询友链评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数"),
            @ApiImplicitParam(name = "pageSize", value = "一次展示的页数")
    })
    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.LINK_COMMENT, null, pageNum, pageSize);
    }

    /**
     * 发表评论
     * @param commentDto
     * @return
     */
    @ApiOperation(value = "发表评论")
    @PostMapping("/addComment")
    public ResponseResult addComment(@RequestBody AddCommentDto commentDto) {
        return commentService.addComment(commentDto);
    }
}
