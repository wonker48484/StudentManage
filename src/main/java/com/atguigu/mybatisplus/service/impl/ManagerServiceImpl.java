package com.atguigu.mybatisplus.service.impl;

import com.atguigu.mybatisplus.dto.JwtManagerDTO;
import com.atguigu.mybatisplus.entity.Manager;
import com.atguigu.mybatisplus.enums.CodeEnum;
import com.atguigu.mybatisplus.mapper.ManagerMapper;
import com.atguigu.mybatisplus.req.LoginReq;
import com.atguigu.mybatisplus.results.Result;
import com.atguigu.mybatisplus.service.IManagerService;
import com.atguigu.mybatisplus.util.JwtUtil;
import com.atguigu.mybatisplus.vo.JwtManagerVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author wonker47
 * @since 2022-06-13
 */
@Service
@Slf4j
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements IManagerService {
@Autowired ManagerMapper managerMapper;

    @Override
    public Result<JwtManagerVO> login(LoginReq loginReq) {

        LambdaQueryWrapper<Manager>qw=new LambdaQueryWrapper<>();
        qw.eq(Manager::getId,loginReq.getId())
                .eq(Manager::getPwd,loginReq.getPwd());
        Manager manager=managerMapper.selectOne(qw);
        if(manager!=null){
            JwtManagerVO vo =generateToken(manager);
            log.info("result:{}",vo);
            return Result.buildSuccess(vo);
        }
        return Result.buildFailure("用户名或密码错误");
    }

    private JwtManagerVO generateToken(Manager manager) {
        JwtManagerDTO dto=new JwtManagerDTO();
        BeanUtils.copyProperties(manager,dto);
        String token= JwtUtil.getToken(dto);
        //返回给及前端token+用户信息
        JwtManagerVO vo =new JwtManagerVO();

        BeanUtils.copyProperties(dto,vo);
        vo.setToken(token);
        return vo;
    }
}
