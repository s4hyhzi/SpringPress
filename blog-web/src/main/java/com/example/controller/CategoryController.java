package com.example.controller;

import com.example.blogdao.entity.Category;
import com.example.blogservice.service.CategoryService;
import com.example.utils.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    public ResponseServer CategoryGet(){
        List<Category> result= categoryService.allCategory();
        return ResponseServer.success(result);
    }
}
