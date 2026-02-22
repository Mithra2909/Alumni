package com.example.springapp.Alumni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.springapp.Alumni.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

    Page<Job> findByStatus(String status, Pageable pageable);

    Page<Job> findByCompanyContainingIgnoreCase(String company, Pageable pageable);
}