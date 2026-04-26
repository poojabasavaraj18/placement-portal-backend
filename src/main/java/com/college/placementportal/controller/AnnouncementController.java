package com.college.placementportal.controller;

import com.college.placementportal.entity.Announcement;
import com.college.placementportal.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cdc/announcements")
public class AnnouncementController {

    @Autowired
    private AnnouncementService service;

    // 🔥 POST (CDC creates announcement)
    @PostMapping
    public Announcement create(@RequestBody Announcement a) {
        return service.create(a);
    }

    // 🔥 GET by target
    @GetMapping("/department/{deptId}")
public List<Announcement> getByDepartment(@PathVariable Long deptId) {
    return service.getByDepartment(deptId);
}

    // 🔥 OPTIONAL: get all
    @GetMapping
    public List<Announcement> getAll() {
        return service.getAll();
    }
}