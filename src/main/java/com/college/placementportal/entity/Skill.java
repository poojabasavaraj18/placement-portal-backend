package com.college.placementportal.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "skills")
    private Set<Student> students;

    @ManyToMany(mappedBy = "requiredSkills")
    private Set<JobPost> jobPosts;

    public Skill() {}

    public Skill(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}