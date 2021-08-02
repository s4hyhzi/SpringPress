package com.example.blogdao;

import com.example.blogdao.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoryDao extends JpaRepository<Category,Integer>{
}
