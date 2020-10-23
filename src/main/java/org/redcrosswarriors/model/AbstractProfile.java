/*
The purpose of this class is to be used as a base class for
all profile type classes. It is meant to store basic information that
all profile inputs should have and validate those fields.
 */
package org.redcrosswarriors.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public abstract class AbstractProfile {

    @NotBlank
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+", message="Invalid first name can only contain alphabetical characters")
    protected String firstName;

    @NotBlank
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+", message="Invalid last name can only contain alphabetical characters")
    protected String lastName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\d{10}", message = "Invalid phone number must only contain digits")
    protected String phoneNumber;

    //type of string instead of boolean so that it doesn't break existing code
    @NotNull
    @NotBlank
    @Pattern(regexp = "^Y|N", message = "Invalid blood donor status must be either 'Y' or 'N'")
    protected String bloodDonorStatus;


    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setBloodDonorStatus(String bloodDonorStatus){
        this.bloodDonorStatus = bloodDonorStatus;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public String getBloodDonorStatus(){
        return this.bloodDonorStatus;
    }
}
