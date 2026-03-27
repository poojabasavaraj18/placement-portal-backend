package com.college.placementportal.service;

import com.college.placementportal.entity.Application;
import com.college.placementportal.entity.ApplicationStatus;
import com.college.placementportal.repository.ApplicationRepository;
import com.college.placementportal.dto.ApplicationResponseDTO;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    // 🔥 APPLY WITH FILE UPLOAD (UPDATED)
    public Application apply(Application application, MultipartFile file) throws IOException {

        // ✅ Create uploads folder dynamically
        String uploadDir = System.getProperty("user.dir") + "/uploads/";

        File folder = new File(uploadDir);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // ✅ Clean filename
        String fileName = System.currentTimeMillis() + "_" +
                file.getOriginalFilename().replaceAll("\\s+", "_");

        // ✅ Save file
        String filePath = uploadDir + fileName;
        file.transferTo(new File(filePath));

        // ✅ Save only filename in DB
        application.setResumePath(fileName);
        application.setStatus(ApplicationStatus.APPLIED);

        return applicationRepository.save(application);
    }

    // ✅ ALL APPLICATIONS
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // 🔹 STUDENT VIEW
    public Page<ApplicationResponseDTO> getApplicationsByStudent(Long studentId, Pageable pageable) {

        Page<Application> applications = applicationRepository.findByStudent_Id(studentId, pageable);

        List<ApplicationResponseDTO> dtoList = applications.stream()
                .map(app -> new ApplicationResponseDTO(
                        app.getId(),
                        app.getStudent().getName(),
                        app.getJobPost().getTitle(),
                        app.getJobPost().getCompanyName(),
                        app.getStatus().name(),
                        app.getAppliedDate(),
                     app.getJobPost().getId()))
                .toList();

        return new PageImpl<>(dtoList, pageable, applications.getTotalElements());
    }

    // 🔥 RECRUITER VIEW
    public List<Application> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobPost_Id(jobId);
    }

    // 🔥 UPDATE STATUS
   public Application updateStatus(Long applicationId, ApplicationStatus status) {

    Application application = applicationRepository.findById(applicationId)
            .orElseThrow(() -> new RuntimeException("Application not found"));

    ApplicationStatus currentStatus = application.getStatus();

    // 🚨 Prevent invalid transitions
    if (currentStatus == ApplicationStatus.REJECTED || currentStatus == ApplicationStatus.SELECTED) {
        throw new RuntimeException("Cannot update status after final decision");
    }

    // 🚨 Optional: enforce proper flow
    switch (status) {
        case ROUND1:
            if (currentStatus != ApplicationStatus.APPLIED) {
                throw new RuntimeException("Must be APPLIED to move to ROUND1");
            }
            break;

        case ROUND2:
            if (currentStatus != ApplicationStatus.ROUND1) {
                throw new RuntimeException("Must complete ROUND1 first");
            }
            break;

        case HR:
            if (currentStatus != ApplicationStatus.ROUND2) {
                throw new RuntimeException("Must complete ROUND2 first");
            }
            break;

        case SELECTED:
        case REJECTED:
            if (currentStatus != ApplicationStatus.HR) {
                throw new RuntimeException("Final decision only after HR round");
            }
            break;

        default:
            break;
    }

    application.setStatus(status);

    return applicationRepository.save(application);
}
}