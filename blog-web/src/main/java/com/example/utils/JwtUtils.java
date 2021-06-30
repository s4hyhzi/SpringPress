package com.example.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

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
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", "1");
        map.put("loginName", "admin");
        String token = getToken(map);
        System.out.println(token);
        try {

            DecodedJWT tokenInfo = verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpbk5hbWUiOiJhZG1pbiIsImV4cCI6MTYyNTAxODQ1MSwidXNlcklkIjoiMSJ9.osqotyTXaFQ6vGw6KbxWKzgSYqThJeurSY0vgPOHLnA");
            String userId = tokenInfo.getClaim("userId").asString();
            String loginName = tokenInfo.getClaim("loginName").asString();

            System.out.println(userId);
            System.out.println(loginName);
        }catch (TokenExpiredException tokenExpiredException){
            System.out.println("过期");
        }
    }
}
