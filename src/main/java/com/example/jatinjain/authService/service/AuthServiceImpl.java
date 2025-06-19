package com.example.jatinjain.authService.service;

import com.example.jatinjain.authService.exceptionHandling.exceptions.UserNotRegisteredException;
import com.example.jatinjain.authService.exceptionHandling.exceptions.WrongCredentialsException;
import com.example.jatinjain.authService.model.DTOs.LoginRequest;
import com.example.jatinjain.authService.model.DTOs.RegistrationRequest;
import com.example.jatinjain.authService.model.DTOs.Response;
import com.example.jatinjain.authService.model.entity.User;
import com.example.jatinjain.authService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    final private UserRepository userRepo;
    final private JwtService jwtService;
    final private PasswordEncoder passwordEncoder;
    static final private String EMAIL_REGEX = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
            + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.\\p{L}{2,})$";

    @Autowired
    public AuthServiceImpl(UserRepository userRepo, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Response registerUser(RegistrationRequest request) {
        String fullName = request.getFullName();
        String email = request.getEmail();
        String password = request.getPassword();

        if(fullName == null || email == null || password == null ||
                fullName.isBlank() || email.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException("request entries cannot be empty");
        }

        if(!email.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("email is not valid");
        }

        if(userRepo.findByEmail(email).isPresent()){
            throw new DuplicateKeyException("email is already registered");
        }

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        userRepo.save(user);
        return new Response(true, "user registered successfully", "");
    }

    @Override
    public Response loginUser(LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        if(email == null || password == null ||
                email.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException("email and password cannot be empty");
        }

        if(!email.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("email is not valid");
        }

        return userRepo.findByEmail(email)
                .map( user -> {
                    if(passwordEncoder.matches(password, user.getPassword())) {
                        String token = jwtService.generateToken(email);
                        return new Response(true, "login successful", token);
                    } else {
                        throw new WrongCredentialsException("Bad Credentials");
                    }
                })
                .orElseThrow(() -> new UserNotRegisteredException("user is not registered"));
    }
}
