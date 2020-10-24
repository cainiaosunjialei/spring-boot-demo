package com.example.demo.admin.mapper;

import com.example.demo.admin.model.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author sunjialei
 * @since 2020-10-22
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRoleListByAdminId(@Param("adminId") Long adminId);
}
