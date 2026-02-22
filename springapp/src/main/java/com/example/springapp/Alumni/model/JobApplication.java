package com.example.springapp.Alumni.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_applications",
       uniqueConstraints = @UniqueConstraint(columnNames = {"jobId", "applicantId"}))
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;
    private Long applicantId;

    @Column(length = 3000)
    private String resumeLink;

    private String status; // APPLIED, SHORTLISTED, REJECTED

    private LocalDateTime appliedAt;

    @PrePersist
    public void onApply() {
        this.appliedAt = LocalDateTime.now();
        this.status = "APPLIED";
    }

    // Getters & Setters
    public Long getId() { return id; }
    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }
    public Long getApplicantId() { return applicantId; }
    public void setApplicantId(Long applicantId) { this.applicantId = applicantId; }
    public String getResumeLink() { return resumeLink; }
    public void setResumeLink(String resumeLink) { this.resumeLink = resumeLink; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}