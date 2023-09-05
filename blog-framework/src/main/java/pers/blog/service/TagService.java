package pers.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.TagListDto;
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
}
