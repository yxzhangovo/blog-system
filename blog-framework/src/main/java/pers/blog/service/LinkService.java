package pers.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.Link;


/**
 * @author: zyx
 * @create: 2023/8/28
 */
public interface LinkService extends IService<Link> {
    // 友链查询
    ResponseResult getAllLink();
}
