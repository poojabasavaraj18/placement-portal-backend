// package com.college.placementportal.service;

// import com.college.placementportal.entity.Application;
// import com.college.placementportal.entity.ApplicationStatus;
// import com.college.placementportal.repository.ApplicationRepository;
// import com.college.placementportal.dto.ApplicationResponseDTO;

// import org.springframework.stereotype.Service;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.PageImpl;

// import java.util.List;
// import java.util.stream.Collectors;

// @Service
// public class ApplicationService {

//     private final ApplicationRepository applicationRepository;

//     public ApplicationService(ApplicationRepository applicationRepository) {
//         this.applicationRepository = applicationRepository;
//     }

//     // ✅ Apply for job
//     public Application apply(Application application) {
//         return applicationRepository.save(application);
//     }

//     // ✅ Get all applications
//     public List<Application> getAllApplications() {
//         return applicationRepository.findAll();
//     }

//     // ✅ Get applications by student (PAGINATED)
//     public Page<ApplicationResponseDTO> getApplicationsByStudent(Long studentId, Pageable pageable) {

//         Page<Application> applications = applicationRepository.findByStudent_Id(studentId, pageable);

//         List<ApplicationResponseDTO> dtoList = applications.stream()
//                 .map(app -> new ApplicationResponseDTO(
//                         app.getId(),
//                         app.getStudent().getName(),
//                         app.getJobPost().getTitle(),
//                         app.getJobPost().getCompanyName(), // ✅ FIXED
//                         app.getStatus().name(),
//                         app.getAppliedDate()))
//                 .toList();

//         return new PageImpl<>(dtoList, pageable, applications.getTotalElements());
//     }

//     // ✅ Get applications for a job (for recruiter)
// {
//         return applicationRepository.findByJobPost_Id(jobId)
//                 .stream()
//                 .map(app -> new ApplicationResponseDTO(
//                         app.getId(),
//                         app.getStudent().getName(),
//                         app.getJobPost().getTitle(),
//                         app.getJobPost().getCompanyName(), // ✅ FIXED
//                         app.getStatus().name(),
//                         app.getAppliedDate()))
//                 .collect(Collectors.toList());
//     }

//     // ✅ Update status (rounds, accept, reject)
//     public Application updateStatus(Long applicationId, ApplicationStatus status) {
//         Application application = applicationRepository.findById(applicationId)
//                 .orElseThrow(() -> new RuntimeException("Application not found"));

//         application.setStatus(status);
//         return applicationRepository.save(application);
//     }
// }
package com.college.placementportal.service;

import com.college.placementportal.entity.Application;
import com.college.placementportal.entity.ApplicationStatus;
import com.college.placementportal.repository.ApplicationRepository;
import com.college.placementportal.dto.ApplicationResponseDTO;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    // ✅ APPLY
    public Application apply(Application application) {
        return applicationRepository.save(application);
    }

    // ✅ ALL APPLICATIONS (optional)
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // 🔹 STUDENT VIEW (KEEP DTO)
    public Page<ApplicationResponseDTO> getApplicationsByStudent(Long studentId, Pageable pageable) {

        Page<Application> applications = applicationRepository.findByStudent_Id(studentId, pageable);

        List<ApplicationResponseDTO> dtoList = applications.stream()
                .map(app -> new ApplicationResponseDTO(
                        app.getId(),
                        app.getStudent().getName(),
                        app.getJobPost().getTitle(),
                        app.getJobPost().getCompanyName(),
                        app.getStatus().name(),
                        app.getAppliedDate()))
                .toList();

        return new PageImpl<>(dtoList, pageable, applications.getTotalElements());
    }

    // 🔥 RECRUITER VIEW (FULL DATA — IMPORTANT)
    public List<Application> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobPost_Id(jobId);
    }

    // 🔥 UPDATE STATUS
    public Application updateStatus(Long applicationId, ApplicationStatus status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setStatus(status);
        return applicationRepository.save(application);
    }
}