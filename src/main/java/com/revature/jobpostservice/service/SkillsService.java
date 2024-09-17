package com.revature.jobpostservice.service;

import com.revature.jobpostservice.model.Skills;
import com.revature.jobpostservice.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillsService {
    @Autowired
    SkillsRepository skillsRepository;

    public Skills createSkill(Skills skill) {
        return skillsRepository.save(skill);
    }

    public List<Skills> getAllSkills(){
        return skillsRepository.findAll();
    }



}

