package com.revature.jobpostservice.service;

import com.revature.jobpostservice.model.Employer;
import com.revature.jobpostservice.repository.EmployeeRepository;
import com.revature.jobpostservice.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JobRepository jobRepository;

    public List<Employer> getAllEmployees() {
        return employeeRepository.findAll();
    }
}