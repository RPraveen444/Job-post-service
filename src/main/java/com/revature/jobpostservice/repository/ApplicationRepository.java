package com.revature.jobpostservice.repository;

import com.revature.jobpostservice.model.Application;
import com.revature.jobpostservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByUser(User user);
    Application findByJob_JobIdAndUser_UserId(Long jobId, Long userId);
    Optional<Application> findByUserUserIdAndJobJobId(Long userId, Long jobId);
}

