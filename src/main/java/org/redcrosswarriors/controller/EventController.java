package org.redcrosswarriors.controller;

import org.redcrosswarriors.model.Event;
import org.redcrosswarriors.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventRepository repository;

    @GetMapping("/event")
    public ResponseEntity<List<Event>> getEvents(){
        List<Event> events = StreamSupport.stream(
                repository.findAll().spliterator(), false
        ).collect(Collectors.toList());
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping("/event")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> createEvent(@RequestBody @Valid Event event){
        Map<String, String> json = new HashMap<String, String>();
        json.put("message", "Successfully created event");
        repository.save(event);
        return new ResponseEntity<>(json, HttpStatus.CREATED);
    }

    @GetMapping("/event/upcoming")
    public ResponseEntity<List<Event>> getUpcomingEvents(){
        List<Event> events = repository.getUpcomingEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @DeleteMapping("/event/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> deleteEvent(@PathVariable("id") int id){
        Map<String, String> json = new HashMap<>();
        repository.deleteById(id);
        json.put("message", "Successfully deleted event");
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @DeleteMapping("/event/outdated")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> deleteOutdatedEvents(){
        Map<String, String> json = new HashMap<>();
        repository.deleteOutdatedEvents();
        json.put("message", "Successfully deleted outdated events");
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
