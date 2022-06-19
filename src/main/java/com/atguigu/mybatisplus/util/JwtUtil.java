package com.atguigu.mybatisplus.util;

import com.alibaba.fastjson.JSON;
import com.atguigu.mybatisplus.dto.JwtManagerDTO;
import com.atguigu.mybatisplus.results.Result;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;

@Slf4j
public class JwtUtil {
private static final String SECRET ="roadJava.com";
//生成token
    public static <T> String getToken(T t){

        //定义过期时间
        Calendar instance=Calendar.getInstance();
        instance.add(Calendar.MONTH,1);
        JWTCreator.Builder builder= JWT.create()
                .withClaim(t.getClass().getSimpleName(), JSON.toJSONString(t));
        String token=builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SECRET));
        return token;
    }
//用来校验token是否合法
    public static Result<DecodedJWT> verify(String tokenToVerify) {
        String errMsg;
        try{
            DecodedJWT decodedJWT=JWT.require(Algorithm.HMAC256(SECRET))
                    .build().verify(tokenToVerify);
            return Result.buildSuccess(decodedJWT);
        }catch (AlgorithmMismatchException e){
            errMsg="算法不匹配";
            log.error(errMsg);
        }
        catch (SignatureVerificationException signatureVerificationException){
            errMsg="签名不一致";
            log.error(errMsg);
        }catch (TokenExpiredException tokenExpiredException){
            errMsg="token过期";
            log.error(errMsg);
        }catch (InvalidClaimException invalidClaimException){
            errMsg="声明无效";
            log.error(errMsg);
        }catch (Exception exception){
            errMsg="校验令牌失败";
            log.error(errMsg);
        }
        return Result.buildFailure(errMsg);
    }

    public static <T> T  parse(DecodedJWT decodedJWT, Class<T> clz) {
        //与放入时的声明一致
        Claim claim=decodedJWT.getClaim(clz.getSimpleName());
        if(claim==null){
            return  null;
        }
        return JSON.parseObject(claim.asString(),clz);
    }
}
