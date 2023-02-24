package com.example.SpringSecurityJWT.advice;

import com.example.SpringSecurityJWT.Exceptions.UserAlreadyRegistered;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Map<String ,String> customValidationError(MethodArgumentNotValidException ex){
        Map<String,String> error=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err->{
            error.put(err.getField(),err.getDefaultMessage());
        });
        return error;
    }

    @ExceptionHandler(UserAlreadyRegistered.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Map<String, String> badCredentialsException(UserAlreadyRegistered ex){
        Map<String, String> errorMap=new HashMap<>();
        errorMap.put("message",ex.getMessage());
        return errorMap;
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> badCredentialsException(BadCredentialsException ex){
        Map<String, String> errorMap=new HashMap<>();
        errorMap.put("message","invalid username or password");
        return errorMap;
    }
}
