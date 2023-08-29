package pers.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.blog.constans.SystemConstants;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.Link;
import pers.blog.domain.vo.GetAllLinkVo;
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
}
