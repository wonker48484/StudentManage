package com.atguigu.mybatisplus.results;

import com.atguigu.mybatisplus.enums.CodeEnum;
import lombok.Data;
import org.aspectj.apache.bcel.classfile.Code;

@Data
public class Result <T>{
    //响应信息
    private Integer code= CodeEnum.SUCCESS.getCode();
    private String msg=CodeEnum.SUCCESS.getMsg();

    private  Boolean success=Boolean.TRUE;
    //数据
    private T data;

    private long total;

    //成功类方法
    public static <T>Result<T> buildEmptySuccess(){
        return new Result<>();
    }
    public static <T>Result<T> buildSuccess(T t){
        Result<T> objectResult= buildEmptySuccess();
        objectResult.setData(t);
        return objectResult;
    }
    public static <T>Result<T> buildSuccess(T t,long total){
        Result<T> objectResult= buildSuccess(t);
        objectResult.setTotal(total);
        return objectResult;
    }
    //失败类的方法
    public static  <T>Result<T> buildFailure(String msg){
        Result<T>result=new Result<>();
        result.setCode(null);
        result.setMsg(msg);
        result.setSuccess(false);
        return result;
    }
    public static  <T>Result<T> buildFailure(Integer code,String msg){
        Result<T>result=new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setSuccess(false);
        return result;
    }
    public static  <T>Result<T> buildFailure(CodeEnum codeEnum){
        Result<T>result=new Result<>();
        result.setCode(codeEnum.getCode());
        result.setMsg(codeEnum.getMsg());
        result.setSuccess(false);
        return result;
    }
    public Boolean isSuccess(){
        return success;
    }
    public Boolean isFailed(){
        return !isSuccess();
    }
}
