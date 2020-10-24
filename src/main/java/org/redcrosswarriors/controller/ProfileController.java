package org.redcrosswarriors.controller;

import org.redcrosswarriors.controllerservice.ProfileControllerService;
import org.redcrosswarriors.model.input.EditProfileInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class ProfileController {
    @Autowired
    private ProfileControllerService service;

    @PutMapping("/profile")
    @Secured("ROLE_USER")
    public ResponseEntity<Object> editProfile(Principal principal, @Valid @RequestBody EditProfileInput input) {
        return service.updateProfile(principal.getName(), input);
    }

    @GetMapping("/profile")
    @Secured("ROLE_USER")
    public ResponseEntity<Object> getProfile(Principal principal){
        return service.getProfile(principal.getName());
    }

    @DeleteMapping("/profile")
    @Secured("ROLE_USER")
    public ResponseEntity<Object> deleteProfile(Principal principal){
        return service.deleteProfile(principal.getName());
    }
}
