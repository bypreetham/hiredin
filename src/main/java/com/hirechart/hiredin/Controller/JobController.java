package com.hirechart.hiredin.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hirechart.hiredin.Dto.ApplicantDetailDto;
import com.hirechart.hiredin.Dto.JobStatusDto;
import com.hirechart.hiredin.Dto.ResponseStructure;
import com.hirechart.hiredin.entity.Job;
import com.hirechart.hiredin.service.JobService;

@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    // ---- Browse / Search Jobs (Candidate-facing) ----

    /** Get a single job's details */
    @GetMapping("/job/{jobId}")
    public ResponseStructure<Job> getJobById(@PathVariable Long jobId) {
        return jobService.getJobById(jobId);
    }

    /** Get all currently ACTIVE jobs (candidate browse) */
    @GetMapping("/jobs/active")
    public ResponseStructure<List<Job>> getAllActiveJobs() {
        return jobService.getAllActiveJobs();
    }

    // ---- Company Job Management ----

    /** Get all jobs posted by a specific company */
    @GetMapping("/company/{companyId}/jobs")
    public ResponseStructure<List<Job>> getJobsByCompany(@PathVariable Long companyId) {
        return jobService.getJobsByCompany(companyId);
    }

    /** Get only ACTIVE jobs posted by a specific company */
    @GetMapping("/company/{companyId}/jobs/active")
    public ResponseStructure<List<Job>> getActiveJobsByCompany(@PathVariable Long companyId) {
        return jobService.getActiveJobsByCompany(companyId);
    }

    /**
     * Set job status — ACTIVE, INACTIVE, or EXPIRED.
     * Also optionally accepts a new lastDateToApply when reactivating.
     */
    @PutMapping("/job/{jobId}/status")
    public ResponseStructure<Job> setJobStatus(
            @PathVariable Long jobId,
            @RequestBody JobStatusDto jobStatusDto) {
        return jobService.setJobStatus(jobId, jobStatusDto);
    }

    /** Get all applications/applicants for a specific job */
    @GetMapping("/job/{jobId}/applications")
    public ResponseStructure<List<ApplicantDetailDto>> getJobApplications(@PathVariable Long jobId) {
        return jobService.getJobApplications(jobId);
    }
}
