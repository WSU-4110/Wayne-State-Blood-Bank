package org.redcrosswarriors.controllerservice;

import org.junit.jupiter.api.Test;
import org.redcrosswarriors.model.Feedback;
import org.redcrosswarriors.repository.AccountDetailsRepository;
import org.redcrosswarriors.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestFeedbackControllerService {

    @MockBean
    private FeedbackRepository repository;

    @MockBean
    private AccountDetailsRepository accountDetailsRepository;

    @Autowired
    private FeedbackControllerService service;

    @Test
    public void testCreateFeedback(){
        Feedback feedback = new Feedback();
        feedback.setMessage("Hello World");
        when(accountDetailsRepository.findIdByEmail("test@wayne.edu")).thenReturn(1);
        ResponseEntity<Object> response = service.createFeedback("test@wayne.edu", feedback);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}
