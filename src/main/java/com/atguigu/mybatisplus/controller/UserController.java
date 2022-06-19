package com.atguigu.mybatisplus.controller;


import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wonker47
 * @since 2022-05-08
 */
@SuppressWarnings("unused")
@RestController
@RequestMapping("/user")
public class UserController {
@Autowired UserMapper userMapper;
@ResponseBody
@GetMapping("/all")
    public List<User>getAllPerson(){

	return userMapper.selectList(null);
}
@ResponseBody
@GetMapping("/name/{name}")
    public User getPersonById(@PathVariable("name") String name) {
        QueryWrapper<User> querycond=
                new QueryWrapper<User>();
        querycond=querycond.eq("userName",name);
		//		保证只有一个才可以用selectone,否则用selectList
        return userMapper.selectOne(querycond);
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
	public HashMap insertPeron(@RequestBody User person) {
		HashMap result=new HashMap();
		result.put("success",false);
//		判断要插入的Id是否已存在
		String id=person.getUsername();
		if(this.getPersonById(id)!=null) {
			result.put("success","Id already exsited");
			return result;
		}

		int i=userMapper.insert(person);
		if(i==1) {
			result.put("success",true);
		}
		return result;
	}


}

