package org.redcrosswarriors.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.redcrosswarriors.model.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TestAccountDetailsRepository {

    @Autowired
    private AccountDetailsRepository repository;


    @Test
    @Sql("/test_account_details.sql")
    public void findByEmail(){
        AccountDetails account = repository.findByEmail("user@example.com");
        assertNotNull(account);
        assertEquals(2, account.getRoles().length);
        assertEquals("password", account.getPassword());
    }
}
