package com.college.placementportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.placementportal.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // 🔑 Find by email (for login)
    Student findByEmail(String email);
}