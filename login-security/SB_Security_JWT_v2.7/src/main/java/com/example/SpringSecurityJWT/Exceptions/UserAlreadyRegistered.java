package com.example.SpringSecurityJWT.Exceptions;

public class UserAlreadyRegistered extends Exception {
    public UserAlreadyRegistered(String message) {
        super(message);
    }
}
