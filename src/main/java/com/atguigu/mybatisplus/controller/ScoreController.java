package com.atguigu.mybatisplus.controller;


import com.atguigu.mybatisplus.req.*;
import com.atguigu.mybatisplus.results.Result;
import com.atguigu.mybatisplus.service.impl.ScoreServiceImpl;
import com.atguigu.mybatisplus.vo.ScoreVO;
import com.atguigu.mybatisplus.vo.StudentVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wonker47
 * @since 2022-06-13
 */
@RestController
@RequestMapping("/score")
public class ScoreController {
@Resource private
    ScoreServiceImpl service;
    @PostMapping("/add")
    public Result<String> addScore(@RequestBody @Validated ScoreAddReq addReq){
        service.addScore(addReq);
        return Result.buildSuccess("添加成功");
    }
    @PostMapping("/selectPage")
    public Result<List<ScoreVO>>selectPage(@RequestBody @Validated CommonSearchReq searchReq){
        return service.selectPage(searchReq);
    }

    @GetMapping("/deleteById")
    public Result<String>deleteById(Long id){
        if(id== null){
            return Result.buildFailure("id不能为空");
        }
        service.deleteById(id);
        return Result.buildSuccess("删除成功");
    }
    @PostMapping("/updateById")
    public Result<String>updateById(@RequestBody @Validated ScoreUpdateReq updateReq){
        if(updateReq.getId()== null){
            return Result.buildFailure("id不能为空");
        }
        service.updateScoreById(updateReq);
        return Result.buildSuccess("更新成功");
    }
    @GetMapping("/selectById")
    public Result<ScoreVO>selectById(Long id){
        if(id== null){
            return Result.buildFailure("id不能为空");
        }
        return service.selectById(id);
    }
}

