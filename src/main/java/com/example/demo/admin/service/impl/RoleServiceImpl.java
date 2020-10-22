package com.example.demo.admin.service.impl;

import com.example.demo.admin.model.Role;
import com.example.demo.admin.mapper.RoleMapper;
import com.example.demo.admin.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author sunjialei
 * @since 2020-10-22
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
