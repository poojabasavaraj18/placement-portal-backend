package com.college.placementportal.service;

import com.college.placementportal.entity.JobPost;
import com.college.placementportal.repository.JobPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostService {

    private final JobPostRepository repository;

    public JobPostService(JobPostRepository repository) {
        this.repository = repository;
    }

    public JobPost saveJobPost(JobPost jobPost) {
        return repository.save(jobPost);
    }

    public List<JobPost> getAllJobPosts() {
        return repository.findAll();
    }

    public JobPost getJobPostById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }
}