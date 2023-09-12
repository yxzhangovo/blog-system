package pers.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.blog.domain.ResponseResult;
import pers.blog.service.ArticleService;

/**
 * @author: zyx
 * @create: 2023/8/28
 */
@Api(tags = "文章", description = "文章相关接口")
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 查询热门文章
     * @return
     */
    @ApiOperation(value = "查询热门博文")
    @GetMapping("/hotArticleList")
    public ResponseResult list() {
        return articleService.hotArticleList();
    }

    /**
     * 分页查询文章
     * @return
     */
    @ApiOperation(value = "分页查询文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页数"),
            @ApiImplicitParam(name = "pageSize",value = "一页显示的内容"),
            @ApiImplicitParam(name = "categoryId",value = "大于0的categoryId"),
    })
    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    /**
     * 查询文章内容
     * @param id
     * @return
     */
    @ApiOperation(value = "查询文章详情")
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }

    /**
     * 更新浏览量
     * @param id
     * @return
     */
    @ApiOperation(value = "更新浏览量(缓存到Redis中)")
    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id) {
        return articleService.updateViewCount(id);
    }

}
