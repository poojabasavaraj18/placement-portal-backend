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

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final JobPostRepository jobPostRepository;

    public StudentService(StudentRepository studentRepository,
                          JobPostRepository jobPostRepository) {
        this.studentRepository = studentRepository;
        this.jobPostRepository = jobPostRepository;
    }

    // ✅ Save student
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // ✅ Get all students (paginated)
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    // 🔥 Recommendation Logic (UPDATED)
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
                        job.getCompanyName() // ✅ FIXED
                ))
                .collect(Collectors.toList());
    }
}