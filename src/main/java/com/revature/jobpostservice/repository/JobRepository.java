package com.revature.jobpostservice.repository;

import com.revature.jobpostservice.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByEmployer_EmpolyerId(Long employerId);
}