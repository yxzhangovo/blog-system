package pers.blog.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Zyx
 * @date: 9/11/2023
 * @description: 登录接口DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "登录DTO")
public class LoginUserDto {
    @ApiModelProperty(notes = "用户名")
    private String username;
    @ApiModelProperty(notes = "密码")
    private String password;
}
