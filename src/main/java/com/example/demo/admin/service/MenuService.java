package com.example.demo.admin.service;

import com.example.demo.admin.model.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
