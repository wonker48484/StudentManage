package com.atguigu.mybatisplus.util;

import com.alibaba.fastjson.JSON;
import com.atguigu.mybatisplus.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class ResponseUtil {
    //向浏览器写入数据
    public static void respAppJson(HttpServletResponse response,Object object ){
        response.setCharacterEncoding(Constants.UTF8_NAME);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try(PrintWriter pw=response.getWriter()){
            pw.print(JSON.toJSONString(object));
            pw.flush();
        }catch (IOException e){
            log.error("写到前端异常",e);
        }
    }
}
