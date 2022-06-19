package com.atguigu.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.atguigu.mybatisplus.mapper")
public class AtGuiguApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtGuiguApplication.class, args);
    }

}
