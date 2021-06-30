package com.example.controller;

import com.example.blogdao.entity.User;
import com.example.blogservice.UserService;
import com.example.entity.LoginForm;
import com.example.utils.Aes;
import com.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody LoginForm loginForm) {
        String userName = loginForm.getUsername();
        String password = loginForm.getPassword();
        try {
            User user = userService.findByName(userName);
            if (user.getUserPassword().equals(Aes.encrypt(password))) {
                return "密码正确";
            }else {
                return "密码错误";
            }
        } catch (Exception e) {
            return "账号不存在";
        }
    }
}
