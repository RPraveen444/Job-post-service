package com.revature.jobpostservice.repository;

import com.revature.jobpostservice.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employer, Long> {
}