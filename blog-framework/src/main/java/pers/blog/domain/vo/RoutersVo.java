package pers.blog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: zyx
 * @create: 2023/9/4
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutersVo {
    private List<MenuVo> menus;
}
