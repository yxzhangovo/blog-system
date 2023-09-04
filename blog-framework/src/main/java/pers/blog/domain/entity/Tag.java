package pers.blog.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zyx
 * @create: 2023/9/2
 * @description: 标签
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("zyx_tag")
public class Tag {
    
    private Long id;

    private String name;
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;

    private Integer delFlag;    // 是否删除: 0未删除, 1已删除

    private String remark;
}

