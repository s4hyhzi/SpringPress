package com.example.exception;

import com.example.entity.ServerEnum;

public class AuthenticateException extends RuntimeException{
    private Integer code;
    public AuthenticateException(ServerEnum serverEnum) {
        super(serverEnum.getMessage());
        this.code=serverEnum.getCode();
    }
    public Integer getCode() {
        return code;
    }
}
