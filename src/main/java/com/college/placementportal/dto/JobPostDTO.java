package com.college.placementportal.dto;

public class JobPostDTO {

    private Long id;
    private String title;
    private Double salary;
    private String jobType;
    private String companyName;

    public JobPostDTO(Long id, String title, Double salary, String jobType, String companyName) {
        this.id = id;
        this.title = title;
        this.salary = salary;
        this.jobType = jobType;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getSalary() {
        return salary;
    }

    public String getJobType() {
        return jobType;
    }

    public String getCompanyName() {
        return companyName;
    }
}