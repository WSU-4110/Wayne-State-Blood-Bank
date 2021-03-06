package org.redcrosswarriors.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.redcrosswarriors.model.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
public class TestAccountDetailsRepository {

    @Autowired
    private AccountDetailsRepository repository;


    @Test
    @Sql("/test_account_details_no_roles.sql")
    public void testUpdatePassword(){
        repository.updatePassword("user@example.com", "newPassword");
        AccountDetails details = repository.findByEmail("user@example.com");
        assertEquals("newPassword", details.getPassword());
    }


    @Test
    @Sql("/test_account_details_no_roles.sql")
    public void testRemoveAccountByEmail(){
        try{
            repository.removeAccountByEmail("user@example.com");
        }
        catch (Exception e){
            fail(); // if it fails to delete then fail the test
        }
    }

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

    @Test
    @Sql("/test_account_details.sql")
    public void testGetRolesByEmail(){
        List<String> roles = repository.getRolesByEmail("user@example.com");
        assertEquals(2, roles.size());
    }

}
