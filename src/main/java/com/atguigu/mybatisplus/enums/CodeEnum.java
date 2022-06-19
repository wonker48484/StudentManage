package com.atguigu.mybatisplus.enums;
//响应码枚举类
public enum CodeEnum {
    SUCCESS(200,"成功"),
    PARAM_ERR(10000,"参数错误"),
    DB_ERR(30000,"数据库异常"),
    DUPLICATEKEY_ERR(20001,"唯一性约束校验失败异常"),
    BIZ_ERR(40000,"业务异常"),
    SYS_ERR(50000,"系统异常"),
    AUTH_ERR(60000,"权限异常");
    private Integer code;
    private String msg;
    CodeEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
    public Integer getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }
}
