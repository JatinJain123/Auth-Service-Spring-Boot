package com.example.jatinjain.authService.rest;

import com.example.jatinjain.authService.model.DTOs.LoginRequest;
import com.example.jatinjain.authService.model.DTOs.RegistrationRequest;
import com.example.jatinjain.authService.model.DTOs.Response;
import com.example.jatinjain.authService.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
    final private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody RegistrationRequest request) {
        Response response = authService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody LoginRequest request) {
        Response response = authService.loginUser(request);
        return ResponseEntity.ok(response);
    }
}
