package pers.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
    private Long id;
    private String userName;
    private String nickName;
    private String status;  // 账号状态: 0正常, 1停用
    private String email;
    private String sex; // 性别: 0男, 1女
    private List<Long> roleIds;
}
