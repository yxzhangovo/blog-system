package pers.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.blog.constans.SystemConstants;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.Article;
import pers.blog.domain.vo.HotArticleVo;
import pers.blog.mapper.ArticleMapper;
import pers.blog.service.ArticleService;
import pers.blog.utils.BeanCopyUtils;

import java.util.List;

/**
 * @author: zyx
 * @create: 2023/8/28
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
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
}
