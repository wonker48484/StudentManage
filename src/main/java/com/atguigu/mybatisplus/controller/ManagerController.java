package com.atguigu.mybatisplus.controller;


import com.atguigu.mybatisplus.entity.Manager;
import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.ManagerMapper;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.req.LoginReq;
import com.atguigu.mybatisplus.results.Result;
import com.atguigu.mybatisplus.service.IManagerService;
import com.atguigu.mybatisplus.vo.JwtManagerVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author wonker47
 * @since 2022-06-13
 */
@Slf4j
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ManagerMapper managerMapper;

    @Resource
    private IManagerService iManagerService;

    @ResponseBody
    @GetMapping("/all")
    public List<Manager> getAllManager(){

        return managerMapper.selectList(null);
    }
    @ResponseBody
    @GetMapping("/id/{id}")
    public Manager getManagerById(@PathVariable("id") long id) {
        QueryWrapper<Manager> querycond=
                new QueryWrapper<Manager>();
        querycond=querycond.eq("id",id);
        //		保证只有一个才可以用selectone,否则用selectList
        return managerMapper.selectOne(querycond);
//		List<User>pList=userMapper.selectList(querycond);
//		if(pList!=null) {
//			return pList.get(0);
//		}
//		else {
//			System.out.println("error");
//			return null;
//		}
    }
    @ResponseBody
    @PostMapping("/")
    public HashMap insertManager(@RequestBody Manager manager) {
        HashMap result=new HashMap();
        result.put("success",false);
//		判断要插入的Id是否已存在
        long id=manager.getId();
        if(this.getManagerById(id)!=null) {
            result.put("success","Id already exsited");
            return result;
        }

        int i=managerMapper.insert(manager);
        if(i==1) {
            result.put("success",true);
        }
        return result;
    }
    //修改
    @ResponseBody
    @PostMapping("/update")
    public HashMap updateManager(@RequestBody Manager manager){
        HashMap result=new HashMap();
        result.put("success",false);
        //		判断要更新的Id是否已存在
        long id=manager.getId();
        if(this.getManagerById(id)==null) {
            result.put("success","add new Manager failed");
            int i=managerMapper.insert(manager);
            if(i==1) {
                result.put("success","add new Manager");
            }
            return result;
        }
        else{
            QueryWrapper<Manager> querycond=
                    new QueryWrapper<Manager>();
            querycond=querycond.eq("id",id);
            int i=managerMapper.delete(querycond);
            int i1=managerMapper.insert(manager);
            if(i==1&&i1==1){
                result.put("success","update successs");
            }
        }
        return result;
    }
    //根据id删除
    @ResponseBody
    @DeleteMapping("/id/{id}")
    public HashMap DeleteManagerById(@PathVariable("id") long id){
        HashMap result=new HashMap();
        result.put("success",false);
//		判断要删除的Id是否已存在

        if(this.getManagerById(id)==null) {
            result.put("failed","Id not exsit");
            return result;
        }
        QueryWrapper<Manager> querycond=
                new QueryWrapper<Manager>();
        querycond=querycond.eq("id",id);
        int i=managerMapper.delete(querycond);
        if(i==1) {
            result.put("success",true);
        }
        return result;
    }

    //登录操作

    @PostMapping("/login")
    public Result<JwtManagerVO> login(@RequestBody @Validated LoginReq loginReq){
        log.info("login param:{}",loginReq);
        return iManagerService.login(loginReq);
    }


    @PostMapping("/login2")
    public Result<JwtManagerVO> login2( @Validated LoginReq loginReq){
        log.info("loginw param:{}",loginReq);
        return iManagerService.login(loginReq);
    }

}

