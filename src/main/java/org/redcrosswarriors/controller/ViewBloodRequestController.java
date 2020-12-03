package org.redcrosswarriors.controller;
import org.redcrosswarriors.controllerservice.ViewRequestControllerService;
import org.redcrosswarriors.model.RequestInputDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.security.Principal;


@RestController
public class ViewBloodRequestController {

    @Autowired
    ViewRequestControllerService service;

    @GetMapping("/viewRequests")
    @Secured("ROLE_USER")
    public ResponseEntity<Object> viewResults(Principal principal) {
        System.out.println(principal.getName());
        String email = principal.getName();
        return service.getAllRequests(email);

    }

}
