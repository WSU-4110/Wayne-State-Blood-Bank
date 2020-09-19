package org.redcrosswarriors.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="messages")
public class Message {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id; 
    
    @Column
    @NotBlank(message="Message Must Not be blank")
    private String message;

    public Message(){

    }

    public Message(int id, String message){
        this.id = id;
        this.message = message;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public int getId(){
        return this.id;
    }

    public String getMessage(){
        return this.message;
    }
}
