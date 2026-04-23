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

// 🔥 IMPORT THIS
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final JobPostRepository jobPostRepository;

    // 🔥 ADD THIS
    private final PasswordEncoder passwordEncoder;

    // 🔥 UPDATE CONSTRUCTOR
    public StudentService(StudentRepository studentRepository,
                          JobPostRepository jobPostRepository,
                          PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.jobPostRepository = jobPostRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Register / Save Student (ENCRYPT PASSWORD)
    public Student saveStudent(Student student) {

        // 🔥 Encrypt before saving
        student.setPassword(passwordEncoder.encode(student.getPassword()));

        return studentRepository.save(student);
    }

    // ✅ Login Logic (SECURE)
    public Student login(String email, String password) {

        Student student = studentRepository.findByEmail(email);

        if (student == null) {
            throw new RuntimeException("Student not found");
        }

        // 🔥 USE matches() instead of equals()
        if (!passwordEncoder.matches(password, student.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return student;
    }

    // ✅ Get all students (pagination)
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    // 🔥 Job Recommendation Logic
    public List<JobPostDTO> recommendJobs(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return jobPostRepository.findAll().stream()
                .filter(job -> {
                    if (job.getSkillsRequired() == null) return false;

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
}