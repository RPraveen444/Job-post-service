package com.revature.jobpostservice.service;

import com.revature.jobpostservice.exceptions.EmployerNotFoundException;
import com.revature.jobpostservice.exceptions.InvalidCredentialsException;
import com.revature.jobpostservice.exceptions.InvalidEmailException;
import com.revature.jobpostservice.model.Employer;
import com.revature.jobpostservice.repository.EmployerRepository;
import com.revature.jobpostservice.util.EmailService;
import com.revature.jobpostservice.util.PasswordEncrypter;
import com.revature.jobpostservice.util.PasswordUtils;
import com.revature.jobpostservice.util.RandomCredentialsGenerator;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private PasswordEncrypter passwordEncrypter;

    @Autowired
    private RandomCredentialsGenerator generator;

    public Employer createUser(Employer employer) {
        Employer dbUser = employerRepository.findByEmail(employer.getEmail());

        if (dbUser == null) {
            String password = employer.getPassword();
            employer.setPassword(passwordEncrypter.hashPassword(password));
            Employer createdUser = employerRepository.save(employer);

            try {
                sendWelcomeEmail(employer.getEmail(), employer.getFirstName());
            } catch (MessagingException e) {
                throw new InvalidEmailException("Failed to send email to " + employer.getEmail());
            }

            return createdUser;
        } else {
            throw new InvalidCredentialsException("Email already exists");
        }
    }

    private void sendWelcomeEmail(String email, String firstName) throws MessagingException {
        String subject = "Welcome to Rev Hire!";
        String body = "Hello " + firstName + ",\n\nYour account has been created successfully.";
        emailService.sendEmail(email, subject, body);
    }

    public Employer loginUser(String email, String password) {
        Employer dbUser = employerRepository.findByEmail(email);
        if (dbUser == null) {
            throw new InvalidCredentialsException("Invalid email");
        }

        String hashedPassword = passwordEncrypter.hashPassword(password);
        if (hashedPassword.equals(dbUser.getPassword())) {
            dbUser.setPassword(null);  // Hide password
            return dbUser;
        }

        throw new InvalidCredentialsException("Invalid password");
    }

    public Employer updateEmployer(Long id, Employer employerDetails) {
        Employer existingEmployer = employerRepository.findById(id)
                .orElseThrow(() -> new EmployerNotFoundException("Employer not found with id: " + id));

        // Update fields if they are not null
        if (employerDetails.getFirstName() != null) {
            existingEmployer.setFirstName(employerDetails.getFirstName());
        }
        if (employerDetails.getLastName() != null) {
            existingEmployer.setLastName(employerDetails.getLastName());
        }
        if (employerDetails.getEmail() != null) {
            existingEmployer.setEmail(employerDetails.getEmail());
        }
        if (employerDetails.getContactNumber() != null) {
            existingEmployer.setContactNumber(employerDetails.getContactNumber());
        }
        if (employerDetails.getAddress() != null) {
            existingEmployer.setAddress(employerDetails.getAddress());
        }
        if (employerDetails.getPassword() != null) {
            existingEmployer.setPassword(PasswordUtils.hashPassword(employerDetails.getPassword()));
        }

        // Save updated employer details
        return employerRepository.save(existingEmployer);
    }

    public void deleteEmployer(Long id) {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new EmployerNotFoundException("Employer not found with id: " + id));
        employerRepository.delete(employer);
    }

    public Employer fetchEmployerById(Long id) {
        return employerRepository.findById(id)
                .orElseThrow(() -> new EmployerNotFoundException("Employer not found with id: " + id));
    }

    public List<Employer> fetchAllEmployers() {
        return employerRepository.findAll();
    }

//    public Employer login(String email, String password) {
//        Employer employer = employerRepository.findByEmail(email)
//                .orElseThrow(() -> new EmployerNotFoundException("Employer not found with email: " + email));
//
//        System.out.println("Employer found with email: " + email);
//        if (!PasswordUtils.verifyPassword(password, employer.getPassword())) {
//            throw new IllegalArgumentException("Invalid password");
//        }
//
//        return employer;
//    }

}
