package com.example.springapp.Alumni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.springapp.Alumni.model.User;
import com.example.springapp.Alumni.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    // REGISTER API
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.register(user);
    }

    // LOGIN API
    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        return authService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }
}