package com.example.jatinjain.authService.exceptionHandling.exceptions;

public class WrongCredentialsException extends RuntimeException{
    public WrongCredentialsException(String message) {
        super(message);
    }
}
