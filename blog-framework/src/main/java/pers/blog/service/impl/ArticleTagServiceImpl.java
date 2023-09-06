package pers.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.blog.domain.entity.ArticleTag;
import pers.blog.mapper.ArticleTagMapper;
import pers.blog.service.ArticleTagService;

/**
 * @author: zyx
 * @create: 2023/9/6
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}
