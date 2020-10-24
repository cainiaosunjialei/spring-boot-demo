package com.example.demo.admin.controller;


import com.example.demo.admin.dto.AdminLoginParam;
import com.example.demo.admin.dto.AllocRoleParam;
import com.example.demo.admin.dto.RegisterParam;
import com.example.demo.admin.model.Admin;
import com.example.demo.admin.model.Menu;
import com.example.demo.admin.model.Role;
import com.example.demo.admin.service.AdminService;
import com.example.demo.admin.service.RoleService;
import com.example.demo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private RoleService roleService;

    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterParam registerParam) {
        return adminService.register(registerParam);
    }

    @PostMapping("/login")
    public Result login(@RequestBody AdminLoginParam adminLoginParam) {
        return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword());
    }


    /**
     * 用户分配角色
     * @param allocRoleParam
     * @return
     */
    @PostMapping("/allocRole")
    public Result allocRole(@Validated @RequestBody AllocRoleParam allocRoleParam) {
        boolean success = adminService.allocRole(allocRoleParam.getAdminId(), allocRoleParam.getRoleIds());
        if (success) {
            return Result.success();
        }
        return Result.failure();
    }



    /**
     * 获取登录用户信息
     * @param principal
     * @return
     */
    @GetMapping("/info")
    public Result info(Principal principal) {
        String username = principal.getName();
        Admin admin = adminService.getAdminByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);

        // 获取当前用户菜单
        List<Menu> menuList = roleService.getMenuListByAdminId(admin.getId());
        data.put("menus", menuList);

//        data.put("icon", umsAdmin.getIcon());
        // 获取当前用户角色
        List<Role> roleList = adminService.getRoleList(admin.getId());
        data.put("roles", roleList);
//        if(CollUtil.isNotEmpty(roleList)){
//            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
//            data.put("roles",roles);
//        }
        return Result.success(data);
    }


//    @PreAuthorize("hasRole('admin')")
//    @GetMapping("/wo")
//    public String test() {
//        return "123";
//    }

}

