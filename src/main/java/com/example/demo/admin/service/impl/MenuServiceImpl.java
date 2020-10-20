package com.example.demo.admin.service.impl;

import com.example.demo.admin.model.Menu;
import com.example.demo.admin.mapper.MenuMapper;
import com.example.demo.admin.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    public void updateLevel(Menu menu) {
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
}
