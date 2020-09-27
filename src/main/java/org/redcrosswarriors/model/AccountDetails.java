package org.redcrosswarriors.model;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Immutable
@Table(name ="account_details")
@Subselect("SELECT * FROM account_details")
public class AccountDetails {

    @Column
    @NotNull
    @Id
    private String email;

    @Column
    @NotNull
    private String password;

    @Column
    private String roles;

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String[] getRoles(){
        return roles.split(",");
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setRoles(String roles){
        this.roles = roles;
    }
}
