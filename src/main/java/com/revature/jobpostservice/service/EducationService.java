package com.revature.jobpostservice.service;

import com.revature.jobpostservice.model.Education;
import com.revature.jobpostservice.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    public Education createEducation(Education education) {
        return educationRepository.save(education);
    }

    public List<Education> getAllEducation() {
        return educationRepository.findAll();
    }
}

