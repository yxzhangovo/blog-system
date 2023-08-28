package pers.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/hotarticleList")
    public ResponseResult list() {
        return articleService.hotArticleList();
    }

}
