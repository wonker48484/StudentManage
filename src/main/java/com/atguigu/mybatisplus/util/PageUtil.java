package com.atguigu.mybatisplus.util;

import com.atguigu.mybatisplus.results.Result;
import com.atguigu.mybatisplus.vo.StudentVO;
import com.atguigu.mybatisplus.vo.StudentVoTrans;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class PageUtil {
    //开始分页
    public  static void startPage(Integer pageNow,Integer pageSize){
        PageHelper.startPage(pageNow,pageSize);
    }


    //转换查询结果
    public static <T> Result<List<T>> wrapPageData(List<T> queryList){
        log.info("queryList.size={}",queryList.size());
        PageInfo<T> pageInfo=new PageInfo<>(queryList);
        log.info("page total={}",pageInfo.getTotal());
        return Result.buildSuccess(queryList,pageInfo.getTotal());
    }
//    public static  Result wrapPageDataStudent(List<StudentVoTrans> queryList){
//
//        List<StudentVoTrans> temp= new ArrayList<>();
//        int count=0;
//        for(StudentVO vo:queryList){
//            StudentVoTrans voTrans=new StudentVoTrans();
////            BeanUtils.copyProperties(queryList.get(i),voTrans);
//            count++;
//            voTrans.setId(vo.getId());
//            voTrans.setRealName(vo.getRealName());
//            voTrans.setSno(vo.getSno());
//            voTrans.setEnrollTime(vo.getEnrollTime().toLocalDate());
////            log.info("voTrans.enrollTime:{}",voTrans.getEnrollTime());
//            temp.add(voTrans);
//        }
//        log.info("vo count={}",count);
//        PageInfo<StudentVoTrans>result=new PageInfo<>(temp);
//        result.setTotal(Long.valueOf(queryList.size()));
//        log.info("page total:{}",result.getTotal());
//        return Result.buildSuccess(temp,result.getTotal());
//    }
}
