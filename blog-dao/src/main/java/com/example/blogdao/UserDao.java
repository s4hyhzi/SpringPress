package com.example.blogdao;

import com.example.blogdao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
    User getByUserName(String userName);
}
