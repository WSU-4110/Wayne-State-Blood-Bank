package org.redcrosswarriors.controllerservice;

import org.redcrosswarriors.model.Feedback;
import org.redcrosswarriors.repository.AccountDetailsRepository;
import org.redcrosswarriors.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.HashMap;

@Service
public class FeedbackControllerService {

    @Autowired
    private FeedbackRepository repository;

    @Autowired
    private AccountDetailsRepository accountDetailsRepository;

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


}
