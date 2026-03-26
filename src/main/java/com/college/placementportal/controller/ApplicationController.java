// package com.college.placementportal.controller;

// import com.college.placementportal.dto.ApplicationResponseDTO;
// import com.college.placementportal.entity.Application;
// import com.college.placementportal.entity.ApplicationStatus;
// import com.college.placementportal.entity.JobPost;
// import com.college.placementportal.entity.Student;
// import com.college.placementportal.service.ApplicationService;
// import com.college.placementportal.repository.StudentRepository;
// import com.college.placementportal.repository.JobPostRepository;

// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;

// import java.util.List;

// @RestController
// @RequestMapping("/applications")
// @CrossOrigin(origins = "http://localhost:3000")
// public class ApplicationController {

//     private final ApplicationService applicationService;
//     private final StudentRepository studentRepository;
//     private final JobPostRepository jobPostRepository;

//     public ApplicationController(ApplicationService applicationService,
//             StudentRepository studentRepository,
//             JobPostRepository jobPostRepository) {
//         this.applicationService = applicationService;
//         this.studentRepository = studentRepository;
//         this.jobPostRepository = jobPostRepository;
//     }

//     // 🔥 APPLY WITH FILE UPLOAD
    
//     @PostMapping("/upload")
//     public Application applyWithFile(
//             @RequestParam Long studentId,
//             @RequestParam Long jobId,
//             @RequestParam MultipartFile resume,
//             @RequestParam String name,
//             @RequestParam String email,
//             @RequestParam String phone,
//             @RequestParam String cgpa,
//             @RequestParam(required = false) String skills,
//             @RequestParam(required = false) String experience,
//             @RequestParam String coverLetter) {

//         try {
//             String uploadDir = "uploads/";
//             String fileName = System.currentTimeMillis() + "_" + resume.getOriginalFilename();

//             java.io.File folder = new java.io.File(uploadDir);
//             if (!folder.exists())
//                 folder.mkdirs();

//             java.io.File file = new java.io.File(uploadDir + fileName);
//             resume.transferTo(file);

//             Student student = studentRepository.findById(studentId)
//                     .orElseThrow(() -> new RuntimeException("Student not found"));

//             JobPost job = jobPostRepository.findById(jobId)
//                     .orElseThrow(() -> new RuntimeException("Job not found"));

//             Application application = new Application();

//             application.setStudent(student);
//             application.setJobPost(job);
//             application.setResumePath(fileName);

//             // 🔥 ADD THIS (IMPORTANT)
//             application.setName(name);
//             application.setEmail(email);
//             application.setPhone(phone);
//             application.setCgpa(cgpa);
//             application.setSkills(skills);
//             application.setExperience(experience);
//             application.setCoverLetter(coverLetter);

//             return applicationService.apply(application);

//         } catch (Exception e) {
//             e.printStackTrace();
//             throw new RuntimeException("Upload failed: " + e.getMessage());
//         }
        
//     }

//     // 📄 GET ALL APPLICATIONS
//     @GetMapping
//     public List<Application> getAllApplications() {
//         return applicationService.getAllApplications();
//     }

//     // 👨‍🎓 STUDENT APPLICATIONS
//     @GetMapping("/student/{studentId}")
//     public Page<ApplicationResponseDTO> getApplicationsByStudent(
//             @PathVariable Long studentId,
//             Pageable pageable) {

//         return applicationService.getApplicationsByStudent(studentId, pageable);
//     }

//     // 🏢 JOB APPLICATIONS (Recruiter)
//     @GetMapping("/job/{jobId}")
//     public List<ApplicationResponseDTO> getApplicationsByJob(@PathVariable Long jobId) {
//         return applicationService.getApplicationsByJob(jobId);
//     }

//     // 🔄 UPDATE STATUS
//     @PutMapping("/{id}/status")
//     public Application updateStatus(
//             @PathVariable Long id,
//             @RequestParam ApplicationStatus status) {
//         return applicationService.updateStatus(id, status);
//     }

//     // 📄 VIEW / DOWNLOAD RESUME
//     @GetMapping("/resume/{fileName}")
//     public org.springframework.core.io.Resource getResume(@PathVariable String fileName) throws Exception {

