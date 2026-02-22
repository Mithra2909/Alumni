package com.example.springapp.Student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springapp.Student.entity.Student;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);
}