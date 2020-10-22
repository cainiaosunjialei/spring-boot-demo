package com.example.demo.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.admin.dto.MenuNode;
import com.example.demo.admin.model.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台菜单表 服务类
 * </p>
 *
 * @author sunjialei
 * @since 2020-10-20
 */
public interface MenuService extends IService<Menu> {

    boolean create(Menu menu);

    Page<Menu> list(Long parentId, Integer pageNum, Integer pageSize);

    boolean update(Long id, Menu menu);

    List<MenuNode> treeList();
}
