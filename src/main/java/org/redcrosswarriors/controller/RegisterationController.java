package org.redcrosswarriors.controller;


import org.redcrosswarriors.controllerservice.RegistrationControllerService;
import org.redcrosswarriors.model.input.RegistrationInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RegisterationController
{
    @Autowired
    RegistrationControllerService registerService;

    @PostMapping("/registration")
    public ResponseEntity<Object> registerAccount(@RequestBody @Valid RegistrationInput input)
    {
        Map<String, Object> json = new HashMap();
        if(registerService.userExists(input))
        {
            json.put("status", "User with that username already exists.");
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
