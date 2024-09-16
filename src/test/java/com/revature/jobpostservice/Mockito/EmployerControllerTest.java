package com.revature.jobpostservice.Mockito;

import com.revature.jobpostservice.controller.EmployerController;
import com.revature.jobpostservice.exceptions.InvalidCredentialsException;
import com.revature.jobpostservice.model.Employer;
import com.revature.jobpostservice.service.EmployerService;
import com.revature.jobpostservice.util.BaseResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployerControllerTest {

    @Mock
    private EmployerService employerService;

    @InjectMocks
    private EmployerController employerController;

    public EmployerControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser_Success() {
        Employer mockEmployer = new Employer();
        BaseResponse<Employer> mockResponse = new BaseResponse<>();
        mockResponse.setMessages("Registration Successful");
        mockResponse.setData(mockEmployer);

        when(employerService.createUser(mockEmployer)).thenReturn(mockEmployer);

        ResponseEntity<BaseResponse<Employer>> response = employerController.registerUser(mockEmployer);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(mockEmployer, response.getBody().getData());
    }

    @Test
    public void testRegisterUser_Failure() {
        Employer mockEmployer = new Employer();
        InvalidCredentialsException exception = new InvalidCredentialsException("Invalid credentials");

        when(employerService.createUser(mockEmployer)).thenThrow(exception);

        ResponseEntity<BaseResponse<Employer>> response = employerController.registerUser(mockEmployer);

        assertEquals(400, response.getStatusCodeValue());
    }
}


