package com.college.placementportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.placementportal.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}