package pers.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.AddLinkDto;
import pers.blog.domain.dto.ChangeLinkStatusDto;
import pers.blog.domain.dto.UpdateLinkDto;
import pers.blog.service.LinkService;

@Api(tags = "友链", description = "友链相关接口")
@RestController
@RequestMapping("/content/link")
public class LinkController {
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
    @ApiOperation(value = "分页查询友链")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页数"),
            @ApiImplicitParam(name = "pageSize",value = "一页显示的内容"),
            @ApiImplicitParam(name = "title",value = "可选, 模糊查询友链名"),
            @ApiImplicitParam(name = "summary",value = "可选, 根据状态查询")
    })
    @GetMapping("/list")
    public ResponseResult pageLink(Integer pageNum, Integer pageSize, String name, String status) {
        return linkService.pageLink(pageNum, pageSize, name, status);
    }

    /**
     * 添加友链
     * @param linkDto
     * @return
     */
    @ApiOperation(value = "添加友链")
    @PostMapping
    public ResponseResult addLink(@RequestBody AddLinkDto linkDto) {
        return linkService.addLink(linkDto);
    }

    /**
     * 获取友链信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id获取友链信息")
    @GetMapping("/{id}")
    public ResponseResult getLinkInfo(@PathVariable("id") Long id) {
        return linkService.getLinkInfo(id);
    }

    /**
     * 更新友链
     * @param linkDto
     * @return
     */
    @ApiOperation(value = "更新友链")
    @PutMapping
    public ResponseResult updateLink(@RequestBody UpdateLinkDto linkDto) {
        return linkService.updateLink(linkDto);
    }

    /**
     * 删除友链
     * @param ids
     * @return
     */
    @ApiOperation(value = "根据id删除友链(逻辑删除)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "要删除的id, 可能为多个, 类型为字符串格式")
    })
    @DeleteMapping("{id}")
    public ResponseResult deleteLink(@PathVariable("id") String ids) {
        return linkService.deleteLink(ids);
    }

    @ApiOperation(value = "决定友链状态")
    @PutMapping("/changeLinkStatus")
    public ResponseResult changeLinkStatus(@RequestBody ChangeLinkStatusDto linkStatusDto){
        return linkService.changeLinkStatus(linkStatusDto);
    }
}
