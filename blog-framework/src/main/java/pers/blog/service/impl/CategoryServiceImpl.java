package pers.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.blog.constans.SystemConstants;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.AddCategoryDto;
import pers.blog.domain.dto.ListAllCategoryDto;
import pers.blog.domain.dto.UpdateCategoryDto;
import pers.blog.domain.entity.Article;
import pers.blog.domain.entity.Category;
import pers.blog.domain.vo.CategoryVo;
import pers.blog.domain.vo.GetCategoryInfo;
import pers.blog.domain.vo.PageCategoryVo;
import pers.blog.domain.vo.PageVo;
import pers.blog.mapper.CategoryMapper;
import pers.blog.service.ArticleService;
import pers.blog.service.CategoryService;
import pers.blog.utils.BeanCopyUtils;
import pers.blog.utils.ImplUtils;

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

    /**
     * 查询所有分类
     * @return
     */
    @Override
    public List<ListAllCategoryDto> listAllCategory() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getStatus, SystemConstants.STATUS_NORMAL);
        List<Category> list = this.list(queryWrapper);
        List<ListAllCategoryDto> listAllCategoryDtos = BeanCopyUtils.copyList(list, ListAllCategoryDto.class);
        return listAllCategoryDtos;
    }

    /**
     * 分页查询分类列表
     * @param pageNum
     * @param pageSize
     * @param name
     * @param status
     * @return
     */
    @Override
    public ResponseResult pageCategory(Integer pageNum, Integer pageSize, String name, String status) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(name), Category::getName, name);
        queryWrapper.eq(StringUtils.hasText(status), Category::getStatus, status);
        Page<Category> categoryPage = new Page<>(pageNum, pageSize);
        this.page(categoryPage, queryWrapper);

        List<PageCategoryVo> pageCategoryVos = BeanCopyUtils.copyList(categoryPage.getRecords(), PageCategoryVo.class);
        PageVo pageVo = new PageVo(pageCategoryVos, categoryPage.getTotal());

        return ResponseResult.okResult(pageVo);

    }

    /**
     * 新增分类
     * @param categoryDto
     * @return
     */
    @Override
    public ResponseResult addCategory(AddCategoryDto categoryDto) {
        Category category = BeanCopyUtils.copyBean(categoryDto, Category.class);
        this.save(category);
        return ResponseResult.okResult();
    }

    /**
     * 获取分类信息
     * @param id
     * @return
     */
    @Override
    public ResponseResult getCategoryInfo(Long id) {
        Category category = this.getById(id);
        GetCategoryInfo categoryInfo = BeanCopyUtils.copyBean(category, GetCategoryInfo.class);
        return ResponseResult.okResult(categoryInfo);
    }

    /**
     * 更新分类
     * @param categoryDto
     * @return
     */
    @Override
    public ResponseResult updateCategory(UpdateCategoryDto categoryDto) {
        Category category = BeanCopyUtils.copyBean(categoryDto, Category.class);
        this.updateById(category);
        return ResponseResult.okResult();
    }

    /**
     * 删除分类
     * @param ids
     * @return
     */
    @Override
    public ResponseResult deleteCategories(String ids) {
        Long[] removed = ImplUtils.removeByIds(ids);
        for (Long id : removed) {
            this.removeById(id);
        }

        return ResponseResult.okResult();
    }
}
