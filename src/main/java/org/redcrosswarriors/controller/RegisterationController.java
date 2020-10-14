package org.redcrosswarriors.controller;

import io.swagger.models.Response;
import org.json.JSONObject;
import org.redcrosswarriors.controllerservice.RegistrationControllerService;
import org.redcrosswarriors.model.Message;
import org.redcrosswarriors.model.input.RegisterationInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RegisterationController
{
    @Autowired
    RegistrationControllerService registerService;

    @PostMapping("/registeration")
    public ResponseEntity<Object> registerAccount(@RequestBody RegisterationInput input)
    {
        Map<String, Object> json = new HashMap();
        if(registerService.userExists(input))
        {
            json.put("status", "User with that username already exists.");
            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);

        }
        else if(!registerService.isValidPassword(input))
        {
            json.put("status", "Invalid Password");
            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
        }
        else
        {
            registerService.registerAccount(input);
            json.put("status", "User created.");
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

}
