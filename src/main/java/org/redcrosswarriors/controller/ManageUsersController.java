package org.redcrosswarriors.controller;

//import org.redcrosswarriors.model.BloodDrive;
//import org.redcrosswarriors.repository.BloodDriveRepository;

import org.redcrosswarriors.model.Profile;
import org.redcrosswarriors.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class ManageUsersController {

    @Autowired
    private UserDetailsRepository repository;

    @GetMapping("/user")
 //   @Secured("ROLE_ADMIN")
    public ResponseEntity<List<Profile>> getUsers() {
        List<Profile> users = repository.getAllProfiles();
        return new ResponseEntity<List<Profile>>(users, HttpStatus.OK);
    }

}





