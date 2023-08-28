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
}
