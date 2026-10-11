package com.hirechart.hiredin.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hirechart.hiredin.Dto.ApplicantDetailDto;
import com.hirechart.hiredin.Dto.JobStatusDto;
import com.hirechart.hiredin.Dto.ResponseStructure;
import com.hirechart.hiredin.entity.Application;
import com.hirechart.hiredin.entity.Job;
import com.hirechart.hiredin.exception.CompanyNotFoundException;
import com.hirechart.hiredin.repository.ApplicationRepository;
import com.hirechart.hiredin.repository.CompanyRepository;
import com.hirechart.hiredin.repository.JobRepository;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final ApplicationRepository applicationRepository;
    private final ApplicationService applicationService;

    public JobService(
            JobRepository jobRepository,
            CompanyRepository companyRepository,
            ApplicationRepository applicationRepository,
            ApplicationService applicationService) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.applicationRepository = applicationRepository;
        this.applicationService = applicationService;
    }

    // ---- Get a single job by ID ----
    public ResponseStructure<Job> getJobById(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job with id " + jobId + " not found"));

        ResponseStructure<Job> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Job retrieved successfully");
        response.setData(job);
        return response;
    }

    // ---- Get all ACTIVE jobs (candidate browse) ----
    public ResponseStructure<List<Job>> getAllActiveJobs() {
        List<Job> jobs = jobRepository.findByStatusIgnoreCase("ACTIVE");

        ResponseStructure<List<Job>> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Active jobs retrieved successfully");
        response.setData(jobs);
        return response;
    }

    // ---- Get all jobs posted by a company ----
    public ResponseStructure<List<Job>> getJobsByCompany(Long companyId) {
        companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + companyId + " not found"));

        List<Job> jobs = jobRepository.findByCompanyId(companyId);

        ResponseStructure<List<Job>> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Jobs for company " + companyId + " retrieved successfully");
        response.setData(jobs);
        return response;
    }

    // ---- Get active jobs posted by a company ----
    public ResponseStructure<List<Job>> getActiveJobsByCompany(Long companyId) {
        companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + companyId + " not found"));

        List<Job> jobs = jobRepository.findByCompanyIdAndStatusIgnoreCase(companyId, "ACTIVE");

        ResponseStructure<List<Job>> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Active jobs for company " + companyId + " retrieved successfully");
        response.setData(jobs);
        return response;
    }

    // ---- Set job status (ACTIVE, INACTIVE, EXPIRED) ----
    public ResponseStructure<Job> setJobStatus(Long jobId, JobStatusDto jobStatusDto) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job with id " + jobId + " not found"));

        String status = jobStatusDto.getStatus();
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status must not be blank. Use ACTIVE, INACTIVE, or EXPIRED.");
        }

        String normalizedStatus = status.toUpperCase();
        if (!normalizedStatus.equals("ACTIVE") && !normalizedStatus.equals("INACTIVE") && !normalizedStatus.equals("EXPIRED")) {
            throw new IllegalArgumentException("Invalid status: " + status + ". Must be ACTIVE, INACTIVE, or EXPIRED.");
        }

        job.setStatus(normalizedStatus);

        // Optionally update last date to apply when reactivating
        if (normalizedStatus.equals("ACTIVE") && jobStatusDto.getLastDateToApply() != null
                && !jobStatusDto.getLastDateToApply().isBlank()) {
            try {
                job.setLastDateToApply(LocalDate.parse(jobStatusDto.getLastDateToApply()));
            } catch (DateTimeParseException e) {
                job.setLastDateToApply(LocalDate.now().plusDays(30));
            }
        }

        Job updatedJob = jobRepository.save(job);

        ResponseStructure<Job> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Job status updated to " + normalizedStatus);
        response.setData(updatedJob);
        return response;
    }

    // ---- Get all applicants of a job (delegates to ApplicationService) ----
    public ResponseStructure<List<ApplicantDetailDto>> getJobApplications(Long jobId) {
        jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job with id " + jobId + " not found"));

        List<Application> applications = applicationRepository.findByJobId(jobId);

        ResponseStructure<List<ApplicantDetailDto>> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Applications for job " + jobId + " retrieved successfully");
        response.setData(applicationService.toApplicantDetailDtos(applications));
        return response;
    }
}
