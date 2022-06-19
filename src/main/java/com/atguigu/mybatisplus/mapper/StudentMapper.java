package com.atguigu.mybatisplus.mapper;

import com.atguigu.mybatisplus.entity.Student;
import com.atguigu.mybatisplus.req.CommonSearchReq;
import com.atguigu.mybatisplus.vo.StudentVO;
import com.atguigu.mybatisplus.vo.StudentVoTrans;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 学生表 Mapper 接口
 * </p>
 *
 * @author wonker47
 * @since 2022-06-13
 */
@Repository
public interface StudentMapper extends BaseMapper<Student> {
    List<StudentVO> queryList(CommonSearchReq searchReq);

    List<StudentVoTrans> queryListTrans(CommonSearchReq searchReq);

}
