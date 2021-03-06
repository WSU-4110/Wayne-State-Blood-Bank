package org.redcrosswarriors.controller;
import org.redcrosswarriors.controllerservice.RequestBloodControllerService;
import org.redcrosswarriors.model.RequestInputDetails;
import org.redcrosswarriors.model.ReturnForRequestBlood;
import org.redcrosswarriors.model.input.RequestBloodInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RequestBloodController
{
    @Autowired
    RequestBloodControllerService requestBloodControllerService;

    @GetMapping("/requestBlood")
    @Secured("ROLE_USER")
    public Map<String, String> getEmail(Principal principal){
        Map<String, String> json = new HashMap();
        json.put("email", principal.getName());
        return json;

    }



    @PostMapping("/requestBlood")
    @Secured("ROLE_USER")
    @Valid
    public ResponseEntity<Object> requestBlood(@RequestBody RequestBloodInput input, Principal principal) {

        Map<String, Object> json = new HashMap();
        String email = principal.getName();


        try{
            if(requestBloodControllerService.isBloodTypeValid(input)) {
                return requestBloodControllerService.requestBlood(input, email);
            }
            else {
                json.put("message", "An unknown error has occurred");
                return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
            }
            //return requestBloodControllerService.requestBlood(input, email);
        }
        catch (Exception e){
            json.put("message", "An unknown error has occurred");
            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
        }


    }
}
