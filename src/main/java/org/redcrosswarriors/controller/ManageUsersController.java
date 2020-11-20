package org.redcrosswarriors.controller;

import org.redcrosswarriors.model.Profile;
import org.redcrosswarriors.repository.AccountDetailsRepository;
import org.redcrosswarriors.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManageUsersController {

    @Autowired
    private UserDetailsRepository userRepository;

    @Autowired
    private AccountDetailsRepository accountRepository;

    @GetMapping("/user")
 //   @Secured("ROLE_ADMIN")
    public ResponseEntity<List<Profile>> getUsers() {
        List<Profile> users = userRepository.getAllProfiles();
        return new ResponseEntity<List<Profile>>(users, HttpStatus.OK);
    }

    @DeleteMapping("/user")
  //  @Secured("ROLE_ADMIN")
    public void deleteUser(String emailAddress) {
        accountRepository.removeAccountByEmail(emailAddress);
    }

}





