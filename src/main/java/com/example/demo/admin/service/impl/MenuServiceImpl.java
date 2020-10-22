package com.example.demo.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.admin.dto.MenuNode;
import com.example.demo.admin.model.Menu;
import com.example.demo.admin.mapper.MenuMapper;
import com.example.demo.admin.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台菜单表 服务实现类
 * </p>
 *
 * @author sunjialei
 * @since 2020-10-20
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public boolean create(Menu menu) {
        menu.setCreateTime(new Date());
        updateLevel(menu);
        return save(menu);
    }

    private void updateLevel(Menu menu) {
        if (menu.getParentId() == 0) {
            // 没有父菜单为一级菜单
            menu.setLevel(0);
        } else {
            // 有父菜单根据父菜单level+1
            Long parentId = menu.getParentId();
            Menu parentMenu = getById(parentId);
            menu.setLevel(parentMenu.getLevel() + 1);
        }
    }


    @Override
    public Page<Menu> list(Long parentId, Integer pageNum, Integer pageSize) {
        Page<Menu> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Menu::getParentId, parentId)
                .orderByDesc(Menu::getSort);
        return page(page, queryWrapper);
    }


    @Override
    public boolean update(Long id, Menu menu) {
        menu.setId(id);
        updateLevel(menu);
        return updateById(menu);
    }


    @Override
    public List<MenuNode> treeList() {
        List<Menu> menuList = list();
        List<MenuNode> nodes= convertMenuNode(0, menuList);
        return nodes;
    }

    private MenuNode convertMenuNode(int pid, List<Menu> menuList) {

        List<MenuNode> menuNodes = new ArrayList<>();

        for (Menu menu: menus) {
            MenuNode node = new MenuNode();
            BeanUtils.copyProperties(menu, node);

            List<Menu> list = menuList.stream()
                    .filter(ele -> ele.getParentId().equals(menu.getId()))
                    .collect(Collectors.toList());

            menuNodes.add(node);
            node.setChildren(convertMenuNode(list, menuList));
        }

        return menuNodes;
    }
}
