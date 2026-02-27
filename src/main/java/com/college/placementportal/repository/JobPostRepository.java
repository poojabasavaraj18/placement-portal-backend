package com.college.placementportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.placementportal.entity.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost, Long> {
}