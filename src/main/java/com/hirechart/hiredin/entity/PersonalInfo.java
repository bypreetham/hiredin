package com.hirechart.hiredin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class PersonalInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "candidate_id", unique = true)
  @JsonIgnore
  private Candidate candidate;

  private String name;

  @Column(unique = true)
  private String phone;

  @Column(unique = true)
  private String email;

  private Integer age;

  public PersonalInfo() {
    super();
  }

  public PersonalInfo(String name, String phone, String email, Integer age) {
    super();
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.age = age;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Candidate getCandidate() {
    return candidate;
  }

  public void setCandidate(Candidate candidate) {
    this.candidate = candidate;
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

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}
