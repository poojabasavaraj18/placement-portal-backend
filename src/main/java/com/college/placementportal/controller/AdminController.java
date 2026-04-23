package com.college.placementportal.controller;

import com.college.placementportal.entity.Student;
import com.college.placementportal.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    private final StudentService studentService;

    public AdminController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 🔥 Admin creates recruiter / CDC / department
    @PostMapping("/create-user")
    public Student createUser(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }
}