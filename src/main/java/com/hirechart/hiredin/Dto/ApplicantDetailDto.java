package com.hirechart.hiredin.Dto;

import java.time.LocalDate;
import java.util.List;

import com.hirechart.hiredin.entity.Achievements;
import com.hirechart.hiredin.entity.Certificate;
import com.hirechart.hiredin.entity.Education;
import com.hirechart.hiredin.entity.Experience;
import com.hirechart.hiredin.entity.Patents;
import com.hirechart.hiredin.entity.Project;
import com.hirechart.hiredin.entity.Skill;

public class ApplicantDetailDto {

    private Long applicationId;
    private LocalDate appliedDate;
    private String applicationStatus;

    private Long candidateId;
    private String name;
    private String email;
    private String phone;
    private int age;

    private List<Education> education;
    private List<Experience> experience;
    private List<Skill> skills;
    private List<Achievements> achievements;
    private List<Patents> patents;
    private List<Project> projects;
    private List<Certificate> certificates;

    public ApplicantDetailDto() {
        super();
    }

    public ApplicantDetailDto(Long applicationId, LocalDate appliedDate, String applicationStatus,
                              Long candidateId, String name, String email, String phone, int age,
                              List<Education> education, List<Experience> experience,
                              List<Skill> skills, List<Achievements> achievements, List<Patents> patents,
                              List<Project> projects, List<Certificate> certificates) {
        super();
        this.applicationId = applicationId;
        this.appliedDate = appliedDate;
        this.applicationStatus = applicationStatus;
        this.candidateId = candidateId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.education = education;
        this.experience = experience;
        this.skills = skills;
        this.achievements = achievements;
        this.patents = patents;
        this.projects = projects;
        this.certificates = certificates;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
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

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
