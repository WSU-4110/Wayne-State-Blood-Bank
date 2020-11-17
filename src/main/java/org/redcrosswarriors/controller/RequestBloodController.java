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
    public ResponseEntity<Object> requestBlood(@RequestBody RequestBloodInput input)
    {
        System.out.println("start");
//        System.out.println(input.getLastName());
//        System.out.println(input.getEmail());
//        System.out.println(input.getPhoneNumber());
//        System.out.println(input.getBloodType());
//        System.out.println(input.getHospitalName());
//        System.out.println(input.getStreet());
//        System.out.println(input.getCity());
//        System.out.println(input.getState());
//        System.out.println( input.getZipCode());
//        System.out.println( input.getMessage());

        Map<String, Object> json = new HashMap();
        if(requestBloodControllerService.requestBlood(input))
        {
            System.out.println("start");
            json.put("status", "Request Processed completely");
            int numbers = requestBloodControllerService.numberOfMatches(input.getBloodType());
            System.out.println(numbers);

        }
        else
        {
            json.put("status", "Request cannot be processed");
            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
