package com.college.placementportal.service;

import com.college.placementportal.dto.ApplicationResponseDTO;
import com.college.placementportal.entity.Application;
import com.college.placementportal.entity.ApplicationStatus;
import com.college.placementportal.entity.JobPost;
import com.college.placementportal.entity.Student;
import com.college.placementportal.repository.ApplicationRepository;
import com.college.placementportal.repository.JobPostRepository;
import com.college.placementportal.repository.StudentRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final JobPostRepository jobPostRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              StudentRepository studentRepository,
                              JobPostRepository jobPostRepository) {
        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.jobPostRepository = jobPostRepository;
    }

    // 🔥 FINAL APPLY METHOD (FIXED)
    public Application apply(Long studentId, Long jobId, Application application, MultipartFile resume) {

        // ✅ FETCH STUDENT
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // ✅ FETCH JOB
        JobPost job = jobPostRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // ✅ SET RELATIONS
        application.setStudent(student);
        application.setJobPost(job);

        // 🔥 DUPLICATE CHECK
        if (applicationRepository.existsByStudent_IdAndJobPost_Id(studentId, jobId)) {
            throw new RuntimeException("You already applied for this job");
        }

        try {
            // 🔥 SAFETY CHECK (THIS WAS MISSING)
            if (resume == null || resume.isEmpty()) {
                throw new RuntimeException("Resume file is missing");
            }

            // 📁 FILE SAVE
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            String fileName = System.currentTimeMillis() + "_" + resume.getOriginalFilename();

            java.io.File folder = new java.io.File(uploadDir);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            java.io.File file = new java.io.File(uploadDir + fileName);
            resume.transferTo(file);

            application.setResumePath(fileName);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("File upload failed");
        }

        return applicationRepository.save(application);
    }

    // ✅ STUDENT APPLICATIONS
    public Page<ApplicationResponseDTO> getApplicationsByStudent(Long studentId, Pageable pageable) {
        return applicationRepository.findByStudent_Id(studentId, pageable)
                .map(app -> new ApplicationResponseDTO(
                        app.getId(),
                        app.getJobPost().getTitle(),
                        app.getJobPost().getCompanyName(),
                        app.getStatus(),
                        app.getResumePath()
                ));
    }

    // ✅ GET ALL
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // ✅ GET BY JOB
    public List<Application> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobPost_Id(jobId);
    }

    // ✅ UPDATE STATUS
    public Application updateStatus(Long id, ApplicationStatus status) {
        Application app = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        app.setStatus(status);
        return applicationRepository.save(app);
    }
}