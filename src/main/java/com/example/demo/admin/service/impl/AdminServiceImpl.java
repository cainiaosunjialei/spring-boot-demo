package com.example.demo.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.admin.dto.AdminLoginParam;
import com.example.demo.admin.dto.RegisterParam;
import com.example.demo.admin.model.Admin;
import com.example.demo.admin.mapper.AdminMapper;
import com.example.demo.admin.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.Result;
import com.example.demo.exception.ApiException;
import com.example.demo.security.JwtUserDetails;
import com.example.demo.security.utils.JwtTokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author sunjialei
 * @since 2020-10-19
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Result register(RegisterParam registerParam) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Admin::getUsername, registerParam.getUsername());
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new ApiException("此账号已存在");
        }

        Admin admin = new Admin();
        BeanUtils.copyProperties(registerParam, admin);
        admin.setCreateTime(new Date());
        admin.setPassword(passwordEncoder.encode(registerParam.getPassword()));
        int insert = baseMapper.insert(admin);
        if (insert == 0) {
            return Result.failure();
        }
        return Result.success();
    }

    @Override
    public Result login(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new ApiException("密码不正确");
        }

        // 生成token
        String token = jwtTokenUtil.generateToken(userDetails);

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);

        return Result.success(map);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Admin::getUsername, username);

        Admin user = baseMapper.selectOne(wrapper);
        if (user != null) {
//            List<UmsResource> resourceList = resourceMapper.selectList(null);
            return new JwtUserDetails(user);
        }
        throw new ApiException("用户名不存在");
    }
}
