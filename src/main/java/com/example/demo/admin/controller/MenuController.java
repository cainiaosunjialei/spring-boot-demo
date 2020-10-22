package com.example.demo.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.admin.dto.MenuNode;
import com.example.demo.admin.model.Menu;
import com.example.demo.admin.service.MenuService;
import com.example.demo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 创建菜单
     * @param menu
     * @return
     */
    @PostMapping("/create")
    public Result create(@RequestBody Menu menu) {
        boolean success = menuService.create(menu);
        if (success) {
            return Result.success();
        }
        return Result.failure();
    }


    /**
     * 分页列表
     * @param parentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list/{parentId}")
    public Result<Page<Menu>> list(@PathVariable Long parentId,
                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<Menu> list = menuService.list(parentId, pageNum, pageSize);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<Menu> getItem(@PathVariable Long id) {
        Menu menu = menuService.getById(id);
        return Result.success(menu);
    }


    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = menuService.removeById(id);
        if (success) {
            return Result.success();
        }
        return Result.failure();
    }


    @PostMapping("/update/{id}")
    public Result update(@PathVariable Long id, @RequestBody Menu menu) {
        boolean success = menuService.update(id, menu);
        if (success) {
            return Result.success();
        }
        return Result.failure();
    }


    @GetMapping("/treeList")
    public Result<List<MenuNode>> treeList() {
        List<MenuNode> list = menuService.treeList();
        return Result.success(list);
//        return Result.success(list);
    }




}

