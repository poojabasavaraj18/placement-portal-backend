package com.college.placementportal.service;

import com.college.placementportal.entity.Application;
import com.college.placementportal.entity.ApplicationStatus;
import com.college.placementportal.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Application> getApplicationsByStudent(Long studentId) {
        return applicationRepository.findByStudentId(studentId);
    }

    public List<Application> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobPostId(jobId);
    }

    public Application updateStatus(Long applicationId, ApplicationStatus status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setStatus(status);
        return applicationRepository.save(application);
    }
}