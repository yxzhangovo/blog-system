package pers.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.blog.domain.entity.Role;

import java.util.List;


/**
 * @author: zyx
 * @create: 2023/9/4
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    // 根据id查询角色信息(非管理员)
    List<String> selectRoleKeyByUserId(Long id);
}
