package com.college.placementportal.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.college.placementportal.dto.JobPostDTO;
import com.college.placementportal.entity.Student;
import com.college.placementportal.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // ✅ Register student
    

    @PostMapping("/register")
    public Student register(@RequestBody Student student) {
        student.setPlacementStatus("ACTIVE");
        student.setRole("STUDENT"); // 🔥 important
        return studentService.saveStudent(student);
    }

    // 🔐 Login
    @PostMapping("/login")
    public Student login(@RequestBody Student student) {
        return studentService.login(student.getEmail(), student.getPassword());
    }

    // 📄 Get all students (paginated)
    @GetMapping
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentService.getAllStudents(pageable);
    }

    // 🎯 Recommended jobs
    @GetMapping("/{id}/recommended-jobs")
    public List<JobPostDTO> getRecommendedJobs(@PathVariable Long id) {
        return studentService.recommendJobs(id);
    }
}