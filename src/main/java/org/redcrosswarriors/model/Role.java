package org.redcrosswarriors.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="role_name")
    @NotBlank(message="role can't be blank")
    @NotNull
    private String roleName;

    public void setId(int id){
        this.id = id;
    }

    public void setRoleName(String roleName){
        this.roleName = roleName;
    }

    public String getRoleName(){
        return this.roleName;
    }

    public int getId(){
        return this.id;
    }

}
