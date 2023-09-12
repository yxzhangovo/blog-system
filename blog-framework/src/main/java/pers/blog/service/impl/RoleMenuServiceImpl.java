package pers.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.blog.domain.entity.RoleMenu;
import pers.blog.mapper.RoleMenuMapper;
import pers.blog.service.RoleMenuService;

/**
 * @author: Zyx
 * @date: 9/12/2023
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
}
