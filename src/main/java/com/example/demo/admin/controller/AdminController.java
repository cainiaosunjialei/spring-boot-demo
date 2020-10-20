package com.example.demo.admin.controller;


import com.example.demo.admin.dto.AdminLoginParam;
import com.example.demo.admin.dto.RegisterParam;
import com.example.demo.admin.service.AdminService;
import com.example.demo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author sunjialei
 * @since 2020-10-19
 */
@RestController
@RequestMapping("/admin/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterParam registerParam) {
        return adminService.register(registerParam);
    }

    @PostMapping("/login")
    public Result login(@RequestBody AdminLoginParam adminLoginParam) {
        return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword());
    }
}

