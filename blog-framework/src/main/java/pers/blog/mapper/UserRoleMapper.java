package pers.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.blog.domain.entity.UserRole;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
