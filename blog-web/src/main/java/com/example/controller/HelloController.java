package com.example.controller;

import com.example.annotation.TokenCheckAnnotation;
import com.example.blogdao.entity.Book;
import com.example.blogservice.service.BookService;
import com.example.utils.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    BookService bookService;
    @TokenCheckAnnotation
    @GetMapping("/books")
    public ResponseServer test() {
        List<Book> books=bookService.allBooks();
        return ResponseServer.success(books);
    }
}
