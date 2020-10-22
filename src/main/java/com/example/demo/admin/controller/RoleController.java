package com.example.demo.admin.controller;


import com.example.demo.admin.model.Role;
import com.example.demo.admin.service.RoleService;
import com.example.demo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
}

