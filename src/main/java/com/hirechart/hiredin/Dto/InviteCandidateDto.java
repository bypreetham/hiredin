package com.hirechart.hiredin.Dto;

import jakarta.validation.constraints.NotNull;

public class InviteCandidateDto {

    @NotNull(message = "Company ID is required")
    private Long companyId;

    @NotNull(message = "Candidate ID is required")
    private Long candidateId;

    @NotNull(message = "Job ID is required")
    private Long jobId;

    private String message;

    public InviteCandidateDto() {
        super();
    }

    public InviteCandidateDto(Long companyId, Long candidateId, Long jobId, String message) {
        super();
        this.companyId = companyId;
        this.candidateId = candidateId;
        this.jobId = jobId;
        this.message = message;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
