package com.example.blogdao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "article_category")
@Setter
@Getter
@NoArgsConstructor
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Category {
    private Integer id;
    private Integer pid;
    private Integer user_id;
    private String title;
    private String content;
    private String summary;
    private String type;
    private Integer order_number;
    private String meta_keywords;
    private String meta_description;
    private String created;
    private String modified;

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    public Integer getId() {
        return id;
    }
}
