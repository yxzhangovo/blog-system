package pers.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.blog.domain.entity.Article;

/**
 * @author: zyx
 * @create: 2023/8/28
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
