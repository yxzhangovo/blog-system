package pers.blog.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zyx
 * @create: 2023/8/28
 * @description: 友链
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("zyx_link")
public class Link {
    private Long id;
    
    private String name;
    
    private String logo;
    
    private String description;

    private String address;

    private String status;  // 审核状态: 0未通过, 1已通过, 2待审核
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;

    private Integer delFlag;    // 是否删除: 0未删除, 1已删除
}

