package com.example.springapp.Alumni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

import java.util.List;

import com.example.springapp.Alumni.model.*;
import com.example.springapp.Alumni.repository.*;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepo;

    @Autowired
    private JobApplicationRepository appRepo;

    @Autowired
    private JobReferralRepository referralRepo;

    // CREATE JOB
    public Job createJob(Job job) {
        return jobRepo.save(job);
    }

    // UPDATE JOB
    public Job updateJob(Long id, Job updated) {
        Job job = jobRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        job.setTitle(updated.getTitle());
        job.setCompany(updated.getCompany());
        job.setLocation(updated.getLocation());
        job.setDescription(updated.getDescription());
        job.setJobType(updated.getJobType());
        job.setStatus(updated.getStatus());

        return jobRepo.save(job);
    }

    // DELETE JOB
    public void deleteJob(Long id) {
        jobRepo.deleteById(id);
    }

    // FILTER JOBS (Open only + pagination)
    public Page<Job> getOpenJobs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by("createdAt").descending());
        return jobRepo.findByStatus("OPEN", pageable);
    }

    // APPLY JOB
    public String applyJob(JobApplication application) {

        if (appRepo.existsByJobIdAndApplicantId(
                application.getJobId(),
                application.getApplicantId())) {
            return "You already applied for this job";
        }

        appRepo.save(application);
        return "Application submitted successfully";
    }

    // TRACK APPLICATIONS (For job owner)
    public List<JobApplication> getApplications(Long jobId) {
        return appRepo.findByJobId(jobId);
    }

    // REFER STUDENT
    public JobReferral referStudent(JobReferral referral) {
        return referralRepo.save(referral);
    }

    // CLOSE JOB
    public String closeJob(Long jobId) {
        Job job = jobRepo.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        job.setStatus("CLOSED");
        jobRepo.save(job);
        return "Job closed successfully";
    }
}