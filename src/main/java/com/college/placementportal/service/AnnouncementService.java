package com.college.placementportal.service;

import com.college.placementportal.entity.Announcement;
import com.college.placementportal.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository repo;

    // 🔥 CREATE
    public Announcement create(Announcement a) {
        a.setCreatedAt(LocalDateTime.now());
        return repo.save(a);
    }

    // 🔥 GET ALL (optional)
    public List<Announcement> getAll() {
        return repo.findAll();
    }

    // 🔥 GET BY TARGET (MAIN LOGIC)
    public List<Announcement> getByTarget(String target) {
        return repo.findByTarget(target);
    }
}