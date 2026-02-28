package com.college.placementportal.repository;

import com.college.placementportal.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // List<Application> findByStudentId(Long studentId);
    Page<Application> findByStudentId(Long studentId, Pageable pageable);

    List<Application> findByJobPostId(Long jobPostId);

}