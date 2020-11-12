package org.redcrosswarriors.model;


import javax.persistence.Column;
import javax.validation.constraints.NotNull;

interface RegistrationInfo
{

    public String getFirstName();

    public void setFirstName(String firstName);

    public String getLastName();

    public void setLastName(String lastName);

    public String getBirthDay();

    public void setBirthDay(String birthDay);

    public char getBloodDonor();

    public void setBloodDonor(char bloodDonor);

    public int getPhoneNumber();

    public void setPhoneNumber(int phoneNumber);



    @Column
    @NotNull
    String firstName = null;

    @Column
    @NotNull
    String lastName = null;

    @Column
    @NotNull
    String birthDay = null;

    @Column
    @NotNull
    char bloodDonor = 0;

    @Column
    @NotNull
    String phoneNumber = null;



}
