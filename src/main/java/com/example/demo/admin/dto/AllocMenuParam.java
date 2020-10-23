package com.example.demo.admin.dto;

import lombok.Data;

import java.util.List;

@Data
public class AllocMenuParam {

    private Long roleId;

    private List<Long> menuIds;
}
