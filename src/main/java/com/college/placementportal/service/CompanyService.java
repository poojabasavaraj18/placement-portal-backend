package com.college.placementportal.service;

import com.college.placementportal.entity.Company;
import com.college.placementportal.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // Save company
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    // Get all companies
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // Get company by id
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id " + id));
    }

    // Delete company
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}