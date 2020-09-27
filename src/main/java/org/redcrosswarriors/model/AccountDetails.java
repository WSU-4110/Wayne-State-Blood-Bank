package org.redcrosswarriors.model;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Immutable
@Table(name ="account_details")
@Subselect("SELECT * FROM account_details")
public class AccountDetails {
    @Id
    private int id;

    @Column
    @NotNull
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
        if(roles != null){
            return roles.split(",");
        }
        return new String[0];
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

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }
}
