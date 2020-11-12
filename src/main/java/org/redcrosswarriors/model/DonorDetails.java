package org.redcrosswarriors.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="donorUserDetails")
public class DonorDetails implements RegistrationInfo
{
    @Column
    private char bloodType;

    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public void setFirstName(String firstName) {

    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public void setLastName(String lastName) {

    }

    @Override
    public String getBirthDay() {
        return null;
    }

    @Override
    public void setBirthDay(String birthDay) {

    }

    @Override
    public char getBloodDonor() {
        return 0;
    }

    @Override
    public void setBloodDonor(char bloodDonor) {

    }

    @Override
    public int getPhoneNumber() {
        return 0;
    }

    @Override
    public void setPhoneNumber(int phoneNumber) {

    }

    public char getBloodType() {
        return bloodType;
    }

    public void setBloodType(char bloodType) {
        this.bloodType = bloodType;
    }
}
