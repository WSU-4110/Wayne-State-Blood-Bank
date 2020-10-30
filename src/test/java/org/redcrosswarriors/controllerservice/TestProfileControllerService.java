package org.redcrosswarriors.controllerservice;

import org.junit.jupiter.api.Test;
import org.redcrosswarriors.model.Profile;
import org.redcrosswarriors.model.input.EditProfileInput;
import org.redcrosswarriors.repository.UserDetailsRepository;
import org.redcrosswarriors.security.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestProfileControllerService {

    @MockBean
    private AccountDetailsService accountService;

    @MockBean
    private UserDetailsRepository repository;

    @Autowired
    private ProfileControllerService service;

    @Test
    public void testDeleteProfile(){
        ResponseEntity<Object> response = service.deleteProfile("user@wayne.edu");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateProfile(){
        EditProfileInput input = new EditProfileInput();
        input.setFirstName("John");
        input.setLastName("Doe");
        input.setPassword("Password1234");
        input.setBloodDonorStatus("Y");
        input.setPhoneNumber("1111111111");

        ResponseEntity<Object> response = service.updateProfile("user@wayne.edu", input);
        verify(accountService).updatePassword("user@wayne.edu", "Password1234");
        verify(repository).updateProfileByEmail("user@wayne.edu",
                input.getFirstName(), input.getLastName(), input.getPhoneNumber(), input.getBloodDonorStatus());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetProfile(){
        Profile profile = new Profile();
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setBirthDay("0000-01-01");
        profile.setBloodType("A+");
        profile.setEmail("john@wayne.edu");
        profile.setBloodDonorStatus("Y");
        when(repository.getProfileByEmail("john@wayne.edu")).thenReturn(profile);
        ResponseEntity<Object> response = service.getProfile("john@wayne.edu");
        verify(repository).getProfileByEmail("john@wayne.edu");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
