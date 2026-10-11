package com.hirechart.hiredin.Dto;

public class CreateNewJobDto {

    private Long compid;
    private String role;
    private String reqskill;
    private String jobDescription;
    private int numberOfPositions;
    private double salary;
    private String bond;
    private String experience;
    private String lastDateToApply;
    private String requiredQualification;

    public CreateNewJobDto() {
        super();
    }

    public CreateNewJobDto(Long compid, String role, String reqskill, String jobDescription,
                           int numberOfPositions, double salary, String bond, String experience,
                           String lastDateToApply, String requiredQualification) {
        super();
        this.compid = compid;
        this.role = role;
        this.reqskill = reqskill;
        this.jobDescription = jobDescription;
        this.numberOfPositions = numberOfPositions;
        this.salary = salary;
        this.bond = bond;
        this.experience = experience;
        this.lastDateToApply = lastDateToApply;
        this.requiredQualification = requiredQualification;
    }

    public Long getCompid() {
        return compid;
    }

    public void setCompid(Long compid) {
        this.compid = compid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getReqskill() {
        return reqskill;
    }

    public void setReqskill(String reqskill) {
        this.reqskill = reqskill;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public int getNumberOfPositions() {
        return numberOfPositions;
    }

    public void setNumberOfPositions(int numberOfPositions) {
        this.numberOfPositions = numberOfPositions;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getBond() {
        return bond;
    }

    public void setBond(String bond) {
        this.bond = bond;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getLastDateToApply() {
        return lastDateToApply;
    }

    public void setLastDateToApply(String lastDateToApply) {
        this.lastDateToApply = lastDateToApply;
    }

    public String getRequiredQualification() {
        return requiredQualification;
    }

    public void setRequiredQualification(String requiredQualification) {
        this.requiredQualification = requiredQualification;
    }
}
