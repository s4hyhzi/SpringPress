package com.example.handler;

import com.example.exception.AuthenticateException;
import com.example.utils.ResponseServer;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticateException.class)
    public ResponseServer authenticateException(AuthenticateException e, HttpServletRequest request, HttpServletResponse response){
        return ResponseServer.error(e.getCode(),e.getMessage(),e.getSuccess());
    }

    @ExceptionHandler(Exception.class)
    public ResponseServer exceptionHandler(Exception e,HttpServletRequest request, HttpServletResponse response){
        e.printStackTrace();
        return ResponseServer.error();

    }
}
