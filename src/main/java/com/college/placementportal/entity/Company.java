package com.college.placementportal.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String hrEmail;
    private String description;

    @OneToMany(mappedBy = "company")
    private List<JobPost> jobPosts;

    // Getters & Setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getHrEmail() { return hrEmail; }
    public void setHrEmail(String hrEmail) { this.hrEmail = hrEmail; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<JobPost> getJobPosts() { return jobPosts; }
}