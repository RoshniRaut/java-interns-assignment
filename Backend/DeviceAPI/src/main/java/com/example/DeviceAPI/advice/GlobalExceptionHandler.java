package com.example.DeviceAPI.advice;

import com.example.DeviceAPI.Exceptions.AlreadyRegistered;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
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

    //custom execption for already existing entry in table.
    @ExceptionHandler(AlreadyRegistered.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Map<String, String> AlreadyRegistered(AlreadyRegistered ex){
        Map<String, String> errorMap=new HashMap<>();
        errorMap.put("message",ex.getMessage());
        return errorMap;
    }

    //while login
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> badCredentialsException(BadCredentialsException ex){
        Map<String, String> errorMap=new HashMap<>();
        errorMap.put("message","invalid username or password");
        return errorMap;
    }

    //constraint validation on Tables
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Map<String, String> duplicateEntryInDB(SQLIntegrityConstraintViolationException ex){
        Map<String,String> error=new HashMap<>();
        error.put("message",ex.getMessage());
        return error;
    }

    //on deletion if id doesn't exist
    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Map<String, String> idNotExist(EmptyResultDataAccessException ex){
        Map<String,String> error=new HashMap<>();
        error.put("message",ex.getMessage());
        return error;
    }
}
