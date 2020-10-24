package org.redcrosswarriors.controller;

import org.redcrosswarriors.controllerservice.FeedbackControllerService;
import org.redcrosswarriors.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.security.Principal;

@RestController
public class FeedbackController {
    @Autowired
    private FeedbackControllerService service;

    @PostMapping("/feedback")
    @Secured("ROLE_USER")
    public ResponseEntity<Object> createFeedback(Principal principal, @Valid @RequestBody Feedback feedback){
        return service.createFeedback(principal.getName(), feedback);
    }

    @GetMapping("/feedback")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> getFeedback(){
        return service.getAllFeedback();
    }

}
