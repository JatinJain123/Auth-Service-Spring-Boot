package com.example.jatinjain.authService.rest;

import com.example.jatinjain.authService.model.entity.User;
import com.example.jatinjain.authService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/*
* example usage of jwt token
*/

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
    private final UserRepository userRepo;

    @Autowired
    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/my-profile")
    public ResponseEntity<User> getUser(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        return userRepo.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
