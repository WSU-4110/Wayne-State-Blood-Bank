package org.redcrosswarriors.controller;

import org.redcrosswarriors.repository.AccountDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private AccountDetailsRepository repository;

    @GetMapping("/loginStatus")
    public ResponseEntity<Object> getLoginStatus(Principal principal){
        Map<String, Object> json = new HashMap<String, Object>();
        if(principal != null){
            String email = principal.getName();
            json.put("isLoggedIn", true);
            json.put("userEmail", email);
            List<String> roles = repository.getRolesByEmail(email);
            json.put("roles", roles);

        }
        else{
            json.put("isLoggedIn", false);
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }


}
