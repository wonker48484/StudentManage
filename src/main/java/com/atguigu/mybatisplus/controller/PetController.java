package com.atguigu.mybatisplus.controller;


import com.atguigu.mybatisplus.entity.Pet;
import com.atguigu.mybatisplus.mapper.PetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wonker47
 * @since 2022-05-08
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetMapper petMapper;
    @ResponseBody
    @GetMapping("/all")
    public List<Pet> getAll(){
        return petMapper.selectList(null);
    }
}

