package com.example.demo.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.admin.mapper.MenuMapper;
import com.example.demo.admin.model.Menu;
import com.example.demo.admin.model.Role;
import com.example.demo.admin.mapper.RoleMapper;
import com.example.demo.admin.model.RoleMenuRelation;
import com.example.demo.admin.service.RoleMenuRelationService;
import com.example.demo.admin.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private RoleMenuRelationService roleMenuRelationService;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Page<Role> list(Integer pageNum, Integer pageSize) {
        Page<Role> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByAsc(Role::getSort);
        return page(page, queryWrapper);
    }


    @Override
    public boolean allocMenu(Long roleId, List<Long> menuIds) {
        // 先删除旧数据
        QueryWrapper<RoleMenuRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleMenuRelation::getRoleId, roleId);
        roleMenuRelationService.remove(queryWrapper);

        // 批量插入新关系
        List<RoleMenuRelation> list = new ArrayList<>();
        for (Long menuId: menuIds) {
            RoleMenuRelation roleMenuRelation = new RoleMenuRelation();
            roleMenuRelation.setRoleId(roleId);
            roleMenuRelation.setMenuId(menuId);

            list.add(roleMenuRelation);
        }
        return roleMenuRelationService.saveBatch(list);
    }


    @Override
    public List<Menu> menuList(Long roleId) {
        return menuMapper.getMenuListByRoleId(roleId);
    }

    @Override
    public List<Menu> getMenuListByAdminId(Long adminId) {
        return menuMapper.getMenuListByAdminId(adminId);
    }
}
