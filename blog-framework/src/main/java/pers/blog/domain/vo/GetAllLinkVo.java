package pers.blog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zyx
 * @create: 2023/8/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllLinkVo {
    private Long id;

    private String name;

    private String logo;

    private String description;

    private String address;
}
