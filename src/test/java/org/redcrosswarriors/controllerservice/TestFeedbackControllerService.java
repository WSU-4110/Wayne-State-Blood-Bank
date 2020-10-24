package org.redcrosswarriors.controllerservice;

import org.junit.jupiter.api.Test;
import org.redcrosswarriors.model.Feedback;
import org.redcrosswarriors.model.ViewFeedback;
import org.redcrosswarriors.repository.AccountDetailsRepository;
import org.redcrosswarriors.repository.ViewFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestFeedbackControllerService {

    @MockBean
    private AccountDetailsRepository accountDetailsRepository;

    @MockBean
    private ViewFeedbackRepository feedbackRepository;

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

    @Test
    public void testGetFeedback(){
        ViewFeedback feedback = new ViewFeedback();
        feedback.setEmail("test@example.com");
        feedback.setId(1);
        feedback.setName("John Doe");
        feedback.setMessage("test message");
        ViewFeedback feedback1 = new ViewFeedback();
        feedback.setEmail("test@example.com");
        feedback.setId(2);
        feedback.setName("Jane Doe");
        feedback.setMessage("Hello World");
        List<ViewFeedback> feedbackList = new ArrayList<>();
        feedbackList.add(feedback);
        feedbackList.add(feedback1);
        when(feedbackRepository.findAll()).thenReturn(feedbackList);
        ResponseEntity<Object> response = service.getAllFeedback();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, ((List) response.getBody()).size());
    }

}
