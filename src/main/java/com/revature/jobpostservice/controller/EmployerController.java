package com.revature.jobpostservice.controller;

import com.revature.jobpostservice.exceptions.InvalidCredentialsException;
import com.revature.jobpostservice.model.Employer;
import com.revature.jobpostservice.service.EmployerService;
import com.revature.jobpostservice.util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import com.revature.jobpostservice.service.EmployerService;
import com.revature.jobpostservice.model.Employer;

@RestController
@RequestMapping("/api/employers")
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<Employer>> registerUser(@RequestBody Employer user) {
        BaseResponse<Employer> baseResponse = new BaseResponse<>();
        System.out.println("Received password: " + user.getPassword());
        try {
            Employer createdUser = employerService.createUser(user);
            baseResponse.setMessages("Registration Successful");
            baseResponse.setData(createdUser);
            baseResponse.setStatus(HttpStatus.CREATED.value());
            return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
        } catch (InvalidCredentialsException e) {
            baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            baseResponse.setMessages(e.getMessage());
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            baseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            baseResponse.setMessages("Error during registration: " + e.getMessage());
            return new ResponseEntity<>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<Employer>> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        BaseResponse<Employer> baseResponse = new BaseResponse<>();

        try {
            Employer user = employerService.loginUser(email, password);
            baseResponse.setStatus(HttpStatus.OK.value());
            baseResponse.setMessages("Login Successful");
            baseResponse.setData(user);
            return ResponseEntity.ok(baseResponse);
        } catch (InvalidCredentialsException e) {
            baseResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            baseResponse.setMessages(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(baseResponse);
        } catch (Exception e) {
            baseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            baseResponse.setMessages("Error during authentication: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(baseResponse);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employer> updateEmployer(@PathVariable Long id, @RequestBody Employer employerDetails) {
        Employer updatedEmployer = employerService.updateEmployer(id, employerDetails);
        return ResponseEntity.ok(updatedEmployer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployer(@PathVariable Long id) {
        employerService.deleteEmployer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employer> getEmployerById(@PathVariable Long id) {
        Employer employer = employerService.fetchEmployerById(id);
        return ResponseEntity.ok(employer);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employer>> getAllEmployers() {
        List<Employer> employers = employerService.fetchAllEmployers();
        return ResponseEntity.ok(employers);
    }

//    @PostMapping("/login")
//    public ResponseEntity<Employer> login(@RequestParam String email, @RequestParam String password) {
//        Employer employer = employerService.login(email, password);
//        return ResponseEntity.ok(employer);
//    }
}
