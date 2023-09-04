package pers.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.blog.domain.ResponseResult;
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
     * 查询所有标签
     * @return
     */
    @GetMapping("/list")
    public ResponseResult list() {
        return ResponseResult.okResult(tagService.list());
    }
}
