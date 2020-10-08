package org.redcrosswarriors.model.input;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Immutable
@Table(name ="userDetails")
@Subselect("SELECT * FROM userDetails")
public class registrationInput {


    public int getId(){
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public char getBloodDonor() {
        return bloodDonor;
    }

    public void setBloodDonor(char bloodDonor) {
        this.bloodDonor = bloodDonor;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public char getBloodType() {
        return bloodType;
    }

    public void setBloodType(char bloodType) {
        this.bloodType = bloodType;
    }

    @Id
    private int id;

    @Column
    @NotNull
    private String firstName;

    @Column
    private String lastName;

    @Column
    @NotNull
    private String birthDay;

    @Column
    @NotNull
    private char bloodDonor;

    @Column
    private int phoneNumber;

    @Column
    private char bloodType;


}
