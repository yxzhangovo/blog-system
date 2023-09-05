package pers.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.TagListDto;
import pers.blog.domain.dto.TagUpdateDto;
import pers.blog.domain.entity.Tag;


/**
 * @author: zyx
 * @create: 2023/9/2
 */
public interface TagService extends IService<Tag> {
    // 查询标签列表
    ResponseResult pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);
    // 新增标签
    ResponseResult saveTag(TagListDto tagListDto);
    // 删除标签
    ResponseResult deleteTag(String ids);
    // 查询单个标签
    ResponseResult findTag(Long id);
    // 更新标签
    ResponseResult updateTag(TagUpdateDto tagUpdateDto);
}
