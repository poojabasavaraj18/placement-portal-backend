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

    // 👨‍🎓 Get applications for a specific student (used in dashboard)
    Page<Application> findByStudent_Id(Long studentId, Pageable pageable);

    // 🏢 Get applications for a job (recruiter view)
    List<Application> findByJobPost_Id(Long jobPostId);

    // 📊 Count by status
    long countByStatus(ApplicationStatus selected);

    // 🔥 IMPORTANT FIX: prevent duplicate apply (per student + job)
    boolean existsByStudent_IdAndJobPost_Id(Long studentId, Long jobPostId);

    List<Application> findByStudentDepartmentId(Long departmentId);

    List<Application> findByStudentId(Long studentId);
}