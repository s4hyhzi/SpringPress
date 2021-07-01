package com.example.exception;

import com.example.entity.ServerEnum;

public class AuthenticateException extends RuntimeException{
    private Integer code;
    private Boolean success;
    public AuthenticateException(ServerEnum serverEnum) {
        super(serverEnum.getMessage());
        this.code=serverEnum.getCode();
        this.success=serverEnum.getSuccess();
    }
    public Integer getCode() {
        return code;
    }

    public Boolean getSuccess() {
        return success;
    }
}
