package com.example.blogdao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user")
@Setter
@Getter
@NoArgsConstructor
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class User {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    private String username;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    private String nickname;
    private String email;
    private Boolean email_status;
    //身份
    private String identity;
    private Integer gender;
    //个人签名
    private String signature;
    //头像
    private String avatar;
    private String birthday;
    //公司
    private String company;
    //职业
    private String occupation;
    //毕业学校
    private String graduateschool;
    private String remark;
    //最后的登录时间
    private String logged;
    //激活时间
    private String activated;
    //学历
    private String education;
    private Boolean status;

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    public Integer getId() {
        return id;
    }
}
