package pers.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.AddLinkDto;
import pers.blog.domain.dto.UpdateLinkDto;
import pers.blog.domain.entity.Link;


/**
 * @author: zyx
 * @create: 2023/8/28
 */
public interface LinkService extends IService<Link> {
    // 友链查询
    ResponseResult getAllLink();
    // 分页查询友链
    ResponseResult pageLink(Integer pageNum, Integer pageSize, String name, String status);
    // 添加友链
    ResponseResult addLink(AddLinkDto linkDto);
    // 获取友链信息
    ResponseResult getLinkInfo(Long id);
    // 更新友链
    ResponseResult updateLink(UpdateLinkDto linkDto);
    // 删除友链
    ResponseResult deleteLink(String ids);
}
