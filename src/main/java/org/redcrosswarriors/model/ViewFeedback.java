package org.redcrosswarriors.model;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
* Used to store data from the feedback view in the database
*/
@Entity
@Immutable
@Table(name ="vw_feedback")
@Subselect("SELECT * FROM vw_feedback")
public class ViewFeedback {

    @Id
    private int id;

    @Column(name="email")
    private String email;

    @Column(name="message")
    private String message;

    @Column(name="name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
