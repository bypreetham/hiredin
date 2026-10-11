package com.hirechart.hiredin.Dto;

public class JobRepostDto {

    private Long jobId;
    private String lastDateToApply;
    private Double salary;
    private Integer numberOfPositions;

    public JobRepostDto() {
        super();
    }

    public JobRepostDto(Long jobId, String lastDateToApply, Double salary, Integer numberOfPositions) {
        super();
        this.jobId = jobId;
        this.lastDateToApply = lastDateToApply;
        this.salary = salary;
        this.numberOfPositions = numberOfPositions;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getLastDateToApply() {
        return lastDateToApply;
    }

    public void setLastDateToApply(String lastDateToApply) {
        this.lastDateToApply = lastDateToApply;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getNumberOfPositions() {
        return numberOfPositions;
    }

    public void setNumberOfPositions(Integer numberOfPositions) {
        this.numberOfPositions = numberOfPositions;
    }
}
