package com.college.placementportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.placementportal.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}