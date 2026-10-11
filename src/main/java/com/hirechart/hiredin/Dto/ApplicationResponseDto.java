package com.hirechart.hiredin.Dto;

import java.time.LocalDate;

public class ApplicationResponseDto {

    private Long applicationId;
    private Long candidateId;
    private String candidateName;
    private String candidateEmail;
    private Long jobId;
    private String jobRole;
    private String companyName;
    private LocalDate appliedDate;
    private String status;

    public ApplicationResponseDto() {
        super();
    }

    public ApplicationResponseDto(Long applicationId, Long candidateId, String candidateName,
                                  String candidateEmail, Long jobId, String jobRole,
                                  String companyName, LocalDate appliedDate, String status) {
        super();
        this.applicationId = applicationId;
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.candidateEmail = candidateEmail;
        this.jobId = jobId;
        this.jobRole = jobRole;
        this.companyName = companyName;
        this.appliedDate = appliedDate;
        this.status = status;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
