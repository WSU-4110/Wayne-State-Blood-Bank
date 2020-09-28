package org.redcrosswarriors.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="verification_tokens")
public class VerificationToken {

    @Id
    private String token;

    @Column(name="account_id")
    @NotNull
    private int accountId;

    public String getToken(){
        return this.token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public void setAccountId(int id){
        this.accountId = id;
    }

    public int getAccountId(){
        return this.accountId;
    }

}
