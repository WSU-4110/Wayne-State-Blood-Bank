package org.redcrosswarriors.controller;

import org.redcrosswarriors.model.input.EditProfileInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProfileController {

    @PutMapping("/profile")
    public ResponseEntity<Object> editProfile(@Valid @RequestBody EditProfileInput input) {
        return new ResponseEntity<Object>("good", HttpStatus.OK);
    }
}
