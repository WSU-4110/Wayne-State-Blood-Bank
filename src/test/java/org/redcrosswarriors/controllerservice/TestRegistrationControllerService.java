package org.redcrosswarriors.controllerservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.redcrosswarriors.model.AccountDetails;
import org.redcrosswarriors.model.VerificationToken;
import org.redcrosswarriors.model.input.RegistrationInput;
import org.redcrosswarriors.repository.AccountDetailsRepository;
import org.redcrosswarriors.repository.UserDetailsRepository;
import org.redcrosswarriors.security.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TestRegistrationControllerService {

    @MockBean
    private AccountDetailsRepository accountRepository;

    @MockBean
    private UserDetailsRepository userRepository;

    @MockBean
    private VerificationTokenService tokenService;

    @MockBean
    private AccountDetailsService accountService;

    @Autowired
    private RegistrationControllerService registrationService;

    @Test
    void testRegisterAccount() {
        RegistrationInput user = new RegistrationInput();
        VerificationToken token = new VerificationToken();

        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("user@wayne.edu");
        user.setPassword("Password1234");
        user.setBloodDonorStatus("Y");
        user.setPhoneNumber("1111111111");
        user.setBloodType("A+");
        user.setBirthDay("1998-01-01");


        token.setToken("Whatever");


        when(accountRepository.findIdByEmail(user.getEmail())).thenReturn(1);
        when(tokenService.createVerificationToken(user.getEmail())).thenReturn(token);
        assertTrue(registrationService.registerAccount(user));
        verify(userRepository).registerAccount(1, user.getFirstName(),user.getLastName(),user.getBirthDay(),user.getBloodDonorStatus(),user.getPhoneNumber(), user.getBloodType());


    }

    @Test
    void testUserExists() {
        RegistrationInput user = new RegistrationInput();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("user@wayne.edu");
        user.setPassword("Password1234");
        user.setBloodDonorStatus("Y");
        user.setPhoneNumber("1111111111");
        user.setBloodType("A+");
        user.setBirthDay("1998-01-01");

        when(accountRepository.findByEmail(user.getEmail())).thenReturn(new AccountDetails());
        assertTrue(registrationService.userExists(user));


    }

    @Test
    void testUserDoesNotExists() {
        RegistrationInput user = new RegistrationInput();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("user@wayne.edu");
        user.setPassword("Password1234");
        user.setBloodDonorStatus("Y");
        user.setPhoneNumber("1111111111");
        user.setBloodType("A+");
        user.setBirthDay("1998-01-01");

        when(accountRepository.findByEmail(user.getEmail())).thenReturn(null);
        assertFalse(registrationService.userExists(user));


    }


}