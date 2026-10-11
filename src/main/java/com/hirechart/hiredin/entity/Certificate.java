package com.hirechart.hiredin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String issuingOrganization;
    private String issueDate;
    private String certificateUrl;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    @JsonIgnore
    private Candidate candidate;

    public Certificate() {
        super();
    }

    public Certificate(String name, String issuingOrganization, String issueDate, String certificateUrl) {
        super();
        this.name = name;
        this.issuingOrganization = issuingOrganization;
        this.issueDate = issueDate;
        this.certificateUrl = certificateUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssuingOrganization() {
        return issuingOrganization;
    }

    public void setIssuingOrganization(String issuingOrganization) {
        this.issuingOrganization = issuingOrganization;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getCertificateUrl() {
        return certificateUrl;
    }

    public void setCertificateUrl(String certificateUrl) {
        this.certificateUrl = certificateUrl;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
