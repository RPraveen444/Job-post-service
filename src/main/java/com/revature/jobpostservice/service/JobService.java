package com.revature.jobpostservice.service;
import com.revature.jobpostservice.model.Employer;
import com.revature.jobpostservice.model.Job;
import com.revature.jobpostservice.model.User;
import com.revature.jobpostservice.repository.EmployeeRepository;
import com.revature.jobpostservice.repository.JobRepository;
import com.revature.jobpostservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class JobService {


    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

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
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
        User user = (User) userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));

        job.getApplicants().add(user);
        jobRepository.save(job);
    }

}

