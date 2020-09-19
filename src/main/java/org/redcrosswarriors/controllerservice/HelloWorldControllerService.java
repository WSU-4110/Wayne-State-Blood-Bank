package org.redcrosswarriors.controllerservice;

import org.redcrosswarriors.model.Message;
import org.redcrosswarriors.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HelloWorldControllerService {
    @Autowired
    private MessageRepository repository;

    public List<Message> getAllMessages(){
        return StreamSupport.stream(repository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    public Message getMessageById(int id){
        return repository.findById(id).get();
    }

    public void createMessage(Message message){
        repository.save(message);
    }

    public void deleteMessage(int id){
        repository.deleteById(id);
    }

}
