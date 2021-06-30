package com.example.blogservice;

import com.example.blogdao.UserDao;
import com.example.blogdao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User findById(Integer id){
        return userDao.getById(id);
    }

    public User findByName(String userName){
        return userDao.getByUserName(userName);
    }
}
