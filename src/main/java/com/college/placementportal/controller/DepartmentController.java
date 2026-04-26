package com.college.placementportal.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import com.college.placementportal.entity.*;
import com.college.placementportal.service.DepartmentService;

@RestController
@RequestMapping("/departments")
@CrossOrigin(origins = "http://localhost:3000")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // 📋 Students
    @GetMapping("/{deptId}/students")
    public List<Student> getStudents(@PathVariable Long deptId) {
        return departmentService.getStudentsByDepartment(deptId);
    }

    // 📢 Create Announcement
    @PostMapping("/{deptId}/announcement")
    public Announcement createAnnouncement(
            @PathVariable Long deptId,
            @RequestBody Announcement a) {
        return departmentService.createAnnouncement(deptId, a);
    }

    // 📢 Get Announcements
    @GetMapping("/{deptId}/announcements")
    public List<Announcement> getAnnouncements(@PathVariable Long deptId) {
        return departmentService.getAnnouncements(deptId);
    }

    // 📂 Student basic
    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable Long id) {
        return departmentService.getStudent(id);
    }

    // 🗑 Delete student
    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable Long id) {
        departmentService.deleteStudent(id);
    }

    // 📊 FULL STATS
    @GetMapping("/{deptId}/full-stats")
    public Map<String, Long> getFullStats(@PathVariable Long deptId) {
        return departmentService.getFullStats(deptId);
    }

    // 📂 FULL PROFILE
    @GetMapping("/student/{id}/profile")
    public Map<String, Object> getStudentProfile(@PathVariable Long id) {
        return departmentService.getStudentFullProfile(id);
    }

    // ✅ GET ALL DEPARTMENTS (REQUIRED FOR DROPDOWN)
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // 📤 EXPORT
    @GetMapping("/{deptId}/export")
    public List<Student> exportStudents(@PathVariable Long deptId) {
        return departmentService.exportStudents(deptId);
    }
}