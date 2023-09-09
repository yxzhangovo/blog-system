package pers.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.AddLinkDto;
import pers.blog.service.LinkService;

@RestController
@RequestMapping("/content/link")
public class ListController {
    @Autowired
    private LinkService linkService;

    /**
     * 分页查询友链
     * @param pageNum
     * @param pageSize
     * @param name
     * @param status
     * @return
     */
    @GetMapping("/list")
    public ResponseResult pageLink(Integer pageNum, Integer pageSize, String name, String status) {
        return linkService.pageLink(pageNum, pageSize, name, status);
    }

    /**
     * 添加友链
     * @param linkDto
     * @return
     */
    @PostMapping
    public ResponseResult addLink(@RequestBody AddLinkDto linkDto) {
        return linkService.addLink(linkDto);
    }
}
