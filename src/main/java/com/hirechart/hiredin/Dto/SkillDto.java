package com.hirechart.hiredin.Dto;

import java.util.List;

public class SkillDto {

    private String category;
    private List<String> skills;

    public SkillDto() {
        super();
    }

    public SkillDto(String category, List<String> skills) {
        super();
        this.category = category;
        this.skills = skills;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
