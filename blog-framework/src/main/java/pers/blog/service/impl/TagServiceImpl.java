package pers.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.blog.domain.entity.Tag;
import pers.blog.mapper.TagMapper;
import pers.blog.service.TagService;

/**
 * @author: zyx
 * @create: 2023/9/2
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
