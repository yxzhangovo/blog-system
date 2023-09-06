package pers.blog.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zyx
 * @create: 2023/9/6
 * @description: EasyExcelVo导出类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EasyExcelVo {
    @ExcelProperty("分类名")
    private String name;

    @ExcelProperty("描述")
    private String description;

    @ExcelProperty("状态: 0正常 1禁用")
    private String status;
}
