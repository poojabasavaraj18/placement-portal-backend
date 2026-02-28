package com.college.placementportal.controller;

import com.college.placementportal.entity.Application;
import com.college.placementportal.entity.ApplicationStatus;
import com.college.placementportal.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public Application apply(@RequestBody Application application) {
        return applicationService.apply(application);
    }

    @GetMapping
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/student/{studentId}")
    public List<Application> getApplicationsByStudent(@PathVariable Long studentId) {
        return applicationService.getApplicationsByStudent(studentId);
    }

    @GetMapping("/job/{jobId}")
    public List<Application> getApplicationsByJob(@PathVariable Long jobId) {
        return applicationService.getApplicationsByJob(jobId);
    }

    @PutMapping("/{id}/status")
    public Application updateStatus(
            @PathVariable Long id,
            @RequestParam ApplicationStatus status) {
        return applicationService.updateStatus(id, status);
    }
}