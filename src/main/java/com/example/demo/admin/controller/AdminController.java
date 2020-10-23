package com.example.demo.admin.controller;


import com.example.demo.admin.dto.AdminLoginParam;
import com.example.demo.admin.dto.AllocRoleParam;
import com.example.demo.admin.dto.RegisterParam;
import com.example.demo.admin.service.AdminService;
import com.example.demo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author sunjialei
 * @since 2020-10-19
 */
@RestController
@RequestMapping("/admin")
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

    @PostMapping("/allocRole")
    public Result allocRole(@Validated @RequestBody AllocRoleParam allocRoleParam) {
        boolean success = adminService.allocRole(allocRoleParam.getAdminId(), allocRoleParam.getRoleIds());
        if (success) {
            return Result.success();
        }
        return Result.failure();
    }


//    @PreAuthorize("hasRole('admin')")
//    @GetMapping("/wo")
//    public String test() {
//        return "123";
//    }

}

