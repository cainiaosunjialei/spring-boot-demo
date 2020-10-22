package com.example.demo.admin.model;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 后台用户角色表
 * </p>
 *
 * @author sunjialei
 * @since 2020-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("role")
public class Role implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 后台用户数量
     */
    private Integer adminCount;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 启用状态：0->禁用；1->启用
     */
    private Integer status;

    private Integer sort;


}
