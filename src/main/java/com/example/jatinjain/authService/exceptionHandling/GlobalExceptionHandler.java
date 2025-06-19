package com.example.jatinjain.authService.exceptionHandling;

import com.example.jatinjain.authService.exceptionHandling.exceptions.UserNotRegisteredException;
import com.example.jatinjain.authService.exceptionHandling.exceptions.WrongCredentialsException;
import com.example.jatinjain.authService.model.DTOs.Response;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response> handleException(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response(false, e.getMessage(), ""));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Response> handleException(DuplicateKeyException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new Response(false, e.getMessage(), ""));
    }

    @ExceptionHandler(WrongCredentialsException.class)
    public ResponseEntity<Response> handleException(WrongCredentialsException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response(false, e.getMessage(), ""));
    }

    @ExceptionHandler(UserNotRegisteredException.class)
    public ResponseEntity<Response> handleException(UserNotRegisteredException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Response(false, e.getMessage(), ""));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Response(false, "unexpected error occur, try after some time", ""));
    }
}
