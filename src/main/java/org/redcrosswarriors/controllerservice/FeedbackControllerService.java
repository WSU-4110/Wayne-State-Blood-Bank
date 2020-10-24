package org.redcrosswarriors.controllerservice;

import org.redcrosswarriors.model.Feedback;
import org.redcrosswarriors.model.ViewFeedback;
import org.redcrosswarriors.repository.AccountDetailsRepository;
import org.redcrosswarriors.repository.FeedbackRepository;
import org.redcrosswarriors.repository.ViewFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Service
public class FeedbackControllerService {

    @Autowired
    private FeedbackRepository repository;

    @Autowired
    private AccountDetailsRepository accountDetailsRepository;

    @Autowired
    private ViewFeedbackRepository feedbackRepository;

    @Transactional
    public ResponseEntity<Object> createFeedback(String email, Feedback feedback){
        Map<String, String> jsonResponse = new HashMap<String, String>();
        try{
            int accountId = accountDetailsRepository.findIdByEmail(email);
            feedback.setAccountId(accountId);
            repository.save(feedback);
        }
        catch (Exception e){
            e.printStackTrace();
            jsonResponse.put("message", "An Unknown error has occurred");
            return new ResponseEntity<Object>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        jsonResponse.put("message", "Successfully added feedback");
        return new ResponseEntity<Object>(jsonResponse, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> getAllFeedback(){
        List<ViewFeedback> feedbackList = feedbackRepository.findAll();
        return new ResponseEntity<Object>(feedbackList, HttpStatus.OK);
    }


}
