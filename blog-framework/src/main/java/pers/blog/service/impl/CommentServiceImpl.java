package pers.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.Comment;
import pers.blog.domain.vo.CommentVo;
import pers.blog.domain.vo.PageVo;
import pers.blog.mapper.CommentMapper;
import pers.blog.service.CommentService;
import pers.blog.service.UserService;
import pers.blog.utils.BeanCopyUtils;

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

        // 分页查询
        Page<Comment> commentPage = new Page<>(pageNum, pageSize);
        this.page(commentPage, queryWrapper);

        // 封装
        List<CommentVo> commentVoList = toCommentVoList(commentPage.getRecords());

        return ResponseResult.okResult(new PageVo(commentVoList, commentPage.getTotal()));
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
}
