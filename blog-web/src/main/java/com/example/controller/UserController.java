package com.example.controller;

import com.example.blogdao.entity.User;
import com.example.blogservice.service.UserService;
import com.example.entity.LoginForm;
import com.example.entity.ServerEnum;
import com.example.utils.Aes;
import com.example.utils.JwtUtils;
import com.example.utils.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseServer login(@RequestBody LoginForm loginForm) {
        String userName = loginForm.getUserName();
        String password = loginForm.getPassword();
        if (userName==null||password==null){
            return ResponseServer.error(ServerEnum.LOGIN_ISNULL);
        }
        try {
            User user = userService.findByName(userName);
            if (user.getPassword().equals(Aes.encrypt(password))) {
                if (!user.getStatus()) {
                    return ResponseServer.error(ServerEnum.LOGIN_BAN);
                }
                Map<String, String> map = new HashMap<>();
                map.put("userName", user.getUsername());
                String token=JwtUtils.getToken(map);
                Map result=new HashMap();
                System.out.println(user.toString());
                user.setPassword(null);
                user.setId(null);
                result.put("userInfo",user);
                result.put("token",token);
                return ResponseServer.success(result);
            } else {
                return ResponseServer.error(ServerEnum.PASSWORD_WRONG);
            }
        } catch (Exception e) {
            return ResponseServer.error(ServerEnum.USERNAME_NOTEXIST);
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseServer register(){
        return ResponseServer.success();
    }
}
