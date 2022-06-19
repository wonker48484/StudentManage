package com.atguigu.mybatisplus.intercepter;

import com.atguigu.mybatisplus.enums.CodeEnum;
import com.atguigu.mybatisplus.exception.BizEx;
import com.atguigu.mybatisplus.results.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ExAdvice {
//处理MethodArgumentNotValidException异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<String> handleEx(MethodArgumentNotValidException exception){
        log.info("入参校验失败",exception);
        BindingResult bindingResult=exception.getBindingResult();
        //获取到校验失败的属性
        return spliceErrMsg(bindingResult.getFieldErrors(), exception);
    }
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public Result<String> handleEx(BindException e){
        log.info("参数绑定异常",e);
        return spliceErrMsg(e.getFieldErrors(), e);
    }

    private Result<String> spliceErrMsg(List<FieldError> fieldErrors2, BindException e) {
        List<FieldError>fieldErrors= fieldErrors2;
        String errMsg= fieldErrors.stream().map(fieldError -> {
            StringBuilder sb=new StringBuilder();
            sb.append("属性:").append(fieldError.getField())
                    .append("传过来的值是:").append(fieldError.getRejectedValue())
                    .append("校验不通过，原因:").append(fieldError.getDefaultMessage());
            return sb.toString();
        }).collect(Collectors.joining(";"));
        return Result.buildFailure(errMsg);
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<String> handleEx(IllegalArgumentException e){
        log.info("参数不合法",e);
        return Result.buildFailure(CodeEnum.PARAM_ERR);
    }

    @ResponseBody
    @ExceptionHandler(BadSqlGrammarException.class)
    public Result<String> handleEx(BadSqlGrammarException e){
        log.info("SQL语句错误",e);
        return Result.buildFailure(CodeEnum.DB_ERR);
    }

    @ResponseBody
    @ExceptionHandler(DuplicateKeyException.class)
    public Result<String> handleEx(DuplicateKeyException e){
        log.info("唯一性约束失败",e);
        return Result.buildFailure(CodeEnum.DUPLICATEKEY_ERR);
    }

    @ResponseBody
    @ExceptionHandler(BizEx.class)
    public Result<String> handleEx(BizEx e){
        log.info("业务异常",e);
        return Result.buildFailure(e.getCode(),e.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<String> handleEx(Exception e){
        log.info("未精准匹配的异常",e);
        return Result.buildFailure(CodeEnum.SYS_ERR);
    }
}
