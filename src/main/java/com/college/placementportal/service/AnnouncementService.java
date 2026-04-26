package com.college.placementportal.service;

import com.college.placementportal.entity.Announcement;
import com.college.placementportal.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnnouncementService {

    private final AnnouncementRepository repo;

    public AnnouncementService(AnnouncementRepository repo) {
        this.repo = repo;
    }

    // ✅ CREATE
    public Announcement create(Announcement a) {
        a.setCreatedAt(LocalDateTime.now());
        return repo.save(a);
    }

    // ✅ GET ALL
    public List<Announcement> getAll() {
        return repo.findAll();
    }

    // ✅ GET BY DEPARTMENT (FIXED)
    public List<Announcement> getByDepartment(Long deptId) {
        return repo.findByDepartmentId(deptId);
    }
}