package com.example.jatinjain.authService.exceptionHandling.exceptions;

public class UserNotRegisteredException extends RuntimeException{
    public UserNotRegisteredException(String message) {
        super(message);
    }
}
