package pers.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.AddLinkDto;
import pers.blog.domain.dto.UpdateLinkDto;
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

    /**
     * 获取友链信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getLinkInfo(@PathVariable("id") Long id) {
        return linkService.getLinkInfo(id);
    }

    /**
     * 更新友链
     * @param linkDto
     * @return
     */
    @PutMapping
    public ResponseResult updateLink(@RequestBody UpdateLinkDto linkDto) {
        return linkService.updateLink(linkDto);
    }

}
