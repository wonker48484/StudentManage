package com.atguigu.mybatisplus.vo;

import com.atguigu.mybatisplus.dto.JwtManagerDTO;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class JwtManagerVO extends JwtManagerDTO {
private String token;

}

