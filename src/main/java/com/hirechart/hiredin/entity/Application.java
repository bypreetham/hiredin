package com.hirechart.hiredin.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    @JsonIgnore
    private Job job;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    private LocalDate appliedDate;
    private String status; // APPLIED, REVIEWED, SHORTLISTED, REJECTED, HIRED

    public Application() {
        super();
        this.appliedDate = LocalDate.now();
        this.status = "APPLIED";
    }

    public Application(Job job, Candidate candidate) {
        super();
        this.job = job;
        this.candidate = candidate;
        this.appliedDate = LocalDate.now();
        this.status = "APPLIED";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
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
