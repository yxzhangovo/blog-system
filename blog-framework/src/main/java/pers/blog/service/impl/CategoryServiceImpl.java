package pers.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.blog.constans.SystemConstants;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.Article;
import pers.blog.domain.entity.Category;
import pers.blog.domain.vo.CategoryVo;
import pers.blog.mapper.CategoryMapper;
import pers.blog.service.ArticleService;
import pers.blog.service.CategoryService;
import pers.blog.utils.BeanCopyUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: zyx
 * @create: 2023/8/28
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private ArticleService articleService;

    /**
     * 查询分类列表
     * @return
     */
    @Override
    public ResponseResult getCategoryList() {
        // 查询已发布文章
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(queryWrapper);

        // 获取文章分类id, 去重
        Set<Long> categoryIds = articleList.stream().map(Article::getCategoryId)
                .collect(Collectors.toSet());

        // 查询分类表
        List<Category> categories = this.listByIds(categoryIds).stream()
                .filter(category -> Objects.equals(category.getStatus(), SystemConstants.STATUS_NORMAL))
                .collect(Collectors.toList());

        // 封装
        List<CategoryVo> categoryVos = BeanCopyUtils.copyList(categories, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }
}
