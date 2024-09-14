package com.revature.jobpostservice.service;

import com.revature.jobpostservice.enums.ApplicationStatus;
import com.revature.jobpostservice.model.Application;
import com.revature.jobpostservice.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public Application updateApplicationStatus(Long userId, Long jobId, ApplicationStatus newStatus) {
        Optional<Application> applicationOpt = applicationRepository.findByUserUserIdAndJobJobId(userId, jobId);

        if (applicationOpt.isPresent()) {
            Application application = applicationOpt.get();
            application.setStatus(newStatus);
            return applicationRepository.save(application);
        } else {
            throw new RuntimeException("Application not found for the given userId and jobId");
        }
    }
}
