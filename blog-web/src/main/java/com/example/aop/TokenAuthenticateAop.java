package com.example.aop;

import com.example.annotation.TokenCheckAnnotation;
import com.example.entity.ServerEnum;
import com.example.exception.AuthenticateException;
import com.example.utils.JwtUtils;
import com.example.utils.ResponseServer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Aspect // 作用是把当前类标识为一个切面供容器读取
@Component // 把当前类交给spring管理
public class TokenAuthenticateAop {
    @Around(value = "execution(* com.example.controller.*.*(..)) && @annotation(tokenCheckAnnotation)")
    public Object tokenAuth(ProceedingJoinPoint joinPoint, TokenCheckAnnotation tokenCheckAnnotation) {
        Object proceed = null;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String token = request.getHeader("Authentication");
        // 验证token是否为空
        if (token == null) {
            throw new AuthenticateException(ServerEnum.TOKEN_ISNULL);
        }

        // 验证token是否失效
        ResponseServer responseServer = JwtUtils.verify(token);
        if (responseServer.getCode() != 200) {
            throw new AuthenticateException(ServerEnum.LOGIN_EXPIRED);
        }

        // 执行目标方法
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return proceed;
    }
}
