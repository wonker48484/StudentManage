package com.atguigu.mybatisplus.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginReq {
    @NotNull
    private Integer id;
    @NotBlank
    private String pwd;
}
