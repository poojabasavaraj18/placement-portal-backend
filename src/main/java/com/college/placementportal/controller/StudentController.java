package com.college.placementportal.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.college.placementportal.dto.JobPostDTO;
// import com.college.placementportal.entity.JobPost;
import com.college.placementportal.entity.Student;
import com.college.placementportal.service.StudentService;
import jakarta.validation.Valid;
// import com.college.placementportal.dto.JobPostDTO;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent( @Valid @RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    // @GetMapping
    // public List<Student> getAllStudents() {
    //     return studentService.getAllStudents();
    // }
   

    @GetMapping
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentService.getAllStudents(pageable);
    }

    @GetMapping("/{id}/recommended-jobs")
    public List<JobPostDTO> getRecommendedJobs(@PathVariable Long id) {
        return studentService.recommendJobs(id);
}
}