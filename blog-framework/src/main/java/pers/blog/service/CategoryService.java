package pers.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.blog.domain.ResponseResult;
import pers.blog.domain.entity.Category;


/**
 * @author: zyx
 * @create: 2023/8/28
 */
public interface CategoryService extends IService<Category> {
    // 查询分类列表
    ResponseResult getCategoryList();
    // 查询所有分类接口
    ResponseResult listAllCategory();

}
