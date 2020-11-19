package org.redcrosswarriors.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.redcrosswarriors.controllerservice.ProfileControllerService;
import org.redcrosswarriors.controllerservice.RegistrationControllerService;
import org.redcrosswarriors.controllerservice.RequestBloodControllerService;
import org.redcrosswarriors.model.input.RegistrationInput;
import org.redcrosswarriors.model.input.RequestBloodInput;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestRequestBloodController {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RequestBloodControllerService service;

    @Test
    public void testRequestBlood() throws Exception
    {
        RequestBloodInput input  = new RequestBloodInput();

        input.setFirstName("John");
        input.setLastName("Doe");
        input.setEmail("johhn@wayne.edu");
        input.setPhoneNumber("0000000000");
        input.setBloodType("AB+");
        input.setHospitalName("Beaumont");
        input.setStreetName("1234 XYZ");
        input.setCityName("ABC");
        input.setStateName("MI");
        input.setZipCode("48084");
        input.setMessage("I need Blood");

        when(service.requestBlood(input)).thenReturn(true);
        Gson gson = new Gson();
        String json = gson.toJson(input);
        this.mockMvc.perform(post("/requestblood").contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk());

    }
}