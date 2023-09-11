package pers.blog.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pers.blog.domain.ResponseResult;

import pers.blog.domain.dto.AddCategoryDto;
import pers.blog.domain.dto.UpdateCategoryDto;
import pers.blog.domain.entity.Category;
import pers.blog.domain.vo.EasyExcelVo;
import pers.blog.enums.AppHttpCodeEnum;
import pers.blog.service.CategoryService;
import pers.blog.utils.BeanCopyUtils;
import pers.blog.utils.WebUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: zyx
 * @create: 2023/9/5
 */
@Api(tags = "分类", description = "分类相关接口")
@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所有分类
     * @return
     */
    @ApiOperation(value = "查询所有分类")
    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory() {
        return ResponseResult.okResult(categoryService.listAllCategory());
    }

    /**
     * 标签导出
     * @param response
     */
    @ApiOperation(value = "导出分类为xlsx")
    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        try {
            // 设置下载的请求头
            WebUtils.setDownLoadHeader("分类.xlsx", response);

            // 获取需要导出的数据
            List<Category> list = categoryService.list();

            List<EasyExcelVo> easyExcelVos = BeanCopyUtils.copyList(list, EasyExcelVo.class);

            // 将数据写入Excel中
            EasyExcel.write(response.getOutputStream(), EasyExcelVo.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("分类描述")
                    .doWrite(easyExcelVos);
        } catch (Exception e) {
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }

    /**
     * 分页查询分类列表
     * @param pageNum
     * @param pageSize
     * @param name
     * @param status
     * @return
     */
    @ApiOperation(value = "分页查询分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页数"),
            @ApiImplicitParam(name = "pageSize",value = "一页显示多少"),
            @ApiImplicitParam(name = "name",value = "可选, 模糊查询分类名"),
            @ApiImplicitParam(name = "status",value = "可选, 分类状态"),
    })
    @GetMapping("/list")
    public ResponseResult pageCategory(Integer pageNum, Integer pageSize, String name, String status) {
        return categoryService.pageCategory(pageNum, pageSize, name, status);
    }

    /**
     * 新增分类
     * @param categoryDto
     * @return
     */
    @ApiOperation(value = "新增分类")
    @PostMapping
    public ResponseResult addCategory(@RequestBody AddCategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    /**
     * 查询分类信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询分类")
    @GetMapping("/{id}")
    public ResponseResult getCategoryInfo(@PathVariable Long id) {
        return categoryService.getCategoryInfo(id);
    }

    /**
     * 更新分类
     * @param categoryDto
     * @return
     */
    @ApiOperation(value = "更新分类")
    @PutMapping
    public ResponseResult updateCategory(@RequestBody UpdateCategoryDto categoryDto) {
        return categoryService.updateCategory(categoryDto);
    }

    /**
     * 删除分类
     * @param ids
     * @return
     */
    @ApiOperation(value = "根据id删除分类(逻辑删除)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "要删除的id, 可能为多个, 类型为字符串格式")
    })
    @DeleteMapping("/{id}")
    public ResponseResult deleteCategory(@PathVariable("id") String ids) {
        return categoryService.deleteCategories(ids);
    }
}
