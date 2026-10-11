package com.hirechart.hiredin.Dto;

import java.time.LocalDate;

public class CandidateAppliedJobDto {

    private Long applicationId;
    private Long jobId;
    private String role;
    private String companyName;
    private double salary;
    private String experience;
    private LocalDate lastDateToApply;
    private LocalDate appliedDate;
    private String applicationStatus;

    public CandidateAppliedJobDto() {
        super();
    }

    public CandidateAppliedJobDto(Long applicationId, Long jobId, String role, String companyName,
                                 double salary, String experience, LocalDate lastDateToApply,
                                 LocalDate appliedDate, String applicationStatus) {
        super();
        this.applicationId = applicationId;
        this.jobId = jobId;
        this.role = role;
        this.companyName = companyName;
        this.salary = salary;
        this.experience = experience;
        this.lastDateToApply = lastDateToApply;
        this.appliedDate = appliedDate;
        this.applicationStatus = applicationStatus;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public LocalDate getLastDateToApply() {
        return lastDateToApply;
    }

    public void setLastDateToApply(LocalDate lastDateToApply) {
        this.lastDateToApply = lastDateToApply;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
