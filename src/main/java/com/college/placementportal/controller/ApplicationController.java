package com.college.placementportal.controller;

import com.college.placementportal.dto.ApplicationResponseDTO;
import com.college.placementportal.entity.Application;
import com.college.placementportal.entity.ApplicationStatus;
import com.college.placementportal.service.ApplicationService;

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

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // 🔥 APPLY WITH FILE
    @PostMapping("/upload")
    public Application applyWithFile(
            @RequestParam Long studentId,
            @RequestParam Long jobId,
            @RequestParam MultipartFile resume,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            // @RequestParam Double cgpa,
            @RequestParam(required = false) Double cgpa,
            // @RequestParam(required = false) String skills,
            // @RequestParam(required = false) String experience,
            @RequestParam(required = false) String skills,
            @RequestParam(required = false) String experience,
            @RequestParam String coverLetter
    ) {

        Application app = new Application();

        app.setName(name);
        app.setEmail(email);
        app.setPhone(phone);
        app.setCgpa(cgpa);
        app.setSkills(skills);
        app.setExperience(experience);
        app.setCoverLetter(coverLetter);

        return applicationService.apply(studentId, jobId, app, resume);
    }

    // 📄 GET ALL
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
    public List<Application> getApplicationsByJob(@PathVariable Long jobId) {
        return applicationService.getApplicationsByJob(jobId);
    }

    // 🔄 UPDATE STATUS
    @PutMapping("/{id}/status")
    public Application updateStatus(
            @PathVariable Long id,
            @RequestParam ApplicationStatus status) {
        return applicationService.updateStatus(id, status);
    }
}