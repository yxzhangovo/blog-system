package pers.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.TagListDto;
import pers.blog.domain.dto.TagUpdateDto;
import pers.blog.service.TagService;

/**
 * @author: zyx
 * @create: 2023/9/2
 */
@Api(tags = "标签", description = "标签相关接口")
@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 查询标签列表
     * @return
     */
    @ApiOperation(value = "分页查询文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页数"),
            @ApiImplicitParam(name = "pageSize",value = "一页显示的内容"),
            @ApiImplicitParam(name = "tagListDto",value = "可选, 模糊查询标签名和备注"),
    })
    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, String name, String remark) {
        return tagService.pageTagList(pageNum, pageSize, name, remark);
    }

    /**
     * 添加标签
     * @param tagListDto
     * @return
     */
    @ApiOperation(value = "添加标签")
    @PostMapping
    public ResponseResult addTag(@RequestBody TagListDto tagListDto) {
        return tagService.saveTag(tagListDto);
    }

    /**
     * 删除标签
     * @param ids
     * @return
     */
    @ApiOperation(value = "根据id删除标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "要删除的id, 可能为多个, 类型为字符串格式")
    })
    @DeleteMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable("id") String ids) {
        return tagService.deleteTag(ids);
    }

    /**
     * 查询单个标签
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id获取标签信息")
    @GetMapping("/{id}")
    public ResponseResult findTag(@PathVariable Long id) {
        return tagService.findTag(id);
    }

    /**
     * 更新标签
     * @param tagUpdateDto
     * @return
     */
    @ApiOperation(value = "更新标签")
    @PutMapping
    public ResponseResult updateTag(@RequestBody TagUpdateDto tagUpdateDto) {
        return tagService.updateTag(tagUpdateDto);
    }

    /**
     * 查询所有标签
     * @return
     */
    @ApiOperation(value = "查询所有标签")
    @GetMapping("/listAllTag")
    public ResponseResult listAllTag() {
        return tagService.listAllTag();
    }
}
