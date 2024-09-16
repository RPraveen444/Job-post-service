package com.revature.jobpostservice.MockMVC;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.jobpostservice.controller.EmployerController;
import com.revature.jobpostservice.model.Employer;
import com.revature.jobpostservice.service.EmployerService;
import com.revature.jobpostservice.util.BaseResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployerController.class)
public class EmployerControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployerService employerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRegisterUser() throws Exception {
        Employer mockEmployer = new Employer();
        BaseResponse<Employer> mockResponse = new BaseResponse<>();
        mockResponse.setMessages("Registration Successful");

        Mockito.when(employerService.createUser(Mockito.any(Employer.class))).thenReturn(mockEmployer);

        mockMvc.perform(post("/api/employers/register")
                        .content(objectMapper.writeValueAsString(mockEmployer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}


