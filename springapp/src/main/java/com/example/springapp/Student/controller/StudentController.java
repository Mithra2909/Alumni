package com.example.springapp.Student.controller;

import com.example.springapp.Student.entity.Student;
import com.example.springapp.Student.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/student")

public class StudentController {

    @Autowired
    private StudentService service;

    // REGISTER
    @PostMapping("/register")
    public Student register(@RequestBody Student student) {
        return service.register(student);
    }

    // LOGIN
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {

        String token = service.login(
                request.get("email"),
                request.get("password")
        );

        return Map.of("token", token);
    }
}