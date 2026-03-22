package com.college.placementportal.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Student relation
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // 🔗 Job relation
    @ManyToOne
    @JoinColumn(name = "job_post_id")
    private JobPost jobPost;

    // 📌 Status
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    // 📅 Date
    private LocalDate appliedDate;

    // 📄 Resume
    private String resumePath;

    // 🧑 Extra form fields
    private String name;
    private String email;
    private String phone;
    private String cgpa;
    private String skills;
    private String experience;

    @Column(length = 2000)
    private String coverLetter;

    // ✅ Constructor
    public Application() {
        this.appliedDate = LocalDate.now();
        this.status = ApplicationStatus.APPLIED;
    }

    // ✅ Getters & Setters

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public JobPost getJobPost() {
        return jobPost;
    }

    public void setJobPost(JobPost jobPost) {
        this.jobPost = jobPost;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }
}