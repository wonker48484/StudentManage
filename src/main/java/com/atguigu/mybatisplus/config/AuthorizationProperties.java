package com.atguigu.mybatisplus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties("auth")
public class AuthorizationProperties {
//不需要权限校验的列表
    private List<String> ignoreUrls;
}
