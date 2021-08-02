package com.example.blogservice.service;

import com.example.blogdao.CategoryDao;
import com.example.blogdao.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;

    public List<Category> allCategory(){
        return categoryDao.findAll();
    }
}
