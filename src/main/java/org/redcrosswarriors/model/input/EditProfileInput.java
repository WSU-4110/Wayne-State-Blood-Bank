package org.redcrosswarriors.model.input;

import org.redcrosswarriors.model.AbstractProfile;

import javax.validation.constraints.Pattern;

public class EditProfileInput extends AbstractProfile {

    // password must follow the password rules or be null
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
            message = "password must contain at least one digit, one lowercase letter, one uppercase letter, " +
                    "and be at least 8 characters long")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
