package pers.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.TagListDto;
import pers.blog.domain.entity.Tag;
import pers.blog.domain.vo.PageVo;
import pers.blog.mapper.TagMapper;
import pers.blog.service.TagService;
import pers.blog.utils.BeanCopyUtils;

/**
 * @author: zyx
 * @create: 2023/9/2
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    /**
     * 查询标签列表
     * @param pageNum
     * @param pageSize
     * @param tagListDto
     * @return
     */
    @Override
    public ResponseResult pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        String name = tagListDto.getName();
        String remark = tagListDto.getRemark();
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Strings.isNotEmpty(name), Tag::getName, name);
        queryWrapper.eq(Strings.isNotEmpty(remark), Tag::getRemark, remark);

        Page<Tag> tagPage = new Page<>(pageNum, pageSize);
        this.page(tagPage,queryWrapper);

        PageVo pageVo = new PageVo(tagPage.getRecords(), tagPage.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    /**
     * 新增标签
     * @param tagListDto
     * @return
     */
    @Override
    public ResponseResult saveTag(TagListDto tagListDto) {
        Tag tag = BeanCopyUtils.copyBean(tagListDto, Tag.class);
        this.save(tag);
        return ResponseResult.okResult();
    }
}
