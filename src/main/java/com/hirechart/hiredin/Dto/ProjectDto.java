package com.hirechart.hiredin.Dto;

public class ProjectDto {

    private String title;
    private String description;
    private String technologiesUsed;
    private String projectUrl;

    public ProjectDto() {
        super();
    }

    public ProjectDto(String title, String description, String technologiesUsed, String projectUrl) {
        super();
        this.title = title;
        this.description = description;
        this.technologiesUsed = technologiesUsed;
        this.projectUrl = projectUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnologiesUsed() {
        return technologiesUsed;
    }

    public void setTechnologiesUsed(String technologiesUsed) {
        this.technologiesUsed = technologiesUsed;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }
}
