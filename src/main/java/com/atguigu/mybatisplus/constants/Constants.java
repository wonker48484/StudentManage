package com.atguigu.mybatisplus.constants;

import com.baomidou.mybatisplus.generator.config.INameConvert;

import java.nio.charset.StandardCharsets;

public interface Constants {
    String USER_TOKEN_KEY="user_token";
    String UTF8_NAME = StandardCharsets.UTF_8.name();
    Integer DEFAULT_PAGE_NOW = 1;
    Integer DEFAULT_PAGE_SIZE = 10;
}
