package com.example.springapp.Alumni.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springapp.Alumni.model.AlumniProfile;

public interface AlumniProfileRepository extends JpaRepository<AlumniProfile, Long> {

    Optional<AlumniProfile> findByUserId(Long userId);
}