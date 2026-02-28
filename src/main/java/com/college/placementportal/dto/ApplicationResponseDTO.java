package com.college.placementportal.dto;

import java.time.LocalDate;

public class ApplicationResponseDTO {

    private Long id;
    private String studentName;
    private String jobTitle;
    private String companyName;
    private String status;
    private LocalDate appliedDate;

    public ApplicationResponseDTO(
            Long id,
            String studentName,
            String jobTitle,
            String companyName,
            String status,
            LocalDate appliedDate) {
        this.id = id;
        this.studentName = studentName;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.status = status;
        this.appliedDate = appliedDate;
    }

    public Long getId() { return id; }
    public String getStudentName() { return studentName; }
    public String getJobTitle() { return jobTitle; }
    public String getCompanyName() { return companyName; }
    public String getStatus() { return status; }
    public LocalDate getAppliedDate() { return appliedDate; }
}