package com.revature.jobpostservice.controller;

import com.revature.jobpostservice.model.Skills;
import com.revature.jobpostservice.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillsController {

    @Autowired
    private SkillsService skillsService;

    @PostMapping("/create")
    public ResponseEntity<Skills> createSkill(@RequestBody Skills skill) {
        Skills createdSkill = skillsService.createSkill(skill);
        return new ResponseEntity<>(createdSkill, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Skills>> getAllSkills() {
        List<Skills> skillsList = skillsService.getAllSkills();
        return new ResponseEntity<>(skillsList, HttpStatus.OK);
    }
}