package org.redcrosswarriors.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @GetMapping("/loginStatus")
    public ResponseEntity<Object> getLoginStatus(Principal principal){
        Map<String, Object> json = new HashMap<String, Object>();
        if(principal != null){
            json.put("isLoggedIn", true);
            json.put("userEmail", principal.getName());
        }
        else{
            json.put("isLoggedIn", false);
        }

        return new ResponseEntity<>(json, HttpStatus.OK);
    }


}
