package com.college.placementportal.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.college.placementportal.entity.Student;
import com.college.placementportal.repository.StudentRepository;
import com.college.placementportal.repository.JobPostRepository;
import com.college.placementportal.dto.JobPostDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// 🔐 Password Encoder
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final JobPostRepository jobPostRepository;
    private final PasswordEncoder passwordEncoder;

    // ✅ Constructor Injection
    public StudentService(StudentRepository studentRepository,
                          JobPostRepository jobPostRepository,
                          PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.jobPostRepository = jobPostRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ REGISTER (always encrypt password)
    public Student saveStudent(Student student) {

        // 🔥 Prevent double-encoding (important for admin-created users too)
        if (student.getPassword() != null && !student.getPassword().startsWith("$2a$")) {
            student.setPassword(passwordEncoder.encode(student.getPassword()));
        }

        return studentRepository.save(student);
    }

    // ✅ LOGIN (secure + null-safe)
    public Student login(String email, String password) {

        Student student = studentRepository.findByEmail(email);

        // ✅ Always check null FIRST
        if (student == null) {
            throw new RuntimeException("User not found");
        }

        // 🔥 Compare encrypted password
        if (!passwordEncoder.matches(password, student.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return student;
    }

    // ✅ GET ALL STUDENTS
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    // 🔥 JOB RECOMMENDATION
    public List<JobPostDTO> recommendJobs(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return jobPostRepository.findAll().stream()
                .filter(job -> {
                    if (job.getSkillsRequired() == null || student.getSkills() == null) return false;

                    String jobSkills = job.getSkillsRequired().toLowerCase();

                    return student.getSkills().stream()
                            .anyMatch(skill ->
                                    jobSkills.contains(skill.toLowerCase())
                            );
                })
                .map(job -> new JobPostDTO(
                        job.getId(),
                        job.getTitle(),
                        job.getSalary(),
                        job.getJobType(),
                        job.getCompanyName()
                ))
                .collect(Collectors.toList());
    }
    public void deleteUser(Long id) {
    studentRepository.deleteById(id);
}
}