package org.redcrosswarriors.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name ="nonDonorUserDetails")
public class NonDonorDetails implements RegistrationInfo
{

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
}
