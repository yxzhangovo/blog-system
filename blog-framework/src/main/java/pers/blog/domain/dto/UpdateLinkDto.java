package pers.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLinkDto {
    private Long id;

    private String name;

    private String logo;

    private String description;

    private String address;

    private String status;  // 审核状态: 0未通过, 1已通过, 2待审核
}
