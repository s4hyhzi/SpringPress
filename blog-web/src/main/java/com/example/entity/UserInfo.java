package com.example.entity;

import lombok.Data;

@Data
public class UserInfo {
    private String userName;
    private String userNikeName;
    private String userEmail;
    private String userAvatar;

    public UserInfo(String userName, String userNikeName, String userEmail, String userAvatar) {
        this.userName=userName;
        this.userNikeName=userNikeName;
        this.userEmail=userEmail;
        this.userAvatar=userAvatar;
    }
}
