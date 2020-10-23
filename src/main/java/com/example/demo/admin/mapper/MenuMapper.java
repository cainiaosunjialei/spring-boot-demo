package com.example.demo.admin.mapper;

import com.example.demo.admin.model.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台菜单表 Mapper 接口
 * </p>
 *
 * @author sunjialei
 * @since 2020-10-20
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenuListByRoleId(@Param("roleId") Long roleId);
}
