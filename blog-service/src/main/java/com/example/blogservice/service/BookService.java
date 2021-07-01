package com.example.blogservice.service;

import com.example.blogdao.BookDao;
import com.example.blogdao.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookDao bookDao;

    public List<Book> allBooks() {
        return bookDao.findAll();
    }

}
