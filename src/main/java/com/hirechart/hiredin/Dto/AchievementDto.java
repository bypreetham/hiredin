package com.hirechart.hiredin.Dto;

public class AchievementDto {

    private String description;

    public AchievementDto() {
        super();
    }

    public AchievementDto(String description) {
        super();
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
