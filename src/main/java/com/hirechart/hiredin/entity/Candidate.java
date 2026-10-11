package com.hirechart.hiredin.entity;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(mappedBy = "candidate", cascade = CascadeType.ALL)
    private PersonalInfo personalInfo;
    
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Education> education;
    
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Experience> experience;
    
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Skill> skills;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Achievements> achievements;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Patents> patents;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Project> projects;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Certificate> certificates;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Application> applications;

    public Candidate() {
        super();
    }

    public Candidate(
            PersonalInfo personalInfo,
            List<Education> education,
            List<Experience> experience,
            List<Skill> skills,
            List<Achievements> achievements) {

        super();

        this.personalInfo = personalInfo;
        this.education = education;
        this.experience = experience;
        this.skills = skills;
        this.achievements = achievements;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
        if (personalInfo != null) {
            personalInfo.setCandidate(this);
        }
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }

    public List<Experience> getExperience() {
        return experience;
    }

    public void setExperience(List<Experience> experience) {
        this.experience = experience;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Achievements> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievements> achievements) {
        this.achievements = achievements;
    }

    public List<Patents> getPatents() {
        return patents;
    }

    public void setPatents(List<Patents> patents) {
        this.patents = patents;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }
}

