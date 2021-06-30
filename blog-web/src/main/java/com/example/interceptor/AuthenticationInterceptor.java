package com.example.interceptor;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.annotation.PassToken;
import com.example.annotation.UserLoginToken;
import com.example.blogdao.entity.User;
import com.example.blogservice.UserService;
import com.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String token = request.getHeader("Authentication");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod) object;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)){
            PassToken passToken=method.getAnnotation(PassToken.class);
            if (passToken.required()){
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)){
            UserLoginToken userLoginToken=method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()){
                //执行认证
                if (token==null){
                    throw new RuntimeException("无token，请重新登录");
                }
                //获取token中的用户信息
                String userName;
                try {
                    userName= JwtUtils.verify(token).getClaim("userName").asString();
                }catch (TokenExpiredException tokenExpiredException){
                    throw new RuntimeException("登录过期");
                } catch (Exception e){
                    throw new RuntimeException("401");
                }
                User user=userService.findByName(userName);
                if (user==null){
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
