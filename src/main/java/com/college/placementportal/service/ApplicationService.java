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

    public Application apply(Application application) {
        return applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // 🔹 PAGINATED DTO for student
    public Page<ApplicationResponseDTO> getApplicationsByStudent(Long studentId, Pageable pageable) {

        Page<Application> applications =
                applicationRepository.findByStudentId(studentId, pageable);

        List<ApplicationResponseDTO> dtoList =
                applications.stream()
                        .map(app -> new ApplicationResponseDTO(
                                app.getId(),
                                app.getStudent().getName(),
                                app.getJobPost().getTitle(),
                                app.getJobPost().getCompany().getName(),
                                app.getStatus().name(),
                                app.getAppliedDate()
                        ))
                        .toList();

        return new PageImpl<>(dtoList, pageable, applications.getTotalElements());
    }

    // 🔹 DTO for job applicants
    public List<ApplicationResponseDTO> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobPostId(jobId)
                .stream()
                .map(app -> new ApplicationResponseDTO(
                        app.getId(),
                        app.getStudent().getName(),
                        app.getJobPost().getTitle(),
                        app.getJobPost().getCompany().getName(),
                        app.getStatus().name(),
                        app.getAppliedDate()
                ))
                .collect(Collectors.toList());
    }

    public Application updateStatus(Long applicationId, ApplicationStatus status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setStatus(status);
        return applicationRepository.save(application);
    }
}