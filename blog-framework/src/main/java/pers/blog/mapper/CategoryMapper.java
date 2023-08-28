package pers.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.blog.domain.entity.Category;

/**
 * @author: zyx
 * @create: 2023/8/28
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
