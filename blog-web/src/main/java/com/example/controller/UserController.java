package com.example.controller;

import com.example.blogdao.entity.User;
import com.example.blogservice.UserService;
import com.example.entity.LoginForm;
import com.example.utils.Aes;
import com.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody LoginForm loginForm) {
        String userName = loginForm.getUserName();
        String password = loginForm.getPassword();
        if (userName==null){
            return "请输入账号和密码！";
        }
        try {
            User user = userService.findByName(userName);
            if (user.getUserPassword().equals(Aes.encrypt(password))) {
                if (!user.getStatus()) {
                    return "用户被封禁";
                }
                Map<String, String> map = new HashMap<>();
                map.put("userName", user.getUserName());
                return JwtUtils.getToken(map);
            } else {
                return "密码错误";
            }
        } catch (Exception e) {
            return "账号不存在";
        }
    }
}
