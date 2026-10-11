package com.hirechart.hiredin.Controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hirechart.hiredin.Dto.ApplicantDetailDto;
import com.hirechart.hiredin.Dto.ApplicationResponseDto;
import com.hirechart.hiredin.Dto.CompanyRegisterDto;
import com.hirechart.hiredin.Dto.CreateNewJobDto;
import com.hirechart.hiredin.Dto.InviteCandidateDto;
import com.hirechart.hiredin.Dto.JobRepostDto;
import com.hirechart.hiredin.Dto.JobStatusDto;
import com.hirechart.hiredin.Dto.ResponseStructure;
import com.hirechart.hiredin.entity.Company;
import com.hirechart.hiredin.entity.Job;
import com.hirechart.hiredin.service.ApplicationService;
import com.hirechart.hiredin.service.CompanyService;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/company/register")
    public ResponseStructure<Company> registerCompany(@Valid @RequestBody CompanyRegisterDto companyRegisterDto) {
        return companyService.registerCompany(companyRegisterDto);
    }

    @GetMapping("/company/{companyId}")
    public ResponseStructure<Company> findCompany(@PathVariable Long companyId) {
        return companyService.findCompany(companyId);
    }

    @DeleteMapping("/company/{companyId}")
    public ResponseStructure<String> deleteCompany(@PathVariable Long companyId) {
        return companyService.deleteCompany(companyId);
    }

    @PostMapping("/company/createnewjob")
    public ResponseStructure<Job> createNewJob(@RequestBody CreateNewJobDto createNewJobDto) {
        return companyService.createNewJob(createNewJobDto);
    }

    @PutMapping("/company/repostjob/{jobId}")
    public ResponseStructure<Job> repostJobWithId(
            @PathVariable Long jobId,
            @RequestBody(required = false) JobRepostDto jobRepostDto) {
        return companyService.repostJob(jobId, jobRepostDto);
    }

    @PutMapping("/company/repostjob")
    public ResponseStructure<Job> repostJob(@RequestBody JobRepostDto jobRepostDto) {
        return companyService.repostJob(jobRepostDto.getJobId(), jobRepostDto);
    }

    @GetMapping("/company/job/{jobId}/applicants")
    public ResponseStructure<List<ApplicantDetailDto>> getJobApplicants(
            @PathVariable Long jobId,
            @RequestParam(required = false) Long companyId) {
        return applicationService.getJobApplicants(jobId, companyId);
    }

    @GetMapping("/company/job/{jobId}/applicants/shortlisted")
    public ResponseStructure<List<ApplicantDetailDto>> getJobShortlistedApplicants(
            @PathVariable Long jobId,
            @RequestParam(required = false) Long companyId) {
        return applicationService.getJobShortlistedApplicants(jobId, companyId);
    }

    @PutMapping("/company/application/{applicationId}/status")
    public ResponseStructure<String> updateApplicationStatus(
            @PathVariable Long applicationId,
            @RequestParam String status) {
        return applicationService.updateApplicationStatus(applicationId, status);
    }

    @PostMapping("/company/invite")
    public ResponseStructure<ApplicationResponseDto> sendInviteToCandidate(
            @RequestBody InviteCandidateDto inviteCandidateDto) {
        return companyService.sendInviteToCandidate(inviteCandidateDto);
    }
}

