package org.redcrosswarriors.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="blood_drives")
public class BloodDrive {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    @NotBlank
    private String title;

    @Column
    @NotNull
    @NotBlank
    private String location;

    @Column(name="blood_drive_time")
    @NotNull
    @NotBlank
    @Pattern(regexp="^\\d{2}:\\d{2}:\\d{2}", message = "Invalid time must be in the format HH:mm:ss")
    private String time;

    @Column(name="blood_drive_date")
    @NotNull
    @NotBlank
    @Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}", message = "Invalid time must be in the format yyyy-mm-dd")
    private String date;

    @Column
    @NotNull
    @NotBlank
    private String description;

    @Column
    private String link;

    public BloodDrive(){

    }
    public BloodDrive(String title, String location, String time, String date, String description, String link){
        setTitle(title);
        setLocation(location);
        setTime(time);
        setDate(date);
        setDescription(description);
        setLink(link);
    }
    public BloodDrive(int id, String title, String location, String time, String date, String description, String link){
        this(title, location, time, date, description, link);
        setId(id);
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public String getLocation(){
        return this.location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getTime(){
        return this.time;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public String getLink(){
        return this.link;
    }

    public void setLink(String link){
        this.link = link;
    }
}
