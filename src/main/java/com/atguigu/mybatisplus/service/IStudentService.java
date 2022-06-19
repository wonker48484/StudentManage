package com.atguigu.mybatisplus.service;

import com.atguigu.mybatisplus.entity.Student;
import com.atguigu.mybatisplus.req.CommonSearchReq;
import com.atguigu.mybatisplus.req.StudentAddReq;
import com.atguigu.mybatisplus.req.StudentUpdateReq;
import com.atguigu.mybatisplus.results.Result;
import com.atguigu.mybatisplus.vo.StudentVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 学生表 服务类
 * </p>
 *
 * @author wonker47
 * @since 2022-06-13
 */
public interface IStudentService extends IService<Student> {
    //新增学生
void addStudent(StudentAddReq addReq);
//分页查询学生列表
Result<List<StudentVO>> selectPage(CommonSearchReq searchReq);

void deleteById(Long id);
Result<StudentVO> selectById(Long id);

void updateStudentById(StudentUpdateReq studentUpdateReq);
Result<List<StudentVO>>selectAll();

}
