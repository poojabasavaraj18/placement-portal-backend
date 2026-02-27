package com.college.placementportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.placementportal.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}