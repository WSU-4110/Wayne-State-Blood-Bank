package org.redcrosswarriors.controllerservice;

import org.junit.jupiter.api.Test;
import org.redcrosswarriors.model.Role;
import org.redcrosswarriors.model.VerificationToken;
import org.redcrosswarriors.repository.AccountDetailsRepository;
import org.redcrosswarriors.repository.RoleRepository;
import org.redcrosswarriors.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@SpringBootTest
public class TestVerificationTokenService {
    @MockBean
    private AccountDetailsRepository accountDetailsRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private VerificationTokenRepository repository;

    @Autowired
    private VerificationTokenService service;


    @Test
    public void testCreateVerificationToken(){
        UUID uuid = UUID.randomUUID();
        VerificationToken token = new VerificationToken();
        token.setToken(uuid.toString());
        token.setAccountId(1);
        when(repository.findById(anyString())).thenReturn(
                Optional.of(token)
        );
        VerificationToken actualToken = service.createVerificationToken("user@example.com");
        assertEquals(token.getToken(), actualToken.getToken());
    }

    @Test
    public void testVerifyUser(){
        String token = "abc-123";
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken("abc-123");
        verificationToken.setAccountId(1);
        Role role = new Role();
        role.setId(1);
        role.setRoleName("TEST_ROLE");
        when(repository.findById(token)).thenReturn(Optional.of(verificationToken));
        when(roleRepository.getRoleByName(anyString())).thenReturn(role);
        ResponseEntity entity = service.verifyUser(token);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

}
