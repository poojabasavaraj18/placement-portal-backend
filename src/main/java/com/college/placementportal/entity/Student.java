package com.college.placementportal.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 👤 Basic Info
    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String placementStatus;

    private String role = "STUDENT";

    // 🔥 NEW FIELDS
    private String usn;
    private Double cgpa;
    private Integer year;
    private String phone;

    // 🏫 Department
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // 🛠 Skills (keep as is if already working)
    @ManyToMany
    private Set<Skill> skills;

    // ✅ GETTERS & SETTERS

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPlacementStatus() { return placementStatus; }
    public void setPlacementStatus(String placementStatus) { this.placementStatus = placementStatus; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getUsn() { return usn; }
    public void setUsn(String usn) { this.usn = usn; }

    public Double getCgpa() { return cgpa; }
    public void setCgpa(Double cgpa) { this.cgpa = cgpa; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public Set<Skill> getSkills() { return skills; }
    public void setSkills(Set<Skill> skills) { this.skills = skills; }
}