package pers.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.blog.domain.entity.Tag;

/**
 * @author: zyx
 * @create: 2023/9/2
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}
