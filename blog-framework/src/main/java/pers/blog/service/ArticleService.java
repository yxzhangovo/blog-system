package pers.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.AddArticleDto;
import pers.blog.domain.dto.ArticleListDto;
import pers.blog.domain.entity.Article;
import pers.blog.domain.vo.ArticleUpdateInfoVo;

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
    // 更新浏览量
    ResponseResult updateViewCount(Long id);
    // 写博文
    ResponseResult add(AddArticleDto articleDto);
    // 后台查询所有文章
    ResponseResult articleListBackend(Integer pageNum, Integer pageSize, String title, String summary);
    // 后台查询文章内容
    ResponseResult getArticleInfo(Long id);
    // 后台更新文章
    ResponseResult updateArticle(ArticleUpdateInfoVo articleUpdateInfoVo);
    // 后台删除文章
    ResponseResult deleteArticle(String ids);
}
