package com.example.demo.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.admin.model.Menu;
import com.example.demo.admin.model.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务类
 * </p>
 *
 * @author sunjialei
 * @since 2020-10-22
 */
public interface RoleService extends IService<Role> {

    Page<Role> list(Integer pageNum, Integer pageSize);

    @Transactional
    boolean allocMenu(Long roleId, List<Long> menuIds);

    List<Menu> menuList(Long roleId);

    List<Menu> getMenuListByAdminId(Long adminId);
}
