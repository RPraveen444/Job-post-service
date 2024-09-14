package com.revature.jobpostservice.service;
import com.revature.jobpostservice.enums.ApplicationStatus;
import com.revature.jobpostservice.model.Application;
import com.revature.jobpostservice.model.Job;
import com.revature.jobpostservice.model.User;
import com.revature.jobpostservice.repository.ApplicationRepository;
import com.revature.jobpostservice.repository.EmployerRepository;
import com.revature.jobpostservice.repository.JobRepository;
import com.revature.jobpostservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmployerRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public Optional<Job> getJobById(Long jobId) {
        return jobRepository.findById(jobId);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public void applyForJob(Long jobId, Long userId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Application application = new Application();
        application.setJob(job);
        application.setUser(user);
        application.setStatus(ApplicationStatus.APPLIED);
        application.setApplicationDate(new Date());

        applicationRepository.save(application);
        job.getApplicants().add(user);
        jobRepository.save(job);
    }

    public List<Application> getUserApplications(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return applicationRepository.findByUser(user);
    }

    public void withdrawApplication(Long jobId, Long userId) {
        Application application = applicationRepository.findByJob_JobIdAndUser_UserId(jobId, userId);

        if (application != null) {
            application.setStatus(ApplicationStatus.WITHDRAWN);
            applicationRepository.save(application);
        } else {
            throw new RuntimeException("Application not found");
        }
    }

    public List<Job> getJobsByEmployerId(Long employerId) {
        return jobRepository.findByEmployer_EmpolyerId(employerId);
    }

}

