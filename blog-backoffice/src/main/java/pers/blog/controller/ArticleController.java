package pers.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.blog.domain.ResponseResult;
import pers.blog.service.ArticleService;

/**
 * @author: zyx
 * @create: 2023/8/28
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 查询热门文章
     */
    @GetMapping("/hotArticleList")
    public ResponseResult list() {
        return articleService.hotArticleList();
    }

    /**
     * 分页查询文章
     * @return
     */
    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    /**
     * 查询文章内容
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }

}
