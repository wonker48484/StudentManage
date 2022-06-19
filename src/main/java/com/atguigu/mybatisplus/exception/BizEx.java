package com.atguigu.mybatisplus.exception;

import com.atguigu.mybatisplus.enums.CodeEnum;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;
@Data
public class BizEx extends RuntimeException {
    private Integer code;
    public BizEx(String msg){
        super(msg);
    }
    public  BizEx(CodeEnum codeEnum){
        super(codeEnum.getMsg());
        this.code=codeEnum.getCode();
    }
}
