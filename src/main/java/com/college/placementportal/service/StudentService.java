
package com.college.placementportal.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

import com.college.placementportal.entity.Student;
import com.college.placementportal.entity.JobPost;
import com.college.placementportal.entity.Skill;
import com.college.placementportal.repository.StudentRepository;
import com.college.placementportal.repository.JobPostRepository;

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
    public List<JobPost> recommendJobs(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Set<Skill> studentSkills = student.getSkills();

        List<JobPost> allJobs = jobPostRepository.findAll();

        return allJobs.stream()
                .filter(job -> {
                    Set<Skill> required = job.getRequiredSkills();

                    if (required == null || required.isEmpty()) return false;
                    if (studentSkills == null || studentSkills.isEmpty()) return false;

                    return studentSkills.containsAll(required);
                })
                .toList();
    }
}