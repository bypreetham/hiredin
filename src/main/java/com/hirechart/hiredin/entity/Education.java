package com.hirechart.hiredin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Education {
	
	@ManyToOne
    @JoinColumn(name = "candidate_id")
    @JsonIgnore
	private Candidate candidate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String degree;
    private String fieldOfStudy;

    public Education() {
        super();
    }

    public Education(String degree, String fieldOfStudy) {
        super();
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
    }

    public Long getId() {
        return id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}