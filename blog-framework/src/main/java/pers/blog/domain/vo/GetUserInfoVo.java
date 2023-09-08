package pers.blog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.blog.domain.entity.Role;
import pers.blog.domain.entity.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserInfoVo {
    private List<Long> roleIds;
    private List<Role> roles;
    private User user;
}
