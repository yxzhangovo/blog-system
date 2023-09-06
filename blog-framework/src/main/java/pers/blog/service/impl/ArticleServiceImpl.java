package pers.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pers.blog.constans.SystemConstants;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.AddArticleDto;
import pers.blog.domain.entity.Article;
import pers.blog.domain.entity.ArticleTag;
import pers.blog.domain.entity.Category;
import pers.blog.domain.vo.*;
import pers.blog.mapper.ArticleMapper;
import pers.blog.service.ArticleService;
import pers.blog.service.ArticleTagService;
import pers.blog.service.CategoryService;
import pers.blog.service.TagService;
import pers.blog.utils.BeanCopyUtils;
import pers.blog.utils.RedisCache;

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

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private TagService tagService;

    /**
     * 查询热门文章
     */
    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL); // 过滤已发布文章
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
            Category category = categoryService.getById(article.getCategoryId());
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

        // 从Redis中获取文章详情
        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", id.toString());
        article.setViewCount(viewCount.longValue());

        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        Category category = categoryService.getById(articleDetailVo.getCategoryId());
        if (category != null) {
            articleDetailVo.setCategoryName(category.getName());
        }

        return ResponseResult.okResult(articleDetailVo);
    }

    /**
     * 更新浏览量
     * @param id
     * @return
     */
    @Override
    public ResponseResult updateViewCount(Long id) {
        // 更新redis中对应id的浏览量
        redisCache.incrementCacheMapValue("article:viewCount", id.toString(), 1);
        return ResponseResult.okResult();
    }

    /**
     * 写博文
     * @param articleDto
     * @return
     */
    @Override
    @Transactional
    public ResponseResult add(AddArticleDto articleDto) {
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        this.save(article);

        List<Long> tags = articleDto.getTags();
        List<ArticleTag> articleTags = tags.stream()
                .map(tag -> new ArticleTag(article.getId(), tag))
                .collect(Collectors.toList());

        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }

    /**
     * 后台查询所有文章
     * @param pageNum
     * @param pageSize
     * @param title
     * @param summary
     * @return
     */
    @Override
    public ResponseResult articleListBackend(Integer pageNum, Integer pageSize, String title, String summary) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(title), Article::getTitle, title);
        queryWrapper.like(StringUtils.hasText(summary), Article::getSummary, summary);

        Page<Article> articlePage = new Page<>(pageNum, pageSize);
        this.page(articlePage, queryWrapper);

        List<Article> articleList = articlePage.getRecords();
        List<ArticleListBackendVo> articleListBackendVos = BeanCopyUtils.copyList(articleList, ArticleListBackendVo.class);
        PageVo pageVo = new PageVo(articleListBackendVos, articlePage.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    /**
     * 后台查询文章内容
     * @param id
     * @return
     */
    @Override
    public ResponseResult getArticleInfo(Long id) {
        // 查询Article
        Article article = this.getById(id);

        // 查询Article对应的TagId
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId, id);
        List<ArticleTag> articleTags = articleTagService.list(queryWrapper);

        List<Long> tags = articleTags.stream()
                .map(ArticleTag::getTagId)
                .collect(Collectors.toList());

        ArticleUpdateInfoVo vo = BeanCopyUtils.copyBean(article, ArticleUpdateInfoVo.class);
        vo.setTags(tags);

        return ResponseResult.okResult(vo);
    }

    /**
     * 更新文章
     * @param vo
     * @return
     */
    @Override
    @Transactional
    public ResponseResult updateArticle(ArticleUpdateInfoVo vo) {
        // 更新Article表
        Article article = BeanCopyUtils.copyBean(vo, Article.class);
        this.updateById(article);

        // 删除ArticleTags表数据
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId, vo.getId());
        articleTagService.remove(queryWrapper);

        // 添加ArticleTags表数据
        List<ArticleTag> articleTags = vo.getTags().stream()
                .map(tag -> new ArticleTag(vo.getId(), tag))
                .collect(Collectors.toList());

        articleTagService.saveBatch(articleTags);

        return ResponseResult.okResult();
    }
}
