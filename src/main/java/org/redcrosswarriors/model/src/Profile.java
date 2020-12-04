package org.redcrosswarriors.model;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Immutable
@Table(name ="vw_user_profile")
@Subselect("SELECT * FROM vw_user_profile")
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
    @Id
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
