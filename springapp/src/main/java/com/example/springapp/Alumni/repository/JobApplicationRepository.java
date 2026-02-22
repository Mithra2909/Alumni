package com.example.springapp.Alumni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springapp.Alumni.model.JobApplication;
import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    boolean existsByJobIdAndApplicantId(Long jobId, Long applicantId);

    List<JobApplication> findByJobId(Long jobId);

    List<JobApplication> findByApplicantId(Long applicantId);
}