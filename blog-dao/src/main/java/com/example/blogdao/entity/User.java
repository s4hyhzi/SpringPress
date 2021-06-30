package com.example.blogdao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user")
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer","handler"})
public class User {
    private Integer id;
    private String userName;
    private String userPassword;
    private String userNikeName;
    private String userEmail;
    private String userAvatar;
    private Boolean status;
    private Boolean delFlag;

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    public Integer getId() {
        return id;
    }
}
