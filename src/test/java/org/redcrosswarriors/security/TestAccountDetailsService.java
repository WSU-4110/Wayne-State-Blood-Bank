package org.redcrosswarriors.security;

import org.junit.jupiter.api.Test;
import org.redcrosswarriors.model.AccountDetails;
import org.redcrosswarriors.model.Role;
import org.redcrosswarriors.repository.AccountDetailsRepository;
import org.redcrosswarriors.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TestAccountDetailsService {

    @Autowired
    AccountDetailsService service;

    @MockBean
    private AccountDetailsRepository repository;

    @MockBean
    private RoleRepository roleRepository;


    private AccountDetails getAccountDetails(){
        AccountDetails details = new AccountDetails();
        details.setId(1);
        details.setRoles("ADMIN,USER");
        details.setPassword("password");
        details.setEmail("user@example.com");

        return details;
    }

    private void mockRoles(){
        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        adminRole.setId(1);

        Role userRole = new Role();
        userRole.setRoleName("USER");
        userRole.setId(2);

        when(roleRepository.getRoleByName("ADMIN")).thenReturn(adminRole);
        when(roleRepository.getRoleByName("USER")).thenReturn(userRole);
    }

    @Test
    public void testCreateAccount(){
        AccountDetails details = getAccountDetails();
        when(repository.findByEmail(details.getEmail())).thenReturn(details);

        mockRoles();
        // assert that the method is called and there are no exceptions
        try{
            service.createAccount(details);
        }
        catch (Exception e){
            fail();
        }
    }

}