//         java.nio.file.Path path = java.nio.file.Paths.get("uploads").resolve(fileName);
//         return new org.springframework.core.io.UrlResource(path.toUri());
//     }
// }
package com.college.placementportal.controller;

import com.college.placementportal.dto.ApplicationResponseDTO;
import com.college.placementportal.entity.Application;
import com.college.placementportal.entity.ApplicationStatus;
import com.college.placementportal.entity.JobPost;
import com.college.placementportal.entity.Student;
import com.college.placementportal.service.ApplicationService;
import com.college.placementportal.repository.StudentRepository;
import com.college.placementportal.repository.JobPostRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/applications")
@CrossOrigin(origins = "http://localhost:3000")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final StudentRepository studentRepository;
    private final JobPostRepository jobPostRepository;

    public ApplicationController(ApplicationService applicationService,
                                 StudentRepository studentRepository,
                                 JobPostRepository jobPostRepository) {
        this.applicationService = applicationService;
        this.studentRepository = studentRepository;
        this.jobPostRepository = jobPostRepository;
    }

    // 🔥 APPLY WITH FILE UPLOAD
    @PostMapping("/upload")
    public Application applyWithFile(
            @RequestParam Long studentId,
            @RequestParam Long jobId,
            @RequestParam MultipartFile resume,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam Double cgpa,
            @RequestParam(required = false) String skills,
            @RequestParam(required = false) String experience,
            @RequestParam String coverLetter
    ) {

        try {

            // 🔍 DEBUG LOGS
            System.out.println("==== DEBUG START ====");
            System.out.println("studentId: " + studentId);
            System.out.println("jobId: " + jobId);
            System.out.println("name: " + name);
            System.out.println("email: " + email);
            System.out.println("phone: " + phone);
            System.out.println("cgpa: " + cgpa);
            System.out.println("skills: " + skills);
            System.out.println("experience: " + experience);
            System.out.println("coverLetter: " + coverLetter);
            System.out.println("resume: " + resume.getOriginalFilename());
            System.out.println("==== DEBUG END ====");

            // 📁 Save file
            // String uploadDir = "uploads/";
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            String fileName = System.currentTimeMillis() + "_" + resume.getOriginalFilename();

            java.io.File folder = new java.io.File(uploadDir);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            java.io.File file = new java.io.File(uploadDir + fileName);
            resume.transferTo(file);

            // 🔗 Fetch entities
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            JobPost job = jobPostRepository.findById(jobId)
                    .orElseThrow(() -> new RuntimeException("Job not found"));

            // 🧠 Create application
            Application application = new Application();

            application.setStudent(student);
            application.setJobPost(job);
            application.setResumePath(fileName);

            application.setName(name);
            application.setEmail(email);
            application.setPhone(phone);
            application.setCgpa(cgpa);
            application.setSkills(skills);
            application.setExperience(experience);
            application.setCoverLetter(coverLetter);

            return applicationService.apply(application);

        } catch (Exception e) {
            e.printStackTrace();  // 🔥 THIS WILL SHOW REAL ERROR
            throw new RuntimeException("Upload failed: " + e.getMessage());
        }
    }

    // 📄 GET ALL APPLICATIONS
    @GetMapping
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    // 👨‍🎓 STUDENT APPLICATIONS
    @GetMapping("/student/{studentId}")
    public Page<ApplicationResponseDTO> getApplicationsByStudent(
            @PathVariable Long studentId,
            Pageable pageable) {

        return applicationService.getApplicationsByStudent(studentId, pageable);
    }

    // 🏢 JOB APPLICATIONS
    @GetMapping("/job/{jobId}")
    public List<ApplicationResponseDTO> getApplicationsByJob(@PathVariable Long jobId) {
        return applicationService.getApplicationsByJob(jobId);
    }

    // 🔄 UPDATE STATUS
    @PutMapping("/{id}/status")
    public Application updateStatus(
            @PathVariable Long id,
            @RequestParam ApplicationStatus status) {
        return applicationService.updateStatus(id, status);
    }

    // 📄 VIEW RESUME
    @GetMapping("/resume/{fileName}")
    public org.springframework.core.io.Resource getResume(@PathVariable String fileName) throws Exception {

        java.nio.file.Path path = java.nio.file.Paths.get("uploads").resolve(fileName);
        return new org.springframework.core.io.UrlResource(path.toUri());
    }
}