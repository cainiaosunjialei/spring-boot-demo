package com.example.demo.admin.dto;

import com.example.demo.admin.model.Menu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 菜单节点
 */
@Setter
@Getter
public class MenuNode extends Menu {

    private List<MenuNode> children;

}
