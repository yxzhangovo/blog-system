package pers.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "文章", description = "后台文章相关接口")
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
    @ApiOperation(value = "添加博文")
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
    @ApiOperation(value = "分页查询文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页数"),
            @ApiImplicitParam(name = "pageSize",value = "一页显示的内容"),
            @ApiImplicitParam(name = "title",value = "可选, 模糊查询文章标题"),
            @ApiImplicitParam(name = "summary",value = "可选, 模糊查询文章摘要")
    })
    @GetMapping("/list")
    public ResponseResult listArticle(Integer pageNum, Integer pageSize, String title, String summary) {
        return articleService.articleListBackend(pageNum, pageSize, title, summary);
    }

    /**
     * 后台查询文章内容
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id获取文章信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "根据id获取对应文章的信息")
    })
    @GetMapping("/{id}")
    public ResponseResult getArticleInfo(@PathVariable Long id) {
        return articleService.getArticleInfo(id);
    }

    /**
     * 更新文章
     * @param articleUpdateInfoVo
     * @return
     */
    @ApiOperation(value = "更新文章")
    @PutMapping
    public ResponseResult updateArticle(@RequestBody ArticleUpdateInfoVo articleUpdateInfoVo) {
        return articleService.updateArticle(articleUpdateInfoVo);
    }

    /**
     * 后台删除文章
     * @param ids
     * @return
     */
    @ApiOperation(value = "根据id删除文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "要删除的id, 可能为多个, 类型为字符串格式")
    })
    @DeleteMapping("/{id}")
    public ResponseResult deleteArticle(@PathVariable("id") String ids) {
        return articleService.deleteArticle(ids);
    }
}
