package org.redcrosswarriors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.redcrosswarriors.model.Message;
import java.util.List;
import org.redcrosswarriors.controllerservice.HelloWorldControllerService;
import org.json.JSONObject;

@RestController
public class HelloWorldController {
    @Autowired
    HelloWorldControllerService service;

    @GetMapping(value="/message")
    public ResponseEntity<List<Message>> getMessages(){
        return new ResponseEntity<>(service.getAllMessages(), HttpStatus.OK);
    }

    @PostMapping(value="/message")
    public ResponseEntity<Object> createMessage(@RequestBody Message message){
        service.createMessage(message);
        JSONObject response = new JSONObject();
        response.append("message", "Message Created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value="/message/{id}")
    public ResponseEntity<Object> getMessage(@PathVariable int id){
        return new ResponseEntity<>(service.getMessageById(id), HttpStatus.OK);
    }

    @DeleteMapping(value="/message/{id}")
    public ResponseEntity<Object> deleteMessage(@PathVariable int id){
        JSONObject response = new JSONObject();
        response.append("message", "Message Deleted");
        service.deleteMessage(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
