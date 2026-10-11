package com.hirechart.hiredin.Dto;

public class ExperienceDto {

    private String companyName;
    private String role;
    private int yearsOfExperience;

    public ExperienceDto() {
        super();
    }

    public ExperienceDto(String companyName, String role, int yearsOfExperience) {
        super();
        this.companyName = companyName;
        this.role = role;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
