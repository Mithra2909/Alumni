package com.example.springapp.Student.service;

import com.example.springapp.Student.entity.Student;
import com.example.springapp.Student.entity.Role;
import com.example.springapp.Student.repository.StudentRepository;
import com.example.springapp.Student.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    // REGISTER
    public Student register(Student student) {

        student.setPassword(encoder.encode(student.getPassword()));
        student.setRole(Role.STUDENT);   // Default Role

        return repository.save(student);
    }

    // LOGIN
    public String login(String email, String password) {

        Student student = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(password, student.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        return jwtUtil.generateToken(student.getEmail(), student.getRole().name());
    }
}