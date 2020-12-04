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

import static org.junit.jupiter.api.Assertions.*;


class TestProfile {


    @Test
    void TestGetBirthday() {
        Profile test = new Profile();
        assertEquals( "01/01/2000" test.getBirthday());
    }

    @Test
    void TestSetBirthday() {
        Profile test = new Profile();
        assertEquals( "02/20/1998" , test.setBirthday("02/20/1998"));
    }

    @Test
    void TestGetBloodType() {
        Profile test = new Profile();
        assertEquals( "A+", test.getBloodType());
    }

    @Test
    void TestSetBloodType() {
        Profile test = new Profile();
        assertEquals( "A+", test.setBloodType("A+"));
    }
	
	
    @Test
    void TestGetEmail() {
        Profile test = new Profile();
        assertEquals( "username@wayne.edu", test.getEmail());
    }

    @Test
    void TestSetBirthday() {
        Profile test = new Profile();
        assertEquals( "username@wayne.edu", test.setEmail("username@wayne.edu"));
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}