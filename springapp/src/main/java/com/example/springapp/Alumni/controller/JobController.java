package com.example.springapp.Alumni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import java.util.List;

import com.example.springapp.Alumni.model.*;
import com.example.springapp.Alumni.service.JobService;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin("*")
public class JobController {

    @Autowired
    private JobService service;

    // CREATE
    @PostMapping
    public Job create(@RequestBody Job job) {
        return service.createJob(job);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Job update(@PathVariable Long id,
                      @RequestBody Job job) {
        return service.updateJob(id, job);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteJob(id);
        return "Job deleted";
    }

    // FETCH OPEN JOBS
    @GetMapping
    public Page<Job> openJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return service.getOpenJobs(page, size);
    }

    // APPLY
    @PostMapping("/apply")
    public String apply(@RequestBody JobApplication application) {
        return service.applyJob(application);
    }

    // TRACK APPLICATIONS
    @GetMapping("/applications/{jobId}")
    public List<JobApplication> applications(@PathVariable Long jobId) {
        return service.getApplications(jobId);
    }

    // REFER
    @PostMapping("/refer")
    public JobReferral refer(@RequestBody JobReferral referral) {
        return service.referStudent(referral);
    }

    // CLOSE JOB
    @PutMapping("/close/{jobId}")
    public String close(@PathVariable Long jobId) {
        return service.closeJob(jobId);
    }
}