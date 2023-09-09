package pers.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.blog.constans.SystemConstants;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.dto.AddLinkDto;
import pers.blog.domain.entity.Link;
import pers.blog.domain.vo.GetAllLinkVo;
import pers.blog.domain.vo.PageLinkVo;
import pers.blog.domain.vo.PageVo;
import pers.blog.mapper.LinkMapper;
import pers.blog.service.LinkService;
import pers.blog.utils.BeanCopyUtils;

import java.util.List;

/**
 * @author: zyx
 * @create: 2023/8/28
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    /**
     * 友链查询
     * @return
     */
    @Override
    public ResponseResult getAllLink() {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> linkList = this.list(queryWrapper);

        List<GetAllLinkVo> getAllLinkVos = BeanCopyUtils.copyList(linkList, GetAllLinkVo.class);

        return ResponseResult.okResult(getAllLinkVos);
    }

    /**
     * 分页查询友链
     * @param pageNum
     * @param pageSize
     * @param name
     * @param status
     * @return
     */
    @Override
    public ResponseResult pageLink(Integer pageNum, Integer pageSize, String name, String status) {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(name), Link::getName, name);
        queryWrapper.eq(StringUtils.hasText(status), Link::getStatus, status);
        Page<Link> linkPage = new Page<>(pageNum, pageSize);
        this.page(linkPage, queryWrapper);

        List<PageLinkVo> pageLinkVos = BeanCopyUtils.copyList(linkPage.getRecords(), PageLinkVo.class);
        PageVo pageVo = new PageVo(pageLinkVos, linkPage.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    /**
     * 添加友链
     * @param linkDto
     * @return
     */
    @Override
    public ResponseResult addLink(AddLinkDto linkDto) {
        Link link = BeanCopyUtils.copyBean(linkDto, Link.class);
        this.save(link);

        return ResponseResult.okResult();
    }
}
