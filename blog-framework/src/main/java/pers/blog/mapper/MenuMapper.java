package pers.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.blog.domain.entity.Menu;

import java.util.List;


/**
 * @author: zyx
 * @create: 2023/9/4
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    // 根据id查询权限信息(非管理员)
    List<String> selectPermsByUserId(Long id);
}
