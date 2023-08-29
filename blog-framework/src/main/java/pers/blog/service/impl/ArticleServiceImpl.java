package pers.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.blog.constans.SystemConstants;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.Article;
import pers.blog.domain.entity.Category;
import pers.blog.domain.vo.ArticleDetailVo;
import pers.blog.domain.vo.ArticleListVo;
import pers.blog.domain.vo.HotArticleVo;
import pers.blog.domain.vo.PageVo;
import pers.blog.mapper.ArticleMapper;
import pers.blog.service.ArticleService;
import pers.blog.service.CategoryService;
import pers.blog.utils.BeanCopyUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: zyx
 * @create: 2023/8/28
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询热门文章
     */
    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_DRAFT); // 过滤已发布文章
        queryWrapper.orderByAsc(Article::getViewCount);

        Page<Article> articlePage = new Page<>(1, 10);
        page(articlePage, queryWrapper);

        List<Article> articleList = articlePage.getRecords();
        List<HotArticleVo> articleVos = BeanCopyUtils.copyList(articleList, HotArticleVo.class);

        return ResponseResult.okResult(articleVos);
    }

    /**
     * 分页查询文章
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getIsTop);

        Page<Article> pageInfo = new Page<>(pageNum, pageSize);
        Page<Article> articlePage = this.page(pageInfo, queryWrapper);

        List<Article> articleList = articlePage.getRecords();
        articleList.stream().map(article -> {
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                article.setCategoryName(category.getName());
            }
            return article;
        }).collect(Collectors.toList());
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyList(articleList, ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos, pageInfo.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    /**
     * 查询文章详情
     * @param id
     * @return
     */
    @Override
    public ResponseResult getArticleDetail(Long id) {
        Article article = this.getById(id);
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        Category category = categoryService.getById(articleDetailVo.getCategoryId());
        if (category != null) {
            articleDetailVo.setCategoryName(category.getName());
        }

        return ResponseResult.okResult(articleDetailVo);
    }
}
