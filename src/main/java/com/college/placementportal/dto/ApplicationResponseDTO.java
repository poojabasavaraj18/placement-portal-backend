// package com.college.placementportal.dto;

// import java.time.LocalDate;

// public class ApplicationResponseDTO {

//     private Long id;
//     private String studentName;
//     private String jobTitle;
//     private String companyName;
//     private String status;
//     private LocalDate appliedDate;
//     private Long jobId;

//     public ApplicationResponseDTO(
//             Long id,
//             String studentName,
//             String jobTitle,
//             String companyName,
//             String status,
//             Long jobId,
//             LocalDate appliedDate
//         ) {
//         this.id = id;
//         this.studentName = studentName;
//         this.jobTitle = jobTitle;
//         this.companyName = companyName;
//         this.status = status;
//         this.appliedDate = appliedDate;
        
        
        
//         this.jobId = jobId;
//     }

//     public Long getId() { return id; }
//     public String getStudentName() { return studentName; }
//     public String getJobTitle() { return jobTitle; }
//     public String getCompanyName() { return companyName; }
//     public String getStatus() { return status; }
//     public LocalDate getAppliedDate() { return appliedDate; }
// }

package com.college.placementportal.dto;

import com.college.placementportal.entity.ApplicationStatus;

public class ApplicationResponseDTO {

    private Long id;
    private String studentName;
    private String jobTitle;
    private String companyName;
    private ApplicationStatus status;   // ✅ FIXED
    private String resumePath;

    // ✅ CONSTRUCTOR
    public ApplicationResponseDTO(Long id, String jobTitle, String companyName, ApplicationStatus status, String resumePath) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.status = status;
        this.resumePath = resumePath;
    }

    // ✅ GETTERS
    public Long getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public String getResumePath() {
        return resumePath;
    }
}