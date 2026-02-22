package com.example.springapp.Alumni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springapp.Alumni.model.JobReferral;
import java.util.List;

public interface JobReferralRepository extends JpaRepository<JobReferral, Long> {

    List<JobReferral> findByStudentId(Long studentId);
}