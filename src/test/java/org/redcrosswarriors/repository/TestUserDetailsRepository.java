package org.redcrosswarriors.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.redcrosswarriors.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
public class TestUserDetailsRepository {

    @Autowired
    private UserDetailsRepository repository;

    @Test
    @Sql("/test_get_profile.sql")
    public void testGetProfile(){
        Profile profile = repository.getProfileByEmail("user@wayne.edu");
        System.out.println(profile.getBirthDay());
        assertEquals("John", profile.getFirstName());
    }

}
