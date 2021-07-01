package com.example.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.entity.ServerEnum;
import com.example.exception.AuthenticateException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    /**
     * 过期60分钟
     */
    private static final Integer EXPIRE_TIME = 60 * 60 * 1000;
    //秘钥 盐
    private static final String SECRET = "YI5VMMNCX3QN2LR3KD54QCJOK8EJ1MCI";

    /**
     * 生成token header.payload.sing
     *
     * @param map
     * @return
     */
    public static String getToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        //设置过期时间单位:秒
        instance.add(Calendar.SECOND, EXPIRE_TIME);
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        //制定令牌过期时间
        String token = builder.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                //sign
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 验证token 合法性 || 获取token信息方法
     *
     * @param token
     */
    public static ResponseServer verify(String token) {
        DecodedJWT decodedJWT;
        try{
            decodedJWT=JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        }catch (ExpiredJwtException exp){
            return ResponseServer.error(ServerEnum.TOKEN_TIMEOUT);
        }catch (SignatureException signatureException){
            return ResponseServer.error(ServerEnum.SAFETY_ERROR);
        }catch (JWTDecodeException jwtDecodeException){
            return ResponseServer.error(ServerEnum.SECRET_ERROR);
        }catch (Exception e){
            return ResponseServer.error(ServerEnum.ERROR);
        }
        return ResponseServer.success(decodedJWT);
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", "1");
        map.put("loginName", "admin");
        String token = getToken(map);
        System.out.println(token);
        try {

            ResponseServer responseServer = verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6ImFkbWluIiwiZXhwIjoxNjI1MTA4MDg3fQ.0sN7cIUcbd_c2Hwzt4gz__h19efOtPB92mJqfjyGY30");
            if (responseServer.getCode() != 200){
                throw new AuthenticateException(ServerEnum.LOGIN_EXPIRED);
            }
            DecodedJWT tokenInfo= (DecodedJWT) responseServer.getData();
            System.out.println(tokenInfo);
            String userName = tokenInfo.getClaim("userName").asString();
//            String loginName = tokenInfo.getClaim("loginName").asString();

//            System.out.println(userId);
            System.out.println(userName);
        }catch (TokenExpiredException tokenExpiredException){
            System.out.println("过期");
        }
    }
}
