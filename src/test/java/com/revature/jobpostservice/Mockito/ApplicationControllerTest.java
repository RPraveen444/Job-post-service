package com.revature.jobpostservice.Mockito;

import com.revature.jobpostservice.controller.ApplicationController;
import com.revature.jobpostservice.enums.ApplicationStatus;
import com.revature.jobpostservice.model.Application;
import com.revature.jobpostservice.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ApplicationControllerTest {

    @Mock
    private ApplicationService applicationService;

    @InjectMocks
    private ApplicationController applicationController;

    public ApplicationControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateApplicationStatus() {
        Long userId = 1L;
        Long jobId = 1L;
        ApplicationStatus status = ApplicationStatus.APPLIED;
        Application mockApplication = new Application();

        when(applicationService.updateApplicationStatus(userId, jobId, status)).thenReturn(mockApplication);

        ResponseEntity<Application> response = applicationController.updateApplicationStatus(userId, jobId, status);

        assertEquals(ResponseEntity.ok(mockApplication), response);
        verify(applicationService, times(1)).updateApplicationStatus(userId, jobId, status);
    }
}

