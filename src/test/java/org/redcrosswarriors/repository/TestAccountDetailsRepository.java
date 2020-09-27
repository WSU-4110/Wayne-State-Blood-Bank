package org.redcrosswarriors.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.redcrosswarriors.model.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TestAccountDetailsRepository {

    @Autowired
    private AccountDetailsRepository repository;

    @Test
    @Sql("/test_account_details.sql")
    public void testFindByEmail(){
        AccountDetails account = repository.findByEmail("user@example.com");
        assertNotNull(account);
        assertEquals(2, account.getRoles().length);
        assertEquals("password", account.getPassword());
    }

    @Test
    @Sql("/test_account_details_no_roles.sql")
    public void testFindByEmailNoRoles(){
        AccountDetails account = repository.findByEmail("user@example.com");
        assertNotNull(account);
        assertEquals(0, account.getRoles().length);
    }

    @Test
    @Sql("/test_account_details_no_roles.sql")
    public void testCreateAccountWithErrors(){
        try{
            repository.createAccount("user@example.com", "password");
            fail(); // it should throw an exception at before this
        }
        catch (Exception e){
            assert(true);
        }
    }

    @Test
    public void testCreateAccount(){
        repository.createAccount("user@example.com", "password");
        AccountDetails details = repository.findByEmail("user@example.com");
        assertNotNull(details);
    }
}
