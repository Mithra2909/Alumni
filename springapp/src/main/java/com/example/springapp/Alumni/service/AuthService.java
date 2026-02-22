package com.example.springapp.Alumni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.springapp.Alumni.model.User;
import com.example.springapp.Alumni.repository.UserRepository;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // REGISTER
    public String register(User user) {

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            return "Email already exists!";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus("ACTIVE");

        userRepository.save(user);

        return "User Registered Successfully!";
    }

    // LOGIN
    public String login(String email, String password) {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (!userOptional.isPresent()) {
            return "User not found!";
        }

        User user = userOptional.get();

        if (!user.getStatus().equals("ACTIVE")) {
            return "User is blocked!";
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Invalid password!";
        }

        return "Login Successful! Role: " + user.getRole();
    }
}