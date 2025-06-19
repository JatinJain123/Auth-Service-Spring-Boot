package com.example.jatinjain.authService.service;

import com.example.jatinjain.authService.model.DTOs.LoginRequest;
import com.example.jatinjain.authService.model.DTOs.RegistrationRequest;
import com.example.jatinjain.authService.model.DTOs.Response;

public interface AuthService {
    Response registerUser(RegistrationRequest request);
    Response loginUser(LoginRequest request);
}
