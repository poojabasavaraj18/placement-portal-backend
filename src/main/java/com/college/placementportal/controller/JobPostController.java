package com.college.placementportal.controller;

import com.college.placementportal.entity.JobPost;
import com.college.placementportal.service.JobPostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobposts")
public class JobPostController {

    private final JobPostService jobPostService;

    public JobPostController(JobPostService jobPostService) {
        this.jobPostService = jobPostService;
    }

    @PostMapping
    public JobPost createJobPost(@RequestBody JobPost jobPost) {
        return jobPostService.saveJobPost(jobPost);
    }

    @GetMapping
    public List<JobPost> getAllJobPosts() {
        return jobPostService.getAllJobPosts();
    }
}