package org.redcrosswarriors.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.redcrosswarriors.controllerservice.ProfileControllerService;
import org.redcrosswarriors.controllerservice.RegistrationControllerService;
import org.redcrosswarriors.model.input.RegistrationInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestRegistrationController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationControllerService service;

    @Test
    public void testRegisterAccount() throws Exception
    {
        RegistrationInput input  = new RegistrationInput();
        input.setEmail("go1277@wayne.edu");
        input.setPassword("Melvagijo14!");
        input.setFirstName("John");
        input.setLastName("hI");
        input.setBirthDay("2000-05-05");
        input.setBloodDonorStatus("Y");
        input.setPhoneNumber("1234567891");
        input.setBloodType("A+");

        when(service.registerAccount(input)).thenReturn(true);
        when(service.userExists(input)).thenReturn(false);
        Gson gson = new Gson();
        String json = gson.toJson(input);
        this.mockMvc.perform(post("/registration").contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk());



    }

    @Test
    public void testBadRegisterAccount() throws Exception
    {
        RegistrationInput input  = new RegistrationInput();
        input.setEmail("go1277@gmail.com");
        input.setPassword("bad_password");
        input.setFirstName("John");
        input.setLastName("hI");
        input.setBirthDay("2000-05-05");
        input.setBloodDonorStatus("Y");
        input.setPhoneNumber("1234567891");
        input.setBloodType("A+");

        when(service.registerAccount(input)).thenReturn(true);
        when(service.userExists(input)).thenReturn(false);
        Gson gson = new Gson();
        String json = gson.toJson(input);
        this.mockMvc.perform(post("/registration").contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isBadRequest());



    }
}