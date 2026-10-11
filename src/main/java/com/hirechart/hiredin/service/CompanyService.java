package com.hirechart.hiredin.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Service;

import com.hirechart.hiredin.Dto.ApplicationResponseDto;
import com.hirechart.hiredin.Dto.CompanyRegisterDto;
import com.hirechart.hiredin.Dto.CreateNewJobDto;
import com.hirechart.hiredin.Dto.InviteCandidateDto;
import com.hirechart.hiredin.Dto.JobRepostDto;
import com.hirechart.hiredin.Dto.ResponseStructure;
import com.hirechart.hiredin.entity.Application;
import com.hirechart.hiredin.entity.Candidate;
import com.hirechart.hiredin.entity.Company;
import com.hirechart.hiredin.entity.Job;
import com.hirechart.hiredin.exception.CandidateNotFound;
import com.hirechart.hiredin.exception.CompanyNotFoundException;
import com.hirechart.hiredin.repository.ApplicationRepository;
import com.hirechart.hiredin.repository.CandidateRepository;
import com.hirechart.hiredin.repository.CompanyRepository;
import com.hirechart.hiredin.repository.JobRepository;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;
    private final CandidateRepository candidateRepository;
    private final ApplicationRepository applicationRepository;

    public CompanyService(
            CompanyRepository companyRepository,
            JobRepository jobRepository,
            CandidateRepository candidateRepository,
            ApplicationRepository applicationRepository) {
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
        this.candidateRepository = candidateRepository;
        this.applicationRepository = applicationRepository;
    }

    public ResponseStructure<Company> registerCompany(CompanyRegisterDto dto) {
        Company company = new Company();
        company.setName(dto.getName());
        company.setMail(dto.getMail());
        company.setPhone(dto.getPhone());
        company.setAddress(dto.getAddress());
        company.setType(dto.getType());
        company.setNumOfEmployees(dto.getNumOfEmployees());

        Company savedCompany = companyRepository.save(company);

        ResponseStructure<Company> response = new ResponseStructure<>();
        response.setStatuscode(201);
        response.setMessage("Company registered successfully");
        response.setData(savedCompany);
        return response;
    }

    public ResponseStructure<Company> findCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + companyId + " not found"));

        ResponseStructure<Company> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Company found successfully");
        response.setData(company);
        return response;
    }

    public ResponseStructure<String> deleteCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + companyId + " not found"));

        companyRepository.delete(company);

        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Company deleted successfully");
        response.setData("Company " + companyId + " deleted");
        return response;
    }

    public ResponseStructure<Job> createNewJob(CreateNewJobDto dto) {
        Company company = companyRepository.findById(dto.getCompid())
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + dto.getCompid() + " not found"));

        Job job = new Job();
        job.setRole(dto.getRole());
        job.setReqSkill(dto.getReqskill());
        job.setJobDescription(dto.getJobDescription());
        job.setNumberOfPositions(dto.getNumberOfPositions());
        job.setSalary(dto.getSalary());
        job.setBond(dto.getBond());
        job.setExperience(dto.getExperience());
        job.setRequiredQualification(dto.getRequiredQualification());
        job.setPostedDate(LocalDate.now());
        job.setStatus("ACTIVE");
        job.setCompany(company);

        if (dto.getLastDateToApply() != null && !dto.getLastDateToApply().isBlank()) {
            try {
                job.setLastDateToApply(LocalDate.parse(dto.getLastDateToApply()));
            } catch (DateTimeParseException e) {
                job.setLastDateToApply(LocalDate.now().plusDays(30));
            }
        }

        Job savedJob = jobRepository.save(job);

        ResponseStructure<Job> response = new ResponseStructure<>();
        response.setStatuscode(201);
        response.setMessage("Job created successfully");
        response.setData(savedJob);
        return response;
    }

    public ResponseStructure<Job> repostJob(Long jobId, JobRepostDto dto) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job with id " + jobId + " not found"));

        if (dto != null) {
            if (dto.getLastDateToApply() != null && !dto.getLastDateToApply().isBlank()) {
                try {
                    job.setLastDateToApply(LocalDate.parse(dto.getLastDateToApply()));
                } catch (DateTimeParseException e) {
                    job.setLastDateToApply(LocalDate.now().plusDays(30));
                }
            } else {
                job.setLastDateToApply(LocalDate.now().plusDays(30));
            }

            if (dto.getSalary() != null) {
                job.setSalary(dto.getSalary());
            }

            if (dto.getNumberOfPositions() != null) {
                job.setNumberOfPositions(dto.getNumberOfPositions());
            }
        } else {
            job.setLastDateToApply(LocalDate.now().plusDays(30));
        }

        job.setPostedDate(LocalDate.now());
        job.setStatus("ACTIVE");

        Job updatedJob = jobRepository.save(job);

        ResponseStructure<Job> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Job reposted successfully");
        response.setData(updatedJob);
        return response;
    }

    public ResponseStructure<ApplicationResponseDto> sendInviteToCandidate(InviteCandidateDto dto) {
        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + dto.getCompanyId() + " not found"));

        Candidate candidate = candidateRepository.findById(dto.getCandidateId())
                .orElseThrow(() -> new CandidateNotFound("Candidate with id " + dto.getCandidateId() + " not found"));

        Job job = jobRepository.findById(dto.getJobId())
                .orElseThrow(() -> new RuntimeException("Job with id " + dto.getJobId() + " not found"));

        if (job.getCompany() == null || !job.getCompany().getId().equals(company.getId())) {
            throw new IllegalArgumentException("Job id " + dto.getJobId() + " does not belong to company id " + dto.getCompanyId());
        }

        Application application = applicationRepository.findByCandidateIdAndJobId(candidate.getId(), job.getId())
                .orElse(new Application(job, candidate));

        application.setStatus("INVITED");
        application.setAppliedDate(LocalDate.now());
        Application savedApp = applicationRepository.save(application);

        String candidateName = candidate.getPersonalInfo() != null ? candidate.getPersonalInfo().getName() : null;
        String candidateEmail = candidate.getPersonalInfo() != null ? candidate.getPersonalInfo().getEmail() : null;

        ApplicationResponseDto responseDto = new ApplicationResponseDto(
                savedApp.getId(),
                candidate.getId(),
                candidateName,
                candidateEmail,
                job.getId(),
                job.getRole(),
                company.getName(),
                savedApp.getAppliedDate(),
                savedApp.getStatus()
        );

        ResponseStructure<ApplicationResponseDto> response = new ResponseStructure<>();
        response.setStatuscode(200);
        response.setMessage("Invitation sent to candidate successfully" + (dto.getMessage() != null ? ": " + dto.getMessage() : ""));
        response.setData(responseDto);
        return response;
    }
}
