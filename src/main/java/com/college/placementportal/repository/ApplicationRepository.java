package com.college.placementportal.repository;

import com.college.placementportal.entity.Application;
import com.college.placementportal.entity.ApplicationStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Page<Application> findByStudent_Id(Long studentId, Pageable pageable);

    List<Application> findByJobPost_Id(Long jobPostId);

    long countByStatus(ApplicationStatus selected);

}