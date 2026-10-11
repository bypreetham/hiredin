package com.hirechart.hiredin.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hirechart.hiredin.Dto.ApplicantDetailDto;
import com.hirechart.hiredin.Dto.ApplicationResponseDto;
import com.hirechart.hiredin.Dto.CandidateAppliedJobDto;
import com.hirechart.hiredin.Dto.ResponseStructure;
import com.hirechart.hiredin.entity.Application;
import com.hirechart.hiredin.entity.Candidate;
import com.hirechart.hiredin.entity.Job;
import com.hirechart.hiredin.entity.PersonalInfo;
import com.hirechart.hiredin.exception.CandidateNotFound;
import com.hirechart.hiredin.repository.ApplicationRepository;
import com.hirechart.hiredin.repository.CandidateRepository;
import com.hirechart.hiredin.repository.JobRepository;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              CandidateRepository candidateRepository,
                              JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.candidateRepository = candidateRepository;
        this.jobRepository = jobRepository;
    }

    public ResponseStructure<ApplicationResponseDto> applyJob(Long candidateId, Long jobId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job with id " + jobId + " not found"));

        if (!"ACTIVE".equalsIgnoreCase(job.getStatus())) {
            throw new IllegalStateException("Job is not currently active for applications");
        }

        if (job.getLastDateToApply() != null && LocalDate.now().isAfter(job.getLastDateToApply())) {
            throw new IllegalStateException("Job application deadline has passed on: " + job.getLastDateToApply());
        }

        if (applicationRepository.existsByCandidateIdAndJobId(candidateId, jobId)) {
            throw new IllegalStateException("Candidate has already applied for this job");
        }

        Application application = new Application(job, candidate);
        Application savedApplication = applicationRepository.save(application);

        String candidateName = null;
        String candidateEmail = null;
        if (candidate.getPersonalInfo() != null) {
            candidateName = candidate.getPersonalInfo().getName();
            candidateEmail = candidate.getPersonalInfo().getEmail();
        }

        String companyName = null;
        if (job.getCompany() != null) {
            companyName = job.getCompany().getName();
        }

        ApplicationResponseDto responseDto = new ApplicationResponseDto(
                savedApplication.getId(),
                candidate.getId(),
                candidateName,
                candidateEmail,
                job.getId(),
                job.getRole(),
                companyName,
                savedApplication.getAppliedDate(),
                savedApplication.getStatus()
        );

        ResponseStructure<ApplicationResponseDto> response = new ResponseStructure<>();
        response.setStatuscode(201);
        response.setMessage("Job applied successfully");
        response.setData(responseDto);
        return response;
    }

    public ResponseStructure<String> revokeApplication(Long candidateId, Long applicationId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application with id " + applicationId + " not found"));

        if (application.getCandidate() == null || !application.getCandidate().getId().equals(candidate.getId())) {
            throw new IllegalArgumentException("Application " + applicationId + " does not belong to candidate id: " + candidateId);
        }

        applicationRepository.delete(application);

        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Application revoked successfully");
        response.setData("Application " + applicationId + " has been revoked");
        return response;
    }

    public ResponseStructure<List<CandidateAppliedJobDto>> getCandidateApplications(Long candidateId) {
        candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        List<Application> applications = applicationRepository.findByCandidateId(candidateId);
        List<CandidateAppliedJobDto> appliedJobs = mapToCandidateAppliedJobDtos(applications);

        ResponseStructure<List<CandidateAppliedJobDto>> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Retrieved applied jobs successfully");
        response.setData(appliedJobs);
        return response;
    }

    public ResponseStructure<List<CandidateAppliedJobDto>> getCandidateShortlistedApplications(Long candidateId) {
        candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        List<Application> applications = applicationRepository.findByCandidateIdAndStatus(candidateId, "SHORTLISTED");
        List<CandidateAppliedJobDto> appliedJobs = mapToCandidateAppliedJobDtos(applications);

        ResponseStructure<List<CandidateAppliedJobDto>> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Retrieved shortlisted applications successfully");
        response.setData(appliedJobs);
        return response;
    }

    public ResponseStructure<List<CandidateAppliedJobDto>> getCandidateRejectedApplications(Long candidateId) {
        candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + candidateId + " not found"));

        List<Application> applications = applicationRepository.findByCandidateIdAndStatus(candidateId, "REJECTED");
        List<CandidateAppliedJobDto> appliedJobs = mapToCandidateAppliedJobDtos(applications);

        ResponseStructure<List<CandidateAppliedJobDto>> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Retrieved rejected applications successfully");
        response.setData(appliedJobs);
        return response;
    }

    public ResponseStructure<List<ApplicantDetailDto>> getJobApplicants(Long jobId, Long companyId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job with id " + jobId + " not found"));

        if (companyId != null) {
            if (job.getCompany() == null || !companyId.equals(job.getCompany().getId())) {
                throw new IllegalArgumentException("Job with id " + jobId + " does not belong to company id: " + companyId);
            }
        }

        List<Application> applications = applicationRepository.findByJobId(jobId);
        List<ApplicantDetailDto> applicants = toApplicantDetailDtos(applications);

        ResponseStructure<List<ApplicantDetailDto>> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Retrieved applicants successfully");
        response.setData(applicants);
        return response;
    }

    public ResponseStructure<List<ApplicantDetailDto>> getJobShortlistedApplicants(Long jobId, Long companyId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job with id " + jobId + " not found"));

        if (companyId != null) {
            if (job.getCompany() == null || !companyId.equals(job.getCompany().getId())) {
                throw new IllegalArgumentException("Job with id " + jobId + " does not belong to company id: " + companyId);
            }
        }

        List<Application> applications = applicationRepository.findByJobIdAndStatus(jobId, "SHORTLISTED");
        List<ApplicantDetailDto> applicants = toApplicantDetailDtos(applications);

        ResponseStructure<List<ApplicantDetailDto>> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Retrieved shortlisted applicants successfully");
        response.setData(applicants);
        return response;
    }

    public ResponseStructure<String> updateApplicationStatus(Long applicationId, String status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application with id " + applicationId + " not found"));

        application.setStatus(status);
        applicationRepository.save(application);

        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Application status updated successfully");
        response.setData("Application " + applicationId + " status set to " + status);
        return response;
    }

    private List<CandidateAppliedJobDto> mapToCandidateAppliedJobDtos(List<Application> applications) {
        List<CandidateAppliedJobDto> appliedJobs = new ArrayList<>();
        for (Application app : applications) {
            Job job = app.getJob();
            String companyName = (job != null && job.getCompany() != null) ? job.getCompany().getName() : null;

            CandidateAppliedJobDto dto = new CandidateAppliedJobDto(
                    app.getId(),
                    job != null ? job.getId() : null,
                    job != null ? job.getRole() : null,
                    companyName,
                    job != null ? job.getSalary() : 0.0,
                    job != null ? job.getExperience() : null,
                    job != null ? job.getLastDateToApply() : null,
                    app.getAppliedDate(),
                    app.getStatus()
            );
            appliedJobs.add(dto);
        }
        return appliedJobs;
    }

    public List<ApplicantDetailDto> toApplicantDetailDtos(List<Application> applications) {
        List<ApplicantDetailDto> applicants = new ArrayList<>();
        for (Application app : applications) {
            Candidate candidate = app.getCandidate();
            ApplicantDetailDto dto = new ApplicantDetailDto();
            dto.setApplicationId(app.getId());
            dto.setAppliedDate(app.getAppliedDate());
            dto.setApplicationStatus(app.getStatus());

            if (candidate != null) {
                dto.setCandidateId(candidate.getId());
                PersonalInfo personalInfo = candidate.getPersonalInfo();
                if (personalInfo != null) {
                    dto.setName(personalInfo.getName());
                    dto.setEmail(personalInfo.getEmail());
                    dto.setPhone(personalInfo.getPhone());
                    dto.setAge(personalInfo.getAge());
                }
                dto.setEducation(candidate.getEducation());
                dto.setExperience(candidate.getExperience());
                dto.setSkills(candidate.getSkills());
                dto.setAchievements(candidate.getAchievements());
                dto.setPatents(candidate.getPatents());
                dto.setProjects(candidate.getProjects());
                dto.setCertificates(candidate.getCertificates());
            }
            applicants.add(dto);
        }
        return applicants;
    }
}
