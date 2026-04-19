package com.college.placementportal.dto;

public class CDCApplicationDTO {

    private String studentName;
    private String jobTitle;
    private String status;
    private String resumePath;
    private Double cgpa;
    private String skills;
    private Long applicationId;
    private String companyName;
private Double salary;
    // Getters & Setters

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getResumePath() { return resumePath; }
    public void setResumePath(String resumePath) { this.resumePath = resumePath; }

    public Double getCgpa() { return cgpa; }
    public void setCgpa(Double cgpa) { this.cgpa = cgpa; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
    public Long getApplicationId() {
    return applicationId;
}

public void setApplicationId(Long applicationId) {
    this.applicationId = applicationId;
}

public String getCompanyName() { return companyName; }
public void setCompanyName(String companyName) { this.companyName = companyName; }

public Double getSalary() { return salary; }
public void setSalary(Double salary) { this.salary = salary; }
}