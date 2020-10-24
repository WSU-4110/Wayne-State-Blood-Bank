package org.redcrosswarriors.controller;

import org.redcrosswarriors.controllerservice.FeedbackControllerService;
import org.redcrosswarriors.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
        ResponseEntity<Object> response = service.createFeedback(principal.getName(), feedback);
        System.out.println((response.getStatusCode()));
        return service.createFeedback(principal.getName(), feedback);
    }


}
