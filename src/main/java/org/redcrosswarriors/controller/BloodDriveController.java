package org.redcrosswarriors.controller;

import org.redcrosswarriors.model.BloodDrive;
import org.redcrosswarriors.repository.BloodDriveRepository;
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
public class BloodDriveController {

    @Autowired
    private BloodDriveRepository repository;

    @GetMapping("/bloodDrive/upcoming")
    public ResponseEntity<List<BloodDrive>> getUpcomingDrives(){
        List<BloodDrive> bloodDrives = repository.findMostRecentBloodDrives();
        return new ResponseEntity<List<BloodDrive>>(bloodDrives, HttpStatus.OK);
    }

    @GetMapping("/bloodDrive")
    public ResponseEntity<List<BloodDrive>> getAllBloodDrives(){
        List<BloodDrive>  bloodDrives = StreamSupport.stream(
                repository.findAll().spliterator(),
                false
        ).collect(Collectors.toList());

        return new ResponseEntity<List<BloodDrive>>(bloodDrives, HttpStatus.OK);
    }

    @PostMapping("/bloodDrive")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> createBloodDrive(@Valid @RequestBody BloodDrive bloodDrive){
        Map<String, Object> json = new HashMap<String, Object>();
        try{
            repository.save(bloodDrive);
            json.put("message", "Successfully created a new blood drive");
        }
        catch (Exception e){
            json.put("message", "An unknown error has occurred");
            return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(json, HttpStatus.CREATED);
    }

    @DeleteMapping("/bloodDrive/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> deleteBloodDrive(@PathVariable("id") int id){
        Map<String, Object> json = new HashMap<String, Object>();
        try{
            repository.deleteById(id);
            json.put("message", "Successfully deleted blood drive");
        }
        catch (Exception e){
            json.put("message", "An unknown error has occurred");
            return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @DeleteMapping("/bloodDrive/outdated")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> deleteOutdatedBloodDrives(){
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("message", "Successfully deleted all outdated blood drives");
        repository.deleteOutdatedBloodDrives();
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

}
