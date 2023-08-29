package pers.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.Article;

/**
 * @author: zyx
 * @create: 2023/8/28
 */
public interface ArticleService extends IService<Article> {
    // 查询热门文章
    ResponseResult hotArticleList();
    // 分页查询文章
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);
    // 查询文章内容
    ResponseResult getArticleDetail(Long id);
}
