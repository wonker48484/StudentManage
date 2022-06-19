package com.atguigu.mybatisplus.intercepter;

import com.atguigu.mybatisplus.constants.Constants;
import com.atguigu.mybatisplus.context.ManagerContext;
import com.atguigu.mybatisplus.dto.JwtManagerDTO;
import com.atguigu.mybatisplus.enums.CodeEnum;
import com.atguigu.mybatisplus.results.Result;
import com.atguigu.mybatisplus.util.JwtUtil;
import com.atguigu.mybatisplus.util.ResponseUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object){

        //打印请求的路径
        log.info("请求路径:{}",httpServletRequest.getServletPath());
        String method = httpServletRequest.getMethod();
        log.info("method:{}",method);
        if(HttpMethod.OPTIONS.matches(method)){
            //去执行剩余的filter
            log.info("method:{}",method);
            return true;
        }
        //获取token
        String tokenToVerify=
                httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("tokenToVerify:{}1",tokenToVerify);
        if(StringUtils.isBlank(tokenToVerify)){
            tokenToVerify=httpServletRequest.getParameter(Constants.USER_TOKEN_KEY);
            log.info("tokenToVerify:{}2",tokenToVerify);
            if(StringUtils.isBlank(tokenToVerify)){
                //响应失败
                log.info("tokenToVerify:{}3",tokenToVerify);
                ResponseUtil.respAppJson(httpServletResponse,Result.buildFailure(CodeEnum.AUTH_ERR));
                return false;
            }
        }
        //校验token
        Result<DecodedJWT>verifyResult= JwtUtil.verify(tokenToVerify);
        if(verifyResult.isFailed()){
            //响应失败
            log.info("tokenToVerify:{}4",tokenToVerify);
            ResponseUtil.respAppJson(httpServletResponse,Result.buildFailure(CodeEnum.AUTH_ERR));
            return false;
        }
        //从token中还原出放入的对象
        JwtManagerDTO dto=JwtUtil.parse(verifyResult.getData(),JwtManagerDTO.class);
        if(dto==null){
            //响应失败
            log.info("tokenToVerify:{}5",tokenToVerify);
            ResponseUtil.respAppJson(httpServletResponse,Result.buildFailure(CodeEnum.AUTH_ERR));
            return false;
        }
        log.info("还原后的dto:{}",dto);
        //放入ThreadLocal之后在任意层（controller、service...）
        ManagerContext.set(dto);
        return true;
    }
    public  void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object){
        ManagerContext.remove();
    }
}
