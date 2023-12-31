package pers.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.TagListDto;
import pers.blog.domain.dto.TagUpdateDto;
import pers.blog.domain.entity.Tag;
import pers.blog.domain.vo.PageVo;
import pers.blog.mapper.TagMapper;
import pers.blog.service.TagService;
import pers.blog.utils.BeanCopyUtils;
import pers.blog.utils.ImplUtils;

import java.util.List;

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
     * @param name
     * @param remark
     * @return
     */
    @Override
    public ResponseResult pageTagList(Integer pageNum, Integer pageSize, String name, String remark) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(name), Tag::getName, name);
        queryWrapper.like(Strings.isNotEmpty(remark), Tag::getRemark, remark);

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

    /**
     * 删除标签
     * @param ids
     * @return
     */
    @Override
    public ResponseResult deleteTag(String ids) {
        Long[] longs = ImplUtils.removeByIds(ids);

        for (Long id : longs) {
            this.removeById(id);
        }
        return ResponseResult.okResult();
    }

    /**
     * 查询单个标签
     * @param id
     * @return
     */
    @Override
    public ResponseResult findTag(Long id) {
        return ResponseResult.okResult(this.getById(id));
    }

    /**
     * 更新标签
     * @param tagUpdateDto
     * @return
     */
    @Override
    public ResponseResult updateTag(TagUpdateDto tagUpdateDto) {
        Tag tag = BeanCopyUtils.copyBean(tagUpdateDto, Tag.class);
        updateById(tag);
        return ResponseResult.okResult();
    }

    /**
     * 查询所有标签
     * @return
     */
    @Override
    public ResponseResult listAllTag() {
        List<Tag> list = this.list();
        List<TagUpdateDto> tagUpdateDtos = BeanCopyUtils.copyList(list, TagUpdateDto.class);
        return ResponseResult.okResult(tagUpdateDtos);
    }


}
