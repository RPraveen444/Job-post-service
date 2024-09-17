package com.revature.jobpostservice.repository;

import com.revature.jobpostservice.model.Summary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SummaryRepository extends JpaRepository<Summary, Long> {
    List<Summary> findByUser_UserId(Long userId);
}

