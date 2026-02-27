
package com.college.placementportal.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

import com.college.placementportal.entity.Student;
import com.college.placementportal.entity.JobPost;
import com.college.placementportal.entity.Skill;
import com.college.placementportal.repository.StudentRepository;
import com.college.placementportal.repository.JobPostRepository;
import com.college.placementportal.dto.JobPostDTO;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final JobPostRepository jobPostRepository;

    public StudentService(StudentRepository studentRepository,
                          JobPostRepository jobPostRepository) {
        this.studentRepository = studentRepository;
        this.jobPostRepository = jobPostRepository;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 🔥 Recommendation Logic
   public List<JobPostDTO> recommendJobs(Long studentId) {

    Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found"));

    return jobPostRepository.findAll().stream()
            .filter(job -> job.getRequiredSkills().stream()
                    .anyMatch(skill -> student.getSkills().contains(skill)))
            .map(job -> new JobPostDTO(
                    job.getId(),
                    job.getTitle(),
                    job.getSalary(),
                    job.getJobType(),
                    job.getCompany().getName()
            ))
            .collect(Collectors.toList());
}
}