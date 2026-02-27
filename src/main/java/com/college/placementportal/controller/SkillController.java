package com.college.placementportal.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.college.placementportal.entity.Skill;
import com.college.placementportal.repository.SkillRepository;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private final SkillRepository skillRepository;

    public SkillController(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        return skillRepository.save(skill);
    }

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }
}