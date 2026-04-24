package com.college.placementportal.controller;
import java.util.List;
import com.college.placementportal.entity.Student;
import com.college.placementportal.service.StudentService;

// import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.Pageable; 
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

    @GetMapping("/users")
    public List<Student> getAllUsers() {
       return studentService.getAllStudents(Pageable.unpaged()).getContent();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        studentService.deleteUser(id);
    }
}