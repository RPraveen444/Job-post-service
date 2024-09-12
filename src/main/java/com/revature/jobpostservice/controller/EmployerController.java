package com.revature.jobpostservice.controller;

import com.revature.jobpostservice.model.Employer;
import com.revature.jobpostservice.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployerController {

    @Autowired
    private EmployerService employeeService;


    @GetMapping("/all")
    public ResponseEntity<List<Employer>> getAllEmployees() {
        List<Employer> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }


}
