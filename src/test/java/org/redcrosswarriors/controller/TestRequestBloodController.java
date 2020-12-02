package org.redcrosswarriors.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.redcrosswarriors.controllerservice.RequestBloodControllerService;
import org.redcrosswarriors.model.Profile;
import org.redcrosswarriors.model.input.RequestBloodInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestRequestBloodController {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RequestBloodControllerService service;

    @WithMockUser(username="admin@wayne.edu",roles={"USER","ADMIN"})
    @Test
    public void testRequestBlood() throws Exception {
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

        Map<String, Object> responseObject = new HashMap<String, Object>();
        responseObject.put("message", "Successfully requested");
        when(service.requestBlood(input, "admin@wayne.edu")).thenReturn(new ResponseEntity<Object>(responseObject, HttpStatus.OK));
        Gson gson = new Gson();
        String json = gson.toJson(input);
        this.mockMvc.perform(post("/requestBlood").contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "admin@wayne.edu", roles={"USER", "ADMIN"})
    public void testGetEmail() throws Exception{
        Map<String, String> responseObject = new HashMap<String, String>();
        responseObject.put("email", "admin@wayne.edu");
        this.mockMvc.perform(get("/requestBlood")).andDo(print()).andExpect(status().isOk());
    }
}