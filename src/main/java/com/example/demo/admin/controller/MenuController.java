package com.example.demo.admin.controller;


import com.example.demo.admin.model.Menu;
import com.example.demo.admin.service.MenuService;
import com.example.demo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 后台菜单表 前端控制器
 * </p>
 *
 * @author sunjialei
 * @since 2020-10-20
 */
@RestController
@RequestMapping("/admin/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/create")
    public Result create(@RequestBody Menu menu) {
        boolean success = menuService.create(menu);
        if (success) {
            return Result.success();
        }
        return Result.failure();
    }


}

