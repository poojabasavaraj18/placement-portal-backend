package com.college.placementportal.repository;

import com.college.placementportal.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    List<Announcement> findByDepartmentId(Long departmentId);
}