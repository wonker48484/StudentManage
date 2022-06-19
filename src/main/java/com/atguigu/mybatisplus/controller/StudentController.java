package com.atguigu.mybatisplus.controller;


import com.atguigu.mybatisplus.req.CommonSearchReq;
import com.atguigu.mybatisplus.req.StudentAddReq;
import com.atguigu.mybatisplus.req.StudentUpdateReq;
import com.atguigu.mybatisplus.results.Result;
import com.atguigu.mybatisplus.service.impl.StudentServiceImpl;
import com.atguigu.mybatisplus.vo.StudentVO;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 学生表 前端控制器
 * </p>
 *
 * @author wonker47
 * @since 2022-06-13
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource private StudentServiceImpl studentService;
    @PostMapping("/add")
    public Result<String>addStudent(@RequestBody @Validated StudentAddReq addReq){
        studentService.addStudent(addReq);
        return Result.buildSuccess("添加成功");
    }
    @PostMapping("/selectPage")
    public Result<List<StudentVO>>selectPage(@RequestBody @Validated CommonSearchReq searchReq){
        return studentService.selectPage(searchReq);
    }
    @GetMapping("/deleteById")
    public Result<String>deleteById(Long id){
        if(id== null){
            return Result.buildFailure("id不能为空");
        }
        studentService.deleteById(id);
        return Result.buildSuccess("删除成功");
    }
    @GetMapping("/selectById")
    public Result<StudentVO>selectById(Long id){
        if(id== null){
            return Result.buildFailure("id不能为空");
        }
        return studentService.selectById(id);
    }
    @PostMapping("/updateById")
    public Result<String>updateById(@RequestBody @Validated StudentUpdateReq updateReq){
        if(updateReq.getId()== null){
            return Result.buildFailure("id不能为空");
        }
        studentService.updateStudentById(updateReq);
        return Result.buildSuccess("更新成功");
    }
    @GetMapping("/selectAll")
    public Result<List<StudentVO>>selectAll(){
        return studentService.selectAll();
    }

}

