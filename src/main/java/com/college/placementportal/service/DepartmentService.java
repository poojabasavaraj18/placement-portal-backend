package com.college.placementportal.service;

import com.college.placementportal.entity.*;
import com.college.placementportal.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class DepartmentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final AnnouncementRepository announcementRepository;
    private final ApplicationRepository applicationRepository;

    public DepartmentService(
            StudentRepository studentRepository,
            DepartmentRepository departmentRepository,
            AnnouncementRepository announcementRepository,
            ApplicationRepository applicationRepository
    ) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
        this.announcementRepository = announcementRepository;
        this.applicationRepository = applicationRepository;
    }

    // 📋 Students
    public List<Student> getStudentsByDepartment(Long deptId) {
        return studentRepository.findByDepartmentId(deptId);
    }

    // 📢 Create Announcement
    public Announcement createAnnouncement(Long deptId, Announcement a) {
        Department dept = departmentRepository.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        a.setDepartment(dept);
        a.setCreatedAt(LocalDateTime.now());
        a.setCreatedBy("DEPARTMENT");
        a.setTarget("STUDENT");

        return announcementRepository.save(a);
    }


    // ✅ GET ALL DEPARTMENTS
public List<Department> getAllDepartments() {
    return departmentRepository.findAll();
}

    // 📢 Get announcements
    public List<Announcement> getAnnouncements(Long deptId) {
        return announcementRepository.findByDepartmentId(deptId);
    }

    // 📂 Student basic
    public Student getStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // 🗑 Delete student
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // 📊 FULL ANALYTICS
    public Map<String, Long> getFullStats(Long deptId) {

        List<Student> students = studentRepository.findByDepartmentId(deptId);
        List<Application> apps = applicationRepository.findByStudentDepartmentId(deptId);

        long totalStudents = students.size();
        long applied = apps.size();

        long selected = apps.stream()
                .filter(a -> a.getStatus().name().equalsIgnoreCase("SELECTED"))
                .count();

        long rejected = apps.stream()
                .filter(a -> a.getStatus().name().equalsIgnoreCase("REJECTED"))
                .count();

        Map<String, Long> stats = new HashMap<>();
        stats.put("totalStudents", totalStudents);
        stats.put("applied", applied);
        stats.put("selected", selected);
        stats.put("rejected", rejected);

        return stats;
    }

    // 📂 FULL PROFILE
    public Map<String, Object> getStudentFullProfile(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Application> apps = applicationRepository.findByStudentId(studentId);

        Map<String, Object> data = new HashMap<>();
        data.put("student", student);
        data.put("applications", apps);

        return data;
    }

    // 📤 EXPORT
    public List<Student> exportStudents(Long deptId) {
        return studentRepository.findByDepartmentId(deptId);
    }
}