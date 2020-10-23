package com.example.demo.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.admin.dto.AllocMenuParam;
import com.example.demo.admin.model.Menu;
import com.example.demo.admin.model.Role;
import com.example.demo.admin.service.RoleService;
import com.example.demo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 前端控制器
 * </p>
 *
 * @author sunjialei
 * @since 2020-10-22
 */
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired

    private RoleService roleService;

    @PostMapping("/create")
    public Result create(@RequestBody Role role) {
        boolean success = roleService.save(role);
        if (success) {
            return Result.success();
        }
        return Result.failure();
    }

    @GetMapping("/list")
    public Result<Page<Role>> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<Role> list = roleService.list(pageNum, pageSize);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<Role> getItem(@PathVariable Long id) {
        Role role = roleService.getById(id);
        return Result.success(role);
    }


    @PostMapping("/update/{id}")
    public Result update(@PathVariable Long id, @RequestBody Role role) {
        role.setId(id);
        boolean success = roleService.updateById(role);
        if (success) {
            return Result.success();
        }
        return Result.failure();
    }


    /**
     * 给角色分配菜单
     * @param allocMenuParam
     * @return
     */
    @PostMapping("/allocMenu")
    public Result allocMenu(@RequestBody AllocMenuParam allocMenuParam) {
        boolean success = roleService.allocMenu(allocMenuParam.getRoleId(), allocMenuParam.getMenuIds());
        if (success) {
            return Result.success();
        }
        return Result.failure();
    }

    /**
     * 角色拥有的菜单
     * @param roleId
     * @return
     */
    @GetMapping("/menuList/{roleId}")
    public Result<List<Menu>> menuList(@PathVariable Long roleId) {
        List<Menu> menuList = roleService.menuList(roleId);
        return Result.success(menuList);
    }


}

