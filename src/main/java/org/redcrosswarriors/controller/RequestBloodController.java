package org.redcrosswarriors.controller;
import org.redcrosswarriors.controllerservice.RequestBloodControllerService;
import org.redcrosswarriors.model.input.RequestBloodInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RequestBloodController
{
    @Autowired
    RequestBloodControllerService requestBloodControllerService;




    @PostMapping("/requestblood")
    public ResponseEntity<Object> registerAccount(@RequestBody RequestBloodInput input, Principal principal)
    {
        System.out.println("start");
        String email = principal.getName();
        Map<String, Object> json = new HashMap();
        if(requestBloodControllerService.requestBlood(input, email))
        {
            json.put("status", "Request Processed completely");
            int numbers = requestBloodControllerService.numberOfMatches(input.getBloodType());

        }
        else
        {
            json.put("status", "Request cannot be processed");
            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
