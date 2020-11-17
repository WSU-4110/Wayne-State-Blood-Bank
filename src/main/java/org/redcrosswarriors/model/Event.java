package org.redcrosswarriors.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    @NotBlank
    private String title;

    @Column
    @NotNull
    @NotBlank
    private String description;

    @Column(name="event_date")
    @NotNull
    @NotBlank
    @Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}", message = "Invalid time must be in the format yyyy-mm-dd")
    private String date;

    public Event(){

    }

    public Event(String title, String description,String date){
        setTitle(title);
        setDescription(description);
        setDate(date);
    }
    public Event(int id, String title, String description, String date){
        this(title, description, date);
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
