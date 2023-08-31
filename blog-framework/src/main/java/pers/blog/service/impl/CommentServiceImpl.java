package pers.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.Comment;
import pers.blog.domain.vo.CommentVo;
import pers.blog.domain.vo.PageVo;
import pers.blog.enums.AppHttpCodeEnum;
import pers.blog.exception.SystemException;
import pers.blog.mapper.CommentMapper;
import pers.blog.service.CommentService;
import pers.blog.service.UserService;
import pers.blog.utils.BeanCopyUtils;
import pers.blog.utils.SecurityUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zyx
 * @create: 2023/8/28
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private UserService userService;

    /**
     * 查询评论列表
     * @param articleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        // 查询对应文章的根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(articleId != null, Comment::getArticleId, articleId);
        queryWrapper.eq(Comment::getRootId, -1);
        queryWrapper.orderByAsc(Comment::getUpdateTime);

        // 分页查询
        Page<Comment> commentPage = new Page<>(pageNum, pageSize);
        this.page(commentPage, queryWrapper);

        // 封装
        List<CommentVo> commentVoList = toCommentVoList(commentPage.getRecords());

        // 查询所有根评论对应的子评论
        commentVoList.stream().map(commentVo -> {
            List<CommentVo> childrenCommits = getChildrenCommit(commentVo.getId());
            commentVo.setChildren(childrenCommits);
            return commentVo;
        }).collect(Collectors.toList());

        return ResponseResult.okResult(new PageVo(commentVoList, commentPage.getTotal()));
    }

    /**
     * 根据根评论id查询其所有子评论
     * @param id
     * @return
     */
    private List<CommentVo> getChildrenCommit(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);
        queryWrapper.orderByAsc(Comment::getUpdateTime);
        List<Comment> commentList = this.list(queryWrapper);

        return toCommentVoList(commentList);
    }

    /**
     * 将CommentList封装为Vo
     * @param commentList
     * @return
     */
    private List<CommentVo> toCommentVoList(List<Comment> commentList) {
        List<CommentVo> commentVos = BeanCopyUtils.copyList(commentList, CommentVo.class);
        // 手动添加toCommentUsername和username属性
        commentVos.stream().map(commentVo -> {
            commentVo.setUsername(userService.getById(commentVo.getCreateBy()).getNickName());
            if (commentVo.getToCommentUserId() != -1) {
                commentVo.setToCommentUserName(userService.getById(commentVo.getToCommentUserId()).getNickName());
            }
            return commentVo;
        }).collect(Collectors.toList());

        return commentVos;
    }

    /**
     * 发表评论
     * @param comment
     * @return
     */
    @Override
    public ResponseResult addComment(Comment comment) {
        if (!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        this.save(comment);
        return ResponseResult.okResult();
    }
}
