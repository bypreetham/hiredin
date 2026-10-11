package com.hirechart.hiredin.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String mail;
    private String phone;
    private String address;
    private String type;
    private int numOfEmployees;
    private String description;
    private String website;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Job> jobs;

    public Company() {
        super();
    }

    public Company(String name, String mail, String phone, String address, String type, int numOfEmployees) {
        super();
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.address = address;
        this.type = type;
        this.numOfEmployees = numOfEmployees;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}