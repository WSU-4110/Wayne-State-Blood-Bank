package org.redcrosswarriors.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.redcrosswarriors.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
public class TestRoleRepository {


    @Autowired
    private RoleRepository repository;

    @Test
    public void testGetRoleByName(){
        Role role = repository.getRoleByName("USER");
        assertEquals("USER", role.getRoleName());
    }

}
