package com.revature.jobpostservice.controller;

import com.revature.jobpostservice.enums.ApplicationStatus;
import com.revature.jobpostservice.model.Application;
import com.revature.jobpostservice.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PutMapping("/updateStatus/{userId}/{jobId}")
    public ResponseEntity<Application> updateApplicationStatus(
            @PathVariable Long userId,
            @PathVariable Long jobId,
            @RequestParam ApplicationStatus status) {
        Application updatedApplication = applicationService.updateApplicationStatus(userId, jobId, status);
        return ResponseEntity.ok(updatedApplication);
    }
}
