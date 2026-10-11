package com.hirechart.hiredin.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CandidateDto {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format. Email must contain '@' and a valid domain.")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 150, message = "Age cannot exceed 150")
    private int age;

    public CandidateDto() {
        super();
    }

    public CandidateDto(String name, String phone, String email, int age) {
        super();
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}