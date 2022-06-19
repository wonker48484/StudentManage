package com.atguigu.mybatisplus.context;

import com.atguigu.mybatisplus.dto.JwtManagerDTO;

public class ManagerContext {
    private static ThreadLocal<JwtManagerDTO> threadLocal=new ThreadLocal<>();
    public  static void set(JwtManagerDTO dto){
        threadLocal.set(dto);
    }
    public  static  void get(){
        threadLocal.get();
    }
    public static  void remove(){
        threadLocal.remove();
    }
}
