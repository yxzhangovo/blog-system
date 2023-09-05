package pers.blog.controller;

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
@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 查询标签列表
     * @return
     */
    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        return tagService.pageTagList(pageNum, pageSize, tagListDto);
    }

    /**
     * 添加标签
     * @param tagListDto
     * @return
     */
    @PostMapping
    public ResponseResult addTag(@RequestBody TagListDto tagListDto) {
        return tagService.saveTag(tagListDto);
    }

    /**
     * 删除标签
     * @param ids
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable("id") String ids) {
        return tagService.deleteTag(ids);
    }

    /**
     * 查询单个标签
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult findTag(@PathVariable Long id) {
        return tagService.findTag(id);
    }

    /**
     * 更新标签
     * @param tagUpdateDto
     * @return
     */
    @PutMapping
    public ResponseResult updateTag(@RequestBody TagUpdateDto tagUpdateDto) {
        return tagService.updateTag(tagUpdateDto);
    }
}
