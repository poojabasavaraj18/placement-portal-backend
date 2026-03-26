package com.college.placementportal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 💼 Basic Info
    @NotBlank
    private String title;

    @NotBlank
    private String companyName;

    private String companyDescription;

    @NotBlank
    private String location; // Remote / Onsite / Hybrid

    // 💰 Job Details
    @NotNull
    private Double salary;

    @NotBlank
    private String jobType; // Full-time / Internship

    private String bond; // Yes/No + details

    // 🧠 Requirements
    private String skillsRequired;

    private Double minCgpa;

    private String experienceRequired;

    // 📄 Description
    @Column(length = 2000)
    private String jobDescription;

    @Column(length = 2000)
    private String responsibilities;

    @Column(length = 2000)
    private String eligibilityCriteria;

    // ⏰ Extra
    private String deadline;

    private Integer openings;

    // ===== Getters & Setters =====

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getCompanyName() { return companyName; }

    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getCompanyDescription() { return companyDescription; }

    public void setCompanyDescription(String companyDescription) { this.companyDescription = companyDescription; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public Double getSalary() { return salary; }

    public void setSalary(Double salary) { this.salary = salary; }

    public String getJobType() { return jobType; }

    public void setJobType(String jobType) { this.jobType = jobType; }

    public String getBond() { return bond; }

    public void setBond(String bond) { this.bond = bond; }

    public String getSkillsRequired() { return skillsRequired; }

    public void setSkillsRequired(String skillsRequired) { this.skillsRequired = skillsRequired; }

    public Double getMinCgpa() { return minCgpa; }

    public void setMinCgpa(Double minCgpa) { this.minCgpa = minCgpa; }

    public String getExperienceRequired() { return experienceRequired; }

    public void setExperienceRequired(String experienceRequired) { this.experienceRequired = experienceRequired; }

    public String getJobDescription() { return jobDescription; }

    public void setJobDescription(String jobDescription) { this.jobDescription = jobDescription; }

    public String getResponsibilities() { return responsibilities; }

    public void setResponsibilities(String responsibilities) { this.responsibilities = responsibilities; }

    public String getEligibilityCriteria() { return eligibilityCriteria; }

    public void setEligibilityCriteria(String eligibilityCriteria) { this.eligibilityCriteria = eligibilityCriteria; }

    public String getDeadline() { return deadline; }

    public void setDeadline(String deadline) { this.deadline = deadline; }

    public Integer getOpenings() { return openings; }

    public void setOpenings(Integer openings) { this.openings = openings; }
}