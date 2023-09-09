package pers.blog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageLinkVo {
    private Long id;

    private String name;

    private String description;

    private String address;

    private String status;  // 审核状态: 0未通过, 1已通过, 2待审核
}
