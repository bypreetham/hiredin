package com.hirechart.hiredin.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CompanyRegisterDto {

    @NotBlank(message = "Company name cannot be blank")
    private String name;

    @NotBlank(message = "Company mail is required")
    @Email(message = "Invalid company email format")
    private String mail;

    @NotBlank(message = "Company phone is required")
    private String phone;

    private String address;
    private String type;
    private int numOfEmployees;

    public CompanyRegisterDto() {
        super();
    }

    public CompanyRegisterDto(String name, String mail, String phone, String address, String type, int numOfEmployees) {
        super();
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.address = address;
        this.type = type;
        this.numOfEmployees = numOfEmployees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumOfEmployees() {
        return numOfEmployees;
    }

    public void setNumOfEmployees(int numOfEmployees) {
        this.numOfEmployees = numOfEmployees;
    }
}
