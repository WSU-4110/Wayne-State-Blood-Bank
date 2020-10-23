package org.redcrosswarriors.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Profile extends AbstractProfile{

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(A\\+)|(B\\+)|(A-)|(B-)|(O\\+)|(O-)|(AB\\+)|(AB-)",
    message = "Invalid blood type")
    protected String bloodType;

    @NotNull
    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format must be yyyy-mm-dd")
    protected String birthDay;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^.{1,64}@wayne\\.edu", message = "Invalid email must be a valid wayne state email")
    protected String email;


    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
