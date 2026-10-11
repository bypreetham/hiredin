package com.hirechart.hiredin.Dto;

public class EducationDto {

    private String degree;
    private String fieldOfStudy;

    public EducationDto() {
        super();
    }

    public EducationDto(String degree, String fieldOfStudy) {
        super();
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
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
}