package org.redcrosswarriors.controller;

import org.redcrosswarriors.controllerservice.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationController {
    @Autowired
    VerificationTokenService service;

    @GetMapping("/verify/{token}")
    public ResponseEntity<Object> verifyUser(@PathVariable String token){
        // TODO change this to redirect user to homepage if successful
        return service.verifyUser(token);
    }
}
