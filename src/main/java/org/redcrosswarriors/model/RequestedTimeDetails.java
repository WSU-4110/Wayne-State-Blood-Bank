package org.redcrosswarriors.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "requester_time")

public class RequestedTimeDetails
{
    @Id
    int id;

    @Column
    @NotNull
    String email;

    @Column
    String time_requested;

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

    public String getTime() {
        return time_requested;
    }

    public void setTime(String time) {
        this.time_requested = time_requested;
    }


}
