package pers.blog.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zyx
 * @create: 2023/8/28
 * @description: 用户
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class User {

    private Long id;

    private String userName;

    private String nickName;

    private String password;

    private String type;    // 用户类型: 0游客, 1管理员

    private String status;  // 账号状态: 0正常, 1停用

    private String email;

    private String phonenumber;

    private String sex; // 性别: 0男, 1女

    private String avatar;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Integer delFlag;    // 是否删除: 0未删除, 1已删除
}

