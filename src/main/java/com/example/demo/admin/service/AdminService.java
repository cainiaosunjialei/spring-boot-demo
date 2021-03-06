package com.example.demo.admin.service;

import com.example.demo.admin.dto.AdminLoginParam;
import com.example.demo.admin.dto.RegisterParam;
import com.example.demo.admin.model.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.admin.model.Role;
import com.example.demo.common.Result;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author sunjialei
 * @since 2020-10-19
 */
public interface AdminService extends IService<Admin> {

    Result register(RegisterParam registerParam);

    Result login(String username, String passwords);

    UserDetails loadUserByUsername(String username);

    boolean allocRole(Long adminId, List<Long> roleIds);

    List<Role> getRoleList(Long adminId);

    Admin getAdminByUsername(String username);
}
