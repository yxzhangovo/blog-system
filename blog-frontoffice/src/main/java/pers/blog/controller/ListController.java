package pers.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.blog.domain.ResponseResult;
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
    public ResponseResult pageList(Integer pageNum, Integer pageSize, String name, String status) {
        return linkService.pageLink(pageNum, pageSize, name, status);
    }
}
