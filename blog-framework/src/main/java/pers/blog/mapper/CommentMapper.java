package pers.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.blog.domain.entity.Comment;


/**
 * @author: zyx
 * @create: 2023/8/30
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
