package com.example.DeviceAPI.Exceptions;

public class UserAlreadyRegistered extends Exception {
    public UserAlreadyRegistered(String message) {
        super(message);
    }
}
