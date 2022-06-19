package com.atguigu.mybatisplus.service.impl;

import com.atguigu.mybatisplus.entity.Student;
import com.atguigu.mybatisplus.exception.BizEx;
import com.atguigu.mybatisplus.mapper.StudentMapper;
import com.atguigu.mybatisplus.req.CommonSearchReq;
import com.atguigu.mybatisplus.req.StudentAddReq;
import com.atguigu.mybatisplus.req.StudentUpdateReq;
import com.atguigu.mybatisplus.results.Result;
import com.atguigu.mybatisplus.service.IStudentService;
import com.atguigu.mybatisplus.util.PageUtil;
import com.atguigu.mybatisplus.vo.StudentVO;
import com.atguigu.mybatisplus.vo.StudentVoTrans;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 学生表 服务实现类
 * </p>
 *
 * @author wonker47
 * @since 2022-06-13
 */
@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
@Resource StudentMapper studentMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addStudent(StudentAddReq addReq) {
        //处理8小时时差
        LocalDateTime enrollTime=addReq.getEnrollTime();
        enrollTime=enrollTime.plusHours(8);
        addReq.setEnrollTime(enrollTime);
        LambdaQueryWrapper<Student> qw=new LambdaQueryWrapper<>();
        qw.eq(Student::getSno,addReq.getSno());
        Long count= Long.valueOf(studentMapper.selectCount(qw));
        if(count>0){
            throw new BizEx("学号已存在");
        }
        Student stu=new Student();
        if(addReq!=null) {
            BeanUtils.copyProperties(addReq, stu);
            //生成随机id
            long id=(int)(Math.random()*1000000);
            LambdaQueryWrapper<Student> qw1=new LambdaQueryWrapper<>();
            qw1.eq(Student::getId,id);
            count=Long.valueOf(studentMapper.selectCount(qw1));
            while(count>0){
                id=(int)(Math.random()*1000000);
                count=Long.valueOf(studentMapper.selectCount(qw1));
            }
            stu.setId(id);
            studentMapper.insert(stu);
        }
        else {
            throw new BizEx("输入的学生信息为空!");
        }
    }

    @Override
    public Result selectPage(CommonSearchReq searchReq) {
        log.info("searchReq:{}",searchReq);
        PageUtil.startPage(searchReq.getPageNow(),searchReq.getPageSize());
        log.info("searchReq:{}",searchReq);
//        List<StudentVO> list =studentMapper.queryList(searchReq);
        List<StudentVoTrans> list=studentMapper.queryListTrans(searchReq);
        return PageUtil.wrapPageData(list);
//        return PageUtil.wrapPageDataStudent(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        studentMapper.deleteById(id);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<StudentVO> selectById(Long id) {
         Student student=studentMapper.selectById(id);
         if(student==null){
             return Result.buildEmptySuccess();
         }
         StudentVO vo =new StudentVO();
         BeanUtils.copyProperties(student,vo);
         log.info("查询结果:{}",vo);
         return Result.buildSuccess(vo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStudentById(StudentUpdateReq studentUpdateReq) {
        Student student=new Student();
        //处理8小时时间差
        log.info("前端传来的学生信息:{}",studentUpdateReq);
        LocalDateTime enrollTime=studentUpdateReq.getEnrollTime();
        enrollTime=enrollTime.plusHours(8);
        studentUpdateReq.setEnrollTime(enrollTime);
        log.info("处理后的前端传来的学生信息:{}",studentUpdateReq);
        BeanUtils.copyProperties(studentUpdateReq,student);
        studentMapper.updateById(student);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<List<StudentVO>> selectAll() {
        List<StudentVO> studentList= studentMapper.queryList(new CommonSearchReq());
        return Result.buildSuccess(studentList);
    }
}
