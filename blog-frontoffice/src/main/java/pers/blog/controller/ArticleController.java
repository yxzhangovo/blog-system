package pers.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.AddArticleDto;
import pers.blog.domain.vo.ArticleUpdateInfoVo;
import pers.blog.service.ArticleService;

/**
 * @author: zyx
 * @create: 2023/9/6
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 添加博文
     * @param articleDto
     * @return
     */
    @PostMapping
    public ResponseResult addArticle(@RequestBody AddArticleDto articleDto) {
        return articleService.add(articleDto);
    }

    /**
     * 后台查询所有文章
     * @param pageNum
     * @param pageSize
     * @param title
     * @param summary
     * @return
     */
    @GetMapping("/list")
    public ResponseResult listArticle(Integer pageNum, Integer pageSize, String title, String summary) {
        return articleService.articleListBackend(pageNum, pageSize, title, summary);
    }

    /**
     * 后台查询文章内容
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getArticleInfo(@PathVariable Long id) {
        return articleService.getArticleInfo(id);
    }

    /**
     * 更新文章
     * @param articleUpdateInfoVo
     * @return
     */
    @PutMapping
    public ResponseResult updateArticle(@RequestBody ArticleUpdateInfoVo articleUpdateInfoVo) {
        return articleService.updateArticle(articleUpdateInfoVo);
    }
}
