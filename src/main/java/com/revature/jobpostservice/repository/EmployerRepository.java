package com.revature.jobpostservice.repository;

import com.revature.jobpostservice.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
//    Employer findByUserName(String userName);
    Employer findByEmail(String email);
}
