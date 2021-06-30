package com.example.controller;

import com.example.annotation.UserLoginToken;
import com.example.blogdao.entity.Book;
import com.example.blogservice.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    BookService bookService;

    @UserLoginToken
    @GetMapping("/books")
    public List<Book> test() {
        return bookService.allBooks();
    }
}
