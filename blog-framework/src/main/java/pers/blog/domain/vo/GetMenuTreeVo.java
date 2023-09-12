package pers.blog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Zyx
 * @date: 9/12/2023
 * @description: 根据角色id获取权限树Vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMenuTreeVo {
    private List<MenuTreeVo> menus;
    private List<Long> checkedKeys;
}
