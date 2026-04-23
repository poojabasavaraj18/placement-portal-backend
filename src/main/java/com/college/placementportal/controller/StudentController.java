package com.college.placementportal.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.college.placementportal.dto.JobPostDTO;
import com.college.placementportal.entity.Student;
import com.college.placementportal.service.StudentService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// 🔥 NEW IMPORTS
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Student student) {
        try {
            student.setPlacementStatus("ACTIVE");
            student.setRole("STUDENT");

            Student saved = studentService.saveStudent(student);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    // 🔐 LOGIN (FIXED)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Student student) {
        try {
            Student loggedIn = studentService.login(
                    student.getEmail(),
                    student.getPassword()
            );

            return ResponseEntity.ok(loggedIn);

        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    // 📄 GET ALL STUDENTS
    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudents(Pageable pageable) {
        return ResponseEntity.ok(studentService.getAllStudents(pageable));
    }

    // 🎯 RECOMMENDED JOBS
    @GetMapping("/{id}/recommended-jobs")
    public ResponseEntity<List<JobPostDTO>> getRecommendedJobs(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.recommendJobs(id));
    }
}