package com.hirechart.hiredin.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;
    private String reqSkill;
    private String jobDescription;
    private int numberOfPositions;
    private double salary;
    private String bond;
    private String experience;
    private LocalDate lastDateToApply;
    private String requiredQualification;
    private LocalDate postedDate;
    private String status; // e.g. ACTIVE, EXPIRED, CLOSED
    private String location;
    private String employmentType;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private Company company;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Application> applications;

    public Job() {
        super();
    }

    public Job(String role, String reqSkill, String jobDescription, int numberOfPositions, double salary,
               String bond, String experience, LocalDate lastDateToApply, String requiredQualification,
               Company company) {
        super();
        this.role = role;
        this.reqSkill = reqSkill;
        this.jobDescription = jobDescription;
        this.numberOfPositions = numberOfPositions;
        this.salary = salary;
        this.bond = bond;
        this.experience = experience;
        this.lastDateToApply = lastDateToApply;
        this.requiredQualification = requiredQualification;
        this.company = company;
        this.postedDate = LocalDate.now();
        this.status = "ACTIVE";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Alias for title
    public String getTitle() {
        return role;
    }

    public void setTitle(String title) {
        this.role = title;
    }

    public String getReqSkill() {
        return reqSkill;
    }

    public void setReqSkill(String reqSkill) {
        this.reqSkill = reqSkill;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    // Alias for description
    public String getDescription() {
        return jobDescription;
    }

    public void setDescription(String description) {
        this.jobDescription = description;
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

    public LocalDate getLastDateToApply() {
        return lastDateToApply;
    }

    public void setLastDateToApply(LocalDate lastDateToApply) {
        this.lastDateToApply = lastDateToApply;
    }

    public String getRequiredQualification() {
        return requiredQualification;
    }

    public void setRequiredQualification(String requiredQualification) {
        this.requiredQualification = requiredQualification;
    }

    public LocalDate getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDate postedDate) {
        this.postedDate = postedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
}