package com.hirechart.hiredin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Patents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patentName;
    private String publishedSource;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    @JsonIgnore
    private Candidate candidate;

    public Patents() {
        super();
    }

    public Patents(String patentName, String publishedSource) {
        super();
        this.patentName = patentName;
        this.publishedSource = publishedSource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public String getPatentName() {
        return patentName;
    }

    public void setPatentName(String patentName) {
        this.patentName = patentName;
    }

    public String getPatientname() {
        return patentName;
    }

    public void setPatientname(String patientname) {
        this.patentName = patientname;
    }

    public String getPublishedSource() {
        return publishedSource;
    }

    public void setPublishedSource(String publishedSource) {
        this.publishedSource = publishedSource;
    }


    public String getPublishedsource() {
        return publishedSource;
    }

    public void setPublishedsource(String publishedsource) {
        this.publishedSource = publishedsource;
    }
}
