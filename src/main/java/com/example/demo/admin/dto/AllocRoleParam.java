package com.example.demo.admin.dto;


import lombok.Data;


import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AllocRoleParam {

    @NotNull
    private Long adminId;

    private List<Long> roleIds;
}
