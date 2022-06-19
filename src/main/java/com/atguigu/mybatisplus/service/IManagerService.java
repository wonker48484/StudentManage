package com.atguigu.mybatisplus.service;

import com.atguigu.mybatisplus.entity.Manager;
import com.atguigu.mybatisplus.results.Result;
import com.atguigu.mybatisplus.vo.JwtManagerVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.mybatisplus.req.LoginReq;
/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author wonker47
 * @since 2022-06-13
 */
public interface IManagerService extends IService<Manager> {
    Result<JwtManagerVO> login(LoginReq loginReq);
}
